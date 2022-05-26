package com.company;

import com.company.exceptions.RoomTakenException;

import java.util.ArrayList;

public class RoomManager {

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

    /**
     * This is called when someone is trying to reserve a room
     * @param room The room
     * @throws RoomTakenException This exception means that the room you are trying to reserve is already taken
     */

    public void reserveRoom(Room room) throws RoomTakenException {
        if(this.rooms.contains(room)){
            this.rooms.remove(room);
            this.reservedRooms.add(room);
        }
        else {
            throw new RoomTakenException("The room : " + room + "is already taken");
        }
    }

    /**
     * This is called when someone leaves a room available for use
     * @param room The room
     */
    public void freeTheRoom(Room room) {
        this.reservedRooms.remove(room);
        this.rooms.add(room);
    }
}
