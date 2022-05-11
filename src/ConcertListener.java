public interface ConcertListener {
    void newConcertEvent(ConcertEvent concertEvent);
    void newTicket(ConcertEvent concert);
    void ticketRemoved(ConcertEvent concertEvent);
}
