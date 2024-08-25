package com.demo.dao;

public interface ManagerCreditsDao {

    void resetAllManagerCredits(int credits);

    void updateCredits(int mgrCredits, String username);

    int getMgrCredits(String username);
}
