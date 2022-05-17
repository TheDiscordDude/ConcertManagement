package com.company;

import com.company.events.RoomEvent;
import com.company.exceptions.RoomTakenException;
import com.company.listeners.RoomListener;

import java.util.ArrayList;

public class RoomManager implements RoomListener {
    private ArrayList<Room> rooms;
    private ArrayList<Room> reservedRooms;

    public RoomManager(ArrayList<Room> rooms) {
        this.rooms = rooms;
        this.reservedRooms = new ArrayList<>();
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room r){
        if(!this.rooms.contains(r) && !this.reservedRooms.contains(r)){
            this.rooms.add(r);
        }
    }

    @Override
    public void reserveRoom(RoomEvent salleEvent) throws RoomTakenException {
        if(this.rooms.contains(salleEvent.getRoom())){
            this.rooms.remove(salleEvent.getRoom());
            this.reservedRooms.add(salleEvent.getRoom());
        }
        else {
            throw new RoomTakenException("The room : " + salleEvent.toString() + "is already taken");
        }
    }

    @Override
    public void roomAvailable(RoomEvent salleEvent) {
        this.reservedRooms.remove(salleEvent.getRoom());
        this.rooms.add(salleEvent.getRoom());
    }
}
