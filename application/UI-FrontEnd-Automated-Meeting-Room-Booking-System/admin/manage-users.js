// Sample user data (for demo purposes)
const users = [
    { name: 'John Doe', email: 'john.doe@example.com', phone: '123-456-7890', role: 'Manager' },
    { name: 'Jane Smith', email: 'jane.smith@example.com', phone: '987-654-3210', role: 'Member' }
];

// Function to initialize the user list
function initializeUserList() {
    const userList = document.getElementById('userList');
    userList.innerHTML = ''; 

    users.forEach(user => {
        const userCard = document.createElement('div');
        userCard.classList.add('user-card');
        userCard.innerHTML = `
            <h3>${user.name}</h3>
            <p>Email: ${user.email}</p>
            <p>Phone: ${user.phone}</p>
            <p>Role: ${user.role}</p>
            <button onclick="editUser('${user.name}')">Edit</button>
        `;
        userList.appendChild(userCard);
    });
}

// Function to search for a user by name
function searchUser() {
    const searchUserName = document.getElementById('searchUserName').value;
    const user = users.find(user => user.name === searchUserName);

    if (user) {
        document.getElementById('userDetails').style.display = 'block';
        document.getElementById('newUserName').value = user.name;
        document.getElementById('newUserEmail').value = user.email;
        document.getElementById('newUserPhone').value = user.phone;
        document.getElementById('newUserRole').value = user.role;
    } else {
        alert('User not found.');
        document.getElementById('userDetails').style.display = 'none';
    }
}

// Function to update user details
function updateUser() {
    const newUserName = document.getElementById('newUserName').value;
    const userIndex = users.findIndex(user => user.name === newUserName);

    if (userIndex !== -1) {
        const newEmail = document.getElementById('newUserEmail').value;
        const newPhone = document.getElementById('newUserPhone').value;
        const newRole = document.getElementById('newUserRole').value;

        users[userIndex] = {
            name: newUserName,
            email: newEmail,
            phone: newPhone,
            role: newRole
        };

        initializeUserList();
        clearForm(); 
    } else {
        alert('User not found.');
    }
}

// Function to edit user 
function editUser(userName) {
    const user = users.find(user => user.name === userName);

    if (user) {
        document.getElementById('userDetails').style.display = 'block';
        document.getElementById('newUserName').value = user.name;
        document.getElementById('newUserEmail').value = user.email;
        document.getElementById('newUserPhone').value = user.phone;
        document.getElementById('newUserRole').value = user.role;
    }
}

function clearForm() {
    document.getElementById('updateUserForm').reset();
    document.getElementById('userDetails').style.display = 'none';
}


window.onload = initializeUserList;
