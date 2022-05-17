package com.company;

import com.company.exceptions.EmptyRoomException;
import com.company.exceptions.FullRoomException;

public class Room {
    private final String name;
    private final int capacity;
    private int reservedPlaces;


    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.reservedPlaces = 0;
    }

    public void addReservation() throws FullRoomException {
        if(this.reservedPlaces == this.capacity)
            throw new FullRoomException("La salle : '" + this.name + "' est pleine");
        this.reservedPlaces += 1;
    }

    public void annulerReservation() throws EmptyRoomException {
        if(this.reservedPlaces == 0)
            throw new EmptyRoomException("La salle : '" + this.name + "' est vide");
        this.reservedPlaces -= 1;
    }

    public int availableTickets(){
        return this.capacity - this.reservedPlaces;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Room)){
            return false;
        }
        return this.name.equals(((Room)obj).name);
    }
}
