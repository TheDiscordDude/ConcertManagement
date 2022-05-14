package com.company.events;

import com.company.Concert;

import java.util.EventObject;

public class ConcertEvent extends EventObject  {

    private final Concert concert;

    public ConcertEvent(Object source, Concert concert) {
        super(source);
        this.concert = concert;
    }

    public Concert getConcert() {
        return concert;
    }

    @Override
    public String toString() {
        return this.concert.toString();
    }
}
