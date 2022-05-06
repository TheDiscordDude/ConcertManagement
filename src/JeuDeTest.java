public class JeuDeTest {
    public static void main(String[] args) {
        Membre m1 = new Membre("DASSEUX", "Damien", 4.0);
        Membre m2 = new Membre("MALACARNE", "Etienne", 10.0);

        Salle s1 = new Salle("s1", 1);

        Club b1 = new Club("PolyBot");

        b1.addMembre(m1);
        b1.addMembre(m2);

        Concert concert = new Concert("concert", "02/08/2022", 5.0, s1);
        b1.addConcert(concert);

        m1.annulerBillet(concert, b1);
        m1.reserverBillet(concert, b1);
        m1.reserverBillet(concert, b1);
        m1.annulerBillet(concert, b1);


    }
}
