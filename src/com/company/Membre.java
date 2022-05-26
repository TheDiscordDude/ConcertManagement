package com.company;

import com.company.events.ConcertEvent;
import com.company.listeners.ConcertListener;

import java.util.ArrayList;
import java.util.List;

public class Membre implements ConcertListener {
    private final String nom;
    private final String prenom;
    private final double seuil;

    public double getSeuil() {
        return seuil;
    }

    private List<Concert> l_concert;

    private String available_ticket_str;

    private int compteur;


    public Membre(String nom, String prenom, double seuil) {
        this.nom = nom;
        this.prenom = prenom;
        this.seuil = seuil;
        this.l_concert=new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    /**
     *  This function allow to book a ticket
     *
     * @param concert The current concert
     * @param club  The current club
     */
    public void reserverBillet(Concert concert, Club club){
        club.reserverBillet(concert);
        maj_concert(concert,"add");
    }

    /**
     * This function allow to remove a ticket
     * @param concert the current concert
     * @param club The current club
     */
    public void annulerBillet(Concert concert, Club club){
        club.annulerBillet(concert);
        maj_concert(concert,"remove");
    }

    /**
     * This function allow to add or remove a concert from the list of concert for a Member if we buy or cancel a tick
     * and to check if the personn can or not buy a ticket
     * @param concert The current concert
     * @param order This param tell to the function if we have to add or remove a concert from the list of concert
     */
    public void maj_concert(Concert concert,String order){

        if(this.available_ticket_str.equals("0")){
            this.compteur+=1;
        }

        if(order.equals("add") ){
            if(this.compteur>=2){
                System.out.println("plus de billet");
            }else{

                this.l_concert.add(concert);
            }
        }else if (order.equals("remove")) {
            if(!this.l_concert.isEmpty()){
                this.compteur=0;
                this.l_concert.remove(concert);
            }

        }
    }

    @Override

    public void newConcertEvent(ConcertEvent concertEvent) {
        if(concertEvent.getConcert().getCost() > this.seuil){
            System.out.println("Trop CHER !");

        }
        else{
            System.out.println("Je peux y aller !");

        }


    }



    @Override

    public void canceledConcertEvent(ConcertEvent concertEvent) {
        while(this.l_concert.contains(concertEvent.getConcert())){
            this.l_concert.remove(concertEvent.getConcert());
        }
    }

    public List<Concert> getL_concert() {
        return l_concert;
    }

    @Override
    public void newTicket(ConcertEvent concertEvent) {
        System.out.println("'"+concertEvent.getConcert().getName() + "' a été reservé, il reste : " + concertEvent.getConcert().availableTickets() + " billets disponible(s)");
        this.available_ticket_str=""+concertEvent.getConcert().availableTickets();
    }



    @Override
    public void ticketRemoved(ConcertEvent concertEvent) {
        System.out.println("Un nouveau billet est disponible pour : '"+concertEvent.getConcert().getName() + "', ainsi il reste : " + concertEvent.getConcert().availableTickets() + " billets disponible(s)");
    }

    @Override
    public String toString() {
        return nom + ' ' + prenom;
    }
}
