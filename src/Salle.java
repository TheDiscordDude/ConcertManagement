public class Salle {
    private String name;
    private int capacity;
    private int reservedPlaces;


    public Salle(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.reservedPlaces = 0;
    }

    public void addReservation() throws SallePleineException{
        if(this.reservedPlaces == this.capacity)
            throw new SallePleineException("La salle : '" + this.name + "' est pleine");
        this.reservedPlaces += 1;
    }

    public void annulerReservation() throws SalleVideException{
        if(this.reservedPlaces == 0)
            throw new SalleVideException("La salle : '" + this.name + "' est vide");
        this.reservedPlaces -= 1;
    }

    public int availableTickets(){
        return this.capacity - this.reservedPlaces;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
