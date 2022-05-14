package com.company.events;

import com.company.Room;

import java.util.EventObject;

public class RoomEvent extends EventObject {
    private final Room room;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public RoomEvent(Object source, Room room) {
        super(source);
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return  room.toString() ;
    }
}
