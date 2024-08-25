package com.hsbc.service;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class TestCreditResetScheduler {

    @Test
    void testScheduleWeeklyTask() {
        ManagerCreditsDao mockDao = mock(ManagerCreditsDao.class);
        CreditResetScheduler.scheduleWeeklyTask();

        verify(mockDao, times(1)).resetAllManagerCredits(2000);
    }
}

