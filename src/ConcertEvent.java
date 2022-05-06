import java.util.EventObject;

public class ConcertEvent extends EventObject  {

    private Concert concert;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ConcertEvent(Object source, String name, String date, Double cost, Salle salle) {
        super(source);
        this.concert = new Concert(name, date, cost, salle);
    }

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
