package com.company;

import com.company.events.ConcertEvent;
import com.company.listeners.ConcertListener;

import java.util.List;

public class GlobalEventManager implements ConcertListener {
    private List<ConcertListener> listeners;

    public GlobalEventManager(List<ConcertListener> listeners) {
        this.listeners = listeners;
    }

    @Override
    public void newConcertEvent(ConcertEvent concertEvent) {
        for(ConcertListener c : this.listeners){
            c.newConcertEvent(concertEvent);
        }
    }

    @Override
    public void newTicket(ConcertEvent concertEvent) {
        for(ConcertListener c : this.listeners){
            c.newTicket(concertEvent);
        }
    }

    @Override
    public void ticketRemoved(ConcertEvent concertEvent) {
        for(ConcertListener c : this.listeners){
            c.ticketRemoved(concertEvent);
        }
    }
}
