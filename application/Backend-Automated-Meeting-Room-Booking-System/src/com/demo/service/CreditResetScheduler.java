package com.demo.service;

import com.demo.dao.ManagerCreditsDao;
import com.demo.dao.ManagerCreditsDaoImpl;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CreditResetScheduler {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) {
        scheduleWeeklyTask();
    }

    public static void scheduleWeeklyTask() {
        Runnable resetCreditsTask = () -> {
            ManagerCreditsDao dao = new ManagerCreditsDaoImpl();
            dao.resetAllManagerCredits(2000);
            System.out.println("Manager credits reset to 2000 for all managers.");
        };

        long initialDelay = calculateInitialDelay();
        long period = TimeUnit.DAYS.toMillis(7);

        scheduler.scheduleAtFixedRate(resetCreditsTask, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    private static long calculateInitialDelay() {
        Calendar nextMonday = Calendar.getInstance();
        nextMonday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        nextMonday.set(Calendar.HOUR_OF_DAY, 6);
        nextMonday.set(Calendar.MINUTE, 0);
        nextMonday.set(Calendar.SECOND, 0);
        nextMonday.set(Calendar.MILLISECOND, 0);

        // If today is Monday after 6 AM, schedule for next Monday
        if (Calendar.getInstance().after(nextMonday)) {
            nextMonday.add(Calendar.WEEK_OF_YEAR, 1);
        }

        return nextMonday.getTimeInMillis() - System.currentTimeMillis();
    }
}
