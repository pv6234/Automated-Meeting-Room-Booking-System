package com.hsbc.dao;

import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class TestDBUtil {

    @Test
    void testGetMyConnection() {
        Connection conn = DBUtil.getMyConnection();
        assertNotNull(conn);
    }

    @Test
    void testCloseMyConnection() {
        Connection conn = DBUtil.getMyConnection();
        assertDoesNotThrow(DBUtil::closeMyConnection);
    }
}

