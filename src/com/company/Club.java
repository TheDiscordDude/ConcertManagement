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

    // The list containing all the mebers.
    public List<Membre> getMembers() {
        return members;
    }

    private List<Membre> members;
    private List<ConcertListener> subscribers;
    private List<ConcertEvent> concertEvents;

    public List<ConcertEvent> getConcertEvents() {
        return concertEvents;
    }

    public Club(String nom, List<Membre> members) {
        this.name = nom;
        this.members = members;
        this.subscribers = new ArrayList<>();
        this.concertEvents = new ArrayList<>();
    }

    public Club(){

    }

    public Club(String nom) {
        this(nom, new ArrayList<>());
    }

    public void reserverBillet(Concert concert){
        for(ConcertEvent c : this.concertEvents){
            if(c.getConcert().equals(concert)){
                try{
                    concert.addReservation();
                    for(ConcertListener cl : this.subscribers){
                        cl.newTicket(c);
                    }
                } catch (FullRoomException e){
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    /**
     * Deletes a ticket
     * @param concert The concert we wish to
     */
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


    /**
     * This method adds a member to the club.
     * By default, a member is also a subscriber
     * @param m The member to be added
     */
    public void addMembre(Membre m){
        if(!this.members.contains(m)){
            this.members.add(m);
            this.addSubscriber(m);
        }

    }

    /**
     * Adds a subscriber to the club newLetter.
     * @param concertListener The subscriber we want to add
     */
    public void addSubscriber(ConcertListener concertListener){
        if(!this.subscribers.contains(concertListener)){
            this.subscribers.add(concertListener);
        }
    }

    /**
     * Removes a subscriber from the member list as well as the newLetter subscriber.
     * @param m The subscriber we want to remove
     */
    public void removeMember(Membre m){
        this.members.remove(m);
        this.subscribers.remove(m);
    }

    public void removeSubscriber(ConcertListener concertListener){
        this.subscribers.remove(concertListener);
    }

    /**
     * Adds a concert to the list.
     * It also sends a notification to all the subscribers
     * @param concert the concert we want to add
     */
    public void addConcert(Concert concert){
        ConcertEvent concertEvent = new ConcertEvent(this, concert);

        for(ConcertListener concertListener : this.subscribers){
            concertListener.newConcertEvent(concertEvent);
        }
        this.concertEvents.add(concertEvent);
    }

    /**
     * Cancels the concert passed as parameter and
     * sends a notification to all subscribers.
     * @param concert The concert we want to cancel
     */
    public void cancelConcert(ConcertEvent concert){
        this.concertEvents.remove(concert);

        for(ConcertListener c : this.subscribers){
            c.cancelConcertEvent(concert);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
