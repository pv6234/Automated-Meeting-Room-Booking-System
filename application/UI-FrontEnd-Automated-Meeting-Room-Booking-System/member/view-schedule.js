// Sample data for meetings
const meetings = [
    { 
        room: 'Conference Room', 
        date: '2024-09-05', 
        time: '10:00', 
        duration: '2 hours', 
        assignedBy: 'John Doe', 
        context: 'Quarterly financial review meeting to discuss Q2 results and forecast for Q3.'
    },
    { 
        room: 'Meeting Room', 
        date: '2024-09-06', 
        time: '13:00', 
        duration: '1.5 hours', 
        assignedBy: 'Jane Smith', 
        context: 'Team brainstorming session on new product development and market strategies.'
    },
    { 
        room: 'Seminar Room', 
        date: '2024-09-07', 
        time: '15:00', 
        duration: '1 hour', 
        assignedBy: 'Alice Johnson', 
        context: 'Client meeting to discuss the requirements for the upcoming project delivery.'
    }
];

// Function to display the meeting schedule in flip cards
function displaySchedule() {
    const scheduleContainer = document.getElementById('scheduleContainer');
    scheduleContainer.innerHTML = ''; 

    meetings.forEach(meeting => {
        const flipCard = document.createElement('div');
        flipCard.className = 'flip-card';

        const flipCardInner = document.createElement('div');
        flipCardInner.className = 'flip-card-inner';

        const flipCardFront = document.createElement('div');
        flipCardFront.className = 'flip-card-front';
        flipCardFront.innerHTML = `
            <h3>${meeting.room}</h3>
            <p><strong>Date:</strong> ${meeting.date}</p>
            <p><strong>Time:</strong> ${meeting.time}</p>
            <p><strong>Duration:</strong> ${meeting.duration}</p>
        `;

        const flipCardBack = document.createElement('div');
        flipCardBack.className = 'flip-card-back';
        flipCardBack.innerHTML = `
           
            <p><strong>Assigned By:</strong> ${meeting.assignedBy}</p>
            <p><strong>Context:</strong> ${meeting.context}</p>
        `;

        flipCardInner.appendChild(flipCardFront);
        flipCardInner.appendChild(flipCardBack);
        flipCard.appendChild(flipCardInner);
        scheduleContainer.appendChild(flipCard);
    });
}


document.addEventListener('DOMContentLoaded', function() {
    displaySchedule();
});
