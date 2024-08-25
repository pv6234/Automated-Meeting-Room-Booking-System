package com.demo.dao;

public class StorageFactory {
    public static RoomDao getInstance(){
        return new RoomDaoImpl();
    }
}