package com.company.listeners;

import com.company.events.RoomEvent;
import com.company.exceptions.RoomTakenException;

public interface RoomListener {
    /**
     * This is called when someone is trying to reserve a room
     * @param roomEvent The event containing the room
     * @throws RoomTakenException This exception means that the room you are trying to reserve is already taken
     */
    void reserveRoom(RoomEvent roomEvent) throws RoomTakenException;

    /**
     * This is called when someone leaves a room available for use
     * @param roomEvent The event containing the room
     */
    void roomAvailable(RoomEvent roomEvent);
}
