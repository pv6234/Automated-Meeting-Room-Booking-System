const rooms = [
    {
        name: 'Training Room',
        capacity: 6,
        amenities: ['Projector', 'Whiteboard'],
        image: '../images/training-room.jpg'
    },
    {
        name: 'Seminar Room',
        capacity: 8,
        amenities: ['WiFi', 'Conference Call', 'Projector'],
        image: '../images/seminar-room.jpeg'
    },
    {
        name: 'Conference Room',
        capacity: 12,
        amenities: ['TV', 'Coffee Machine', 'Projector'],
        image: '../images/conference-room.jpg'
    },
    {
        name: 'Meeting Room',
        capacity: 10,
        amenities: ['Projector', 'Whiteboard', 'WiFi'],
        image: '../images/meeting-room.jpg'
    },
    {
        name: 'Office Space',
        capacity: 20,
        amenities: ['TV', 'Coffee Machine', 'Water Dispenser'],
        image: '../images/office-space.jpg'
    }
];

// Function to update the carousel and booking options
function updateDashboard() {
    const mcarousel = document.getElementById('mcarousel');
    const roomSelect = document.getElementById('roomSelect');
    
    mcarousel.innerHTML = ''; 
    roomSelect.innerHTML = ''; 
    
    rooms.forEach(room => {
        const item = document.createElement('div');
        item.classList.add('mcarousel-item');
        item.innerHTML = `
            <img src="${room.image}" alt="${room.name}" class="room-image">
            <div class="room-details">
                <p><strong>${room.name}</strong></p>
                <p>Capacity: ${room.capacity}</p>
                <p>Amenities: ${room.amenities.join(', ')}</p>
            </div>
        `;
        mcarousel.appendChild(item);
        
        const option = document.createElement('option');
        option.value = room.name;
        option.textContent = room.name;
        roomSelect.appendChild(option);
    });
}

// Function to handle room booking
function bookRoom() {
    const roomName = document.getElementById('roomSelect').value;
    const date = document.getElementById('bookingDate').value;
    const time = document.getElementById('bookingTime').value;
    
    if (roomName && date && time) {
        alert(`Room ${roomName} booked on ${date} at ${time}.`);
    } else {
        alert('Please fill in all booking details.');
    }
}

// Function to move the carousel
function moveCarousel(direction) {
    const mcarousel = document.getElementById('mcarousel');
    const items = mcarousel.querySelectorAll('.mcarousel-item');
    
    if (items.length === 0) return; // Exit if there are no items

    const itemWidth = items[0].offsetWidth + parseFloat(getComputedStyle(items[0]).marginRight);
    let scrollAmount = mcarousel.scrollLeft + direction * itemWidth;
    console.log(scrollAmount)

    // Prevent scrolling past the start
    if (scrollAmount < 0) scrollAmount = 0;
    
    // Prevent scrolling past the end
    if (scrollAmount > mcarousel.scrollWidth - mcarousel.clientWidth) {
        scrollAmount = mcarousel.scrollWidth - mcarousel.clientWidth;
    }
    console.log(mcarousel.scrollWidth - mcarousel.clientWidth)

    mcarousel.scrollTo({
        left: scrollAmount,
        behavior: 'smooth'
    });
    console.log(scrollAmount)
}


window.onload = updateDashboard;



// Dummy data 
let bookings = [
    {
        roomName: 'Conference Room',
        date: '2024-09-01',
        time: '10:00',
        capacity: 12,
        amenities: ['TV', 'Coffee Machine', 'Projector']
    },
    {
        roomName: 'Training Room',
        date: '2024-09-03',
        time: '14:00',
        capacity: 6,
        amenities: ['Projector', 'Whiteboard']
    }
];

// Function to load schedule data
function loadSchedule() {
    const scheduleContainer = document.getElementById('scheduleContainer');

    scheduleContainer.innerHTML = '';

    bookings.forEach(booking => {
        const card = document.createElement('div');
        card.classList.add('schedule-card');


        const roomName = document.createElement('h3');
        roomName.textContent = booking.roomName;
        card.appendChild(roomName);

      
        const detailsDiv = document.createElement('div');
        detailsDiv.classList.add('schedule-details');

       
        const date = document.createElement('p');
        date.innerHTML = `<strong>Date:</strong> ${booking.date}`;
        detailsDiv.appendChild(date);

 
        const time = document.createElement('p');
        time.innerHTML = `<strong>Time:</strong> ${booking.time}`;
        detailsDiv.appendChild(time);

 
        const capacity = document.createElement('p');
        capacity.innerHTML = `<strong>Capacity:</strong> ${booking.capacity}`;
        detailsDiv.appendChild(capacity);

        card.appendChild(detailsDiv);

     
        const amenities = document.createElement('p');
        amenities.classList.add('amenities');
        amenities.innerHTML = `<strong>Amenities:</strong> ${booking.amenities.join(', ')}`;
        card.appendChild(amenities);

        scheduleContainer.appendChild(card);
    });
}



// Dummy data 
let managerCredits = 2000;


function resetCredits() {
    managerCredits = 2000; 
    document.getElementById('currentCredits').textContent = managerCredits; 
    showSuccessMessage();
}

// show a success message
function showSuccessMessage() {
    const successMessage = document.getElementById('successMessage');
    successMessage.style.display = 'block'; 
    setTimeout(() => {
        successMessage.style.display = 'none'; 
    }, 3000);
}

function automaticCreditReset() {
    const now = new Date();
    const day = now.getDay(); 
    const hours = now.getHours(); 
    const minutes = now.getMinutes(); 

    if (day === 1 && hours === 6 && minutes === 0) {
        resetCredits();
    }
}

function startAutomaticCreditReset() {
    setInterval(automaticCreditReset, 60000);
}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('currentCredits').textContent = managerCredits; 
    startAutomaticCreditReset(); 
});
