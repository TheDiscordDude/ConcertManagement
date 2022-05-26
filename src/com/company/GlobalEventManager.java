package com.company;

import com.company.events.ConcertEvent;
import com.company.listeners.ConcertListener;

import java.util.List;

/**
 * This class is used as bridge between the member UI and the Club UI.
 * All the concert Events happening in one side are also called in the other side
 */
public class GlobalEventManager implements ConcertListener {
    private List<ConcertListener> listeners;

    public GlobalEventManager(List<ConcertListener> listeners) {
        this.listeners = listeners;
    }

    /**
     * This method is used as a bridge between ConcertListeners :
     * All the "newConcertEvent" received from the GlobalEventManager are passed down
     * to other Listeners.
     * @param concertEvent The event we are sending with the source and the concert inside
     */
    @Override
    public void newConcertEvent(ConcertEvent concertEvent) {
        for(ConcertListener c : this.listeners){
            c.newConcertEvent(concertEvent);
        }
    }

    /**
     * This method is used as a bridge between ConcertListeners :
     * All the "cancelConcertEvent" received from the GlobalEventManager are passed down
     * to other Listeners.
     * @param concertEvent The concert we are canceling
     */
    @Override
    public void canceledConcertEvent(ConcertEvent concertEvent) {
        for(ConcertListener c : this.listeners){
            c.canceledConcertEvent(concertEvent);
        }
    }

    /**
     * This method is used as a bridge between ConcertListeners :
     * All the "newTicket" received from the GlobalEventManager are passed down
     * to other Listeners.
     * @param concertEvent The event we are sending with the source and the concert inside
     */
    @Override
    public void newTicket(ConcertEvent concertEvent) {
        for(ConcertListener c : this.listeners){
            c.newTicket(concertEvent);
        }
    }

    /**
     * This method is used as a bridge between ConcertListeners :
     * All the "ticketRemoved" received from the GlobalEventManager are passed down
     * to other Listeners.
     * @param concertEvent The event we are sending with the source and the concert inside
     */
    @Override
    public void ticketRemoved(ConcertEvent concertEvent) {
        for(ConcertListener c : this.listeners){
            c.ticketRemoved(concertEvent);
        }
    }
}
