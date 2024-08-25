package com.demo.exceptions;

// Exception thrown when there is a database connection issue
public class DatabaseConnectionException extends RuntimeException {
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}