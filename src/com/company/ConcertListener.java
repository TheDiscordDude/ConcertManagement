package com.company;

/**
 * Allows communication between the generator and the receptor of Concert
 */
public interface ConcertListener {
    /**
     * Is called when a new concert is created ether in the UI or in backend
     * @param concertEvent The event we are sending with the source and the concert inside
     */
    void newConcertEvent(ConcertEvent concertEvent);

    /**
     * Is called when someone gets a ticket for a concert
     * @param concertEvent The event we are sending with the source and the concert inside
     */
    void newTicket(ConcertEvent concertEvent);

    /**
     * Is called when someone cancels their ticket for a concert
     * @param concertEvent The event we are sending with the source and the concert inside
     */
    void ticketRemoved(ConcertEvent concertEvent);
}
