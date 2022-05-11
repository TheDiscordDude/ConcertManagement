import Exceptions.SallePleineException;
import Exceptions.SalleVideException;

public class Concert {

    private final String name;
    private String date;
    private double cost;
    private Salle salle;

    public Concert(String name, String date, double cost, Salle salle) {
        this.name = name;
        this.date = date;
        this.cost = cost;
        this.salle = salle;
    }

    public void addReservation() throws SallePleineException {
        this.salle.addReservation();
    }

    public void annulerReservation() throws SalleVideException {
        this.salle.annulerReservation();
    }

    public int availableTickets(){
        return this.salle.availableTickets();
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " " + date + " " + salle ;
    }
}
