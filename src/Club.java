import java.util.ArrayList;
import java.util.List;

public class Club {
    private String name;
    private List<ConcertListener> members;
    private List<ConcertEvent> concertEvents;

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
                } catch (SallePleineException e){
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
                } catch (SalleVideException e){
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
        if (this.members.contains(m))
            this.members.remove(m);
    }

    public void addConcert(Concert concert){
        for(ConcertListener concertListener : this.members){
            ConcertEvent concertEvent = new ConcertEvent(this, concert);
            concertListener.newConcertEvent(concertEvent);
            this.concertEvents.add(concertEvent);
        }
    }

}
