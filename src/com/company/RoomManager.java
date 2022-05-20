package com.company;

import com.company.events.RoomEvent;
import com.company.exceptions.RoomTakenException;
import com.company.listeners.RoomListener;

import java.util.ArrayList;

public class RoomManager implements RoomListener {

    // This list will contain all the UNOCCUPIED rooms
    private ArrayList<Room> rooms;

    // This list will contain all the reserved rooms
    private ArrayList<Room> reservedRooms;

    public RoomManager(ArrayList<Room> rooms) {
        this.rooms = rooms;
        this.reservedRooms = new ArrayList<>();
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * This method add the new room as an available room
     * @param r represents the room we want to add to the list
     */
    public void addRoom(Room r){
        if(!this.rooms.contains(r) && !this.reservedRooms.contains(r)){
            this.rooms.add(r);
        }
    }

    @Override
    public void reserveRoom(RoomEvent roomEvent) throws RoomTakenException {
        if(this.rooms.contains(roomEvent.getRoom())){
            this.rooms.remove(roomEvent.getRoom());
            this.reservedRooms.add(roomEvent.getRoom());
        }
        else {
            throw new RoomTakenException("The room : " + roomEvent + "is already taken");
        }
    }

    @Override
    public void roomAvailable(RoomEvent roomEvent) {
        this.reservedRooms.remove(roomEvent.getRoom());
        this.rooms.add(roomEvent.getRoom());
    }
}
