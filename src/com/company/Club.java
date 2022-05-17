package com.company;

import com.company.events.ConcertEvent;
import com.company.exceptions.FullRoomException;
import com.company.exceptions.EmptyRoomException;
import com.company.listeners.ConcertListener;

import java.util.ArrayList;
import java.util.List;

public class Club {
    public String getName() {
        return name;
    }

    private String name;

    public List<ConcertListener> getMembers() {
        return members;
    }

    private List<ConcertListener> members;
    private List<ConcertEvent> concertEvents;

    public List<ConcertEvent> getConcertEvents() {
        return concertEvents;
    }

    public void setConcertEvents(List<ConcertEvent> concertEvents) {
        this.concertEvents = concertEvents;
    }

    public Club(String nom, List<ConcertListener> members) {
        this.name = nom;
        this.members = members;
        this.concertEvents = new ArrayList<>();
    }

    public Club(String nom) {
        this(nom, new ArrayList<>());
    }

    public void reserverBillet(Concert concert){
        for(ConcertEvent c : this.concertEvents){
            if(c.getConcert().equals(concert)){
                try{
                    concert.addReservation();
                    for(ConcertListener cl : this.members){
                        cl.newTicket(c);
                    }
                } catch (FullRoomException e){
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    public void annulerBillet(Concert concert){
        for(ConcertEvent c : this.concertEvents){
            if(c.getConcert().equals(concert)){
                try{
                    concert.annulerReservation();
                    for(ConcertListener cl : this.members){
                        cl.ticketRemoved(c);
                    }
                } catch (EmptyRoomException e){
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    public void addMembre(Membre m){
        if(!this.members.contains(m))
            this.members.add(m);
    }

    public void removeMember(Membre m){
        this.members.remove(m);
    }

    public void addConcert(Concert concert){
        ConcertEvent concertEvent = new ConcertEvent(this, concert);

        for(ConcertListener concertListener : this.members){
            concertListener.newConcertEvent(concertEvent);
        }
        this.concertEvents.add(concertEvent);
    }


    @Override
    public String toString() {
        return name;
    }
}
