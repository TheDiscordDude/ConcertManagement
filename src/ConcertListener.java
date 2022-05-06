public interface ConcertListener {
    public void newConcertEvent(ConcertEvent concertEvent);
    public void newTicket(ConcertEvent concert);
    public void ticketRemoved(ConcertEvent concertEvent);
}
