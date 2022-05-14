package com.company.exceptions;

public class RoomTakenException extends Exception{
    public RoomTakenException(String message){
        super(message);
    }
}
