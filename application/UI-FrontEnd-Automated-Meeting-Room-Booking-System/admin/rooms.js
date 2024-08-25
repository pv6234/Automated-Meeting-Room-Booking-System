// room data (for demo purposes)
const rooms = [
    {
        name: 'Training Room',
        capacity: 15,
        amenities: ['Projector', 'Whiteboard', 'WiFi'],
        image: '../images/training-room.jpg'
    },
    {
        name: 'Conference Room',
        capacity: 10,
        amenities: ['Conference Call', 'TV', 'WiFi'],
        image: '../images/conference-room.jpg'
    },
    {
        name: 'Meeting Room',
        capacity: 8,
        amenities: ['Whiteboard', 'Coffee Machine', 'Water Dispenser'],
        image: '../images/meeting-room.jpg'
    }
];

// Function to initialize the room carousel
function initializeCarousel() {
    const carouselTrack = document.querySelector('.carousel-track');
    carouselTrack.innerHTML = '';

    rooms.forEach(room => {
        const roomCard = document.createElement('div');
        roomCard.classList.add('carousel-item');
        roomCard.innerHTML = `
            <img src="${room.image}" alt="${room.name}" style="width:100%; height:auto; border-radius: 8px;" class= "room-image">
            <h3>${room.name}</h3>
            <p>Capacity: ${room.capacity} people</p>
            <p>Amenities: ${room.amenities.join(', ')}</p>
        `;
        carouselTrack.appendChild(roomCard);
    });

    const carouselTrackContainer = document.querySelector('.carousel-track-container');
    carouselTrackContainer.scrollLeft = 0;
}

function moveCarousel(direction) {
    const carouselTrack = document.querySelector('.carousel-track');
    const cards = document.querySelectorAll('.carousel-item');

    if(cards.length === 0) return;

    const cardWidth = cards[0].offsetWidth + parseFloat(getComputedStyle(cards[0]).marginRight);
    let maxScrollLeft = carouselTrack.scrollLeft + direction *  cardWidth;


    if(maxScrollLeft < 0) maxScrollLeft = 0;



    if (maxScrollLeft > carouselTrack.scrollWidth - carouselTrack.clientWidth) {
        maxScrollLeft = carouselTrack.scrollWidth - carouselTrack.clientWidth;
    }

    carouselTrack.scrollTo({
        left: maxScrollLeft,
        behavior: 'smooth'
    });
}

// Function to create a new room
function createRoom() {
    const roomName = document.getElementById('roomName').value;
    const seatingCapacity = document.getElementById('seatingCapacity').value;

    const amenities = Array.from(document.querySelectorAll('.amenity-checkbox:checked')).map(checkbox => checkbox.value);

    const roomImageInput = document.getElementById('roomImage');
    const roomImage = roomImageInput.files[0];
    let imageURL = 'default-room.jpg'; 

    if (roomImage) {
        imageURL = URL.createObjectURL(roomImage);
    }

    const newRoom = {
        name: roomName,
        capacity: parseInt(seatingCapacity),
        amenities: amenities,
        image: imageURL
    };

    rooms.push(newRoom);
    initializeCarousel(); 
    clearForm(); 
}

// Function to search for a room by name
function searchRoom() {
    const searchRoomName = document.getElementById('searchRoomName').value;
    const room = rooms.find(room => room.name === searchRoomName);

    if (room) {
        document.getElementById('roomDetails').style.display = 'block';
        document.getElementById('newRoomName').value = room.name;
        document.getElementById('newSeatingCapacity').value = room.capacity;

        document.querySelectorAll('.new-amenity-checkbox').forEach(checkbox => {
            checkbox.checked = room.amenities.includes(checkbox.value);
        });
    } else {
        alert('Room not found.');
        document.getElementById('roomDetails').style.display = 'none';
    }
}

// Function to update the room details
function updateRoom() {
    const searchRoomName = document.getElementById('searchRoomName').value;
    const roomIndex = rooms.findIndex(room => room.name === searchRoomName);

    if (roomIndex !== -1) {
        const newRoomName = document.getElementById('newRoomName').value;
        const newSeatingCapacity = document.getElementById('newSeatingCapacity').value;
        const amenities = Array.from(document.querySelectorAll('.new-amenity-checkbox:checked')).map(checkbox => checkbox.value);

        const roomImageInput = document.getElementById('newRoomImage');
        const roomImage = roomImageInput.files[0];
        let imageURL = rooms[roomIndex].image; 

        if (roomImage) {
            imageURL = URL.createObjectURL(roomImage);
        }

        rooms[roomIndex] = {
            name: newRoomName,
            capacity: parseInt(newSeatingCapacity),
            amenities: amenities,
            image: imageURL
        };

        initializeCarousel(); 
        clearForm();
    } else {
        alert('Room not found.');
    }
}

function clearForm() {
    document.getElementById('modifyRoomForm').reset();
    document.getElementById('roomDetails').style.display = 'none';
}

window.onload = initializeCarousel;
