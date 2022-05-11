import Exceptions.SallePleineException;
import Exceptions.SalleVideException;

import java.util.Date;

public class Concert {

    private final String name;
    private Date date;
    private double cost;
    private Salle salle;

    public Concert(String name, Date date, double cost, Salle salle) {
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

    public Date getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " " + salle ;
    }
}
