// Function to handle login
document.getElementById('loginForm')?.addEventListener('submit', function(event) {
    event.preventDefault();
    
    // Get the selected role
    const role = document.getElementById('role').value;

    // Redirect based on role
    switch(role) {
        case 'Admin':
            window.location.href = 'admin.html';
            break;
        case 'Manager':
            window.location.href = 'manager.html';
            break;
        case 'Member':
            window.location.href = 'member.html';
            break;
        default:
            alert('Invalid role selected');
    }
});

// Function to handle room creation (admin)
document.getElementById('createRoomForm')?.addEventListener('submit', function(event) {
    event.preventDefault();
    alert('Room created successfully!');
    // Logic to store room details
});

// Function to handle room editing (admin)
document.getElementById('editRoomForm')?.addEventListener('submit', function(event) {
    event.preventDefault();
    alert('Room edited successfully!');
    // Logic to edit room details
});

// Function to handle meeting organization (manager)
document.getElementById('organizeMeetingForm')?.addEventListener('submit', function(event) {
    event.preventDefault();
    alert('Meeting room booked successfully!');
    // Logic to deduct credits and book room
});
