// Dummy data
const meetings = [
    { room: 'Conference Room', date: '2024-09-05', time: '10:00', duration: '2 hours' },
    { room: 'Meeting Room', date: '2024-09-06', time: '13:00', duration: '1.5 hours' },
    { room: 'Seminar Room', date: '2024-09-07', time: '15:00', duration: '1 hour' }
];

function displaySchedule() {
    const scheduleList = document.getElementById('scheduleList');
    scheduleList.innerHTML = '';

    meetings.forEach(meeting => {
        const scheduleItem = document.createElement('div');
        scheduleItem.className = 'schedule-item';
        scheduleItem.innerHTML = `
            <div>
                <strong>Room:</strong> ${meeting.room} <br>
                <strong>Date:</strong> ${meeting.date} <br>
                <strong>Time:</strong> ${meeting.time} <br>
                <strong>Duration:</strong> ${meeting.duration}
            </div>
        `;
        scheduleList.appendChild(scheduleItem);
    });
}


document.addEventListener('DOMContentLoaded', function() {
    displaySchedule();
});
