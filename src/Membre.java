public class Membre implements ConcertListener {
    private String nom;
    private String prenom;
    private double seuil;

    public Membre(String nom, String prenom, double seuil) {
        this.nom = nom;
        this.prenom = prenom;
        this.seuil = seuil;
    }

    public void reserverBillet(Concert concert, Club club){
        club.reserverBillet(concert);
    }

    public void annulerBillet(Concert concert, Club club){
        club.annulerBillet(concert);
    }

    @Override
    public void newConcertEvent(ConcertEvent concertEvent) {
        if(concertEvent.getConcert().getCost() > this.seuil)
            System.out.println("Trop CHER !");
        else
            System.out.println("Je peux y aller !");
    }

    @Override
    public void newTicket(ConcertEvent concertEvent) {
        System.out.println("'"+concertEvent.getConcert().getName() + "' a été reservé, il reste : " + concertEvent.getConcert().availableTickets() + " billets disponible(s)");
    }

    @Override
    public void ticketRemoved(ConcertEvent concertEvent) {
        System.out.println("Un nouveau billet est disponible pour : '"+concertEvent.getConcert().getName() + "', ainsi il reste : " + concertEvent.getConcert().availableTickets() + " billets disponible(s)");
    }
}
