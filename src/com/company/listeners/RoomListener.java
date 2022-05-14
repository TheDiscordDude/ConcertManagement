package com.company.listeners;

import com.company.events.RoomEvent;
import com.company.exceptions.RoomTakenException;

public interface RoomListener {
    void reserveRoom(RoomEvent salleEvent) throws RoomTakenException;
    void roomAvailable(RoomEvent salleEvent);
}
