package com.company;

import com.company.exceptions.FullRoomException;
import com.company.exceptions.EmptyRoomException;

import java.util.Date;

public class Concert {

    private final String name;
    private Date date;
    private double cost;
    private Room salle;

    public Concert(String name, Date date, double cost, Room salle) {
        this.name = name;
        this.date = date;
        this.cost = cost;
        this.salle = salle;
    }

    public void addReservation() throws FullRoomException {
        this.salle.addReservation();
    }

    public void annulerReservation() throws EmptyRoomException {
        this.salle.annulerReservation();
    }

    public int availableTickets(){
        return this.salle.availableTickets();
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public double getCost() {
        return this.cost;
    }

    public Room getRoom() {
        return this.salle;
    }

    @Override
    public String toString() {
        return name + " " + salle ;
    }
}
