package com.company;

import java.util.ArrayList;
import java.util.List;

public class Membre implements ConcertListener {
    private String nom;
    private String prenom;
    private double seuil;

    private List<Concert> l_concert;

    private String available_ticket_str;

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

    public Membre(){}

    public void reserverBillet(Concert concert, Club club){
        club.reserverBillet(concert);
        maj_concert(concert,"add");
    }

    public void annulerBillet(Concert concert, Club club){
        club.annulerBillet(concert);
        maj_concert(concert,"remove");
    }

    public void maj_concert(Concert concert,String order){

        if(order.equals("add") ){
            this.l_concert.add(concert);
        } else if (order.equals("remove")) {
            if(this.l_concert.isEmpty()){
                System .out.println("pas de concert");
            }else{
                this.l_concert.remove(concert);
            }

        }
    }

    @Override
    public void newConcertEvent(ConcertEvent concertEvent) {
        if(concertEvent.getConcert().getCost() > this.seuil)
            System.out.println("Trop CHER !");
        else
            System.out.println("Je peux y aller !");
    }

    public List<Concert> getL_concert() {
        return l_concert;
    }

    @Override
    public void newTicket(ConcertEvent concertEvent) {
        System.out.println("'"+concertEvent.getConcert().getName() + "' a été reservé, il reste : " + concertEvent.getConcert().availableTickets() + " billets disponible(s)");
        this.available_ticket_str="'"+concertEvent.getConcert().getName() + "' a été reservé, il reste : " + concertEvent.getConcert().availableTickets() + " billets disponible(s)";
    }

    public String getAvailable_ticket_str() {
        return available_ticket_str;
    }

    @Override
    public void ticketRemoved(ConcertEvent concertEvent) {
        System.out.println("Un nouveau billet est disponible pour : '"+concertEvent.getConcert().getName() + "', ainsi il reste : " + concertEvent.getConcert().availableTickets() + " billets disponible(s)");
    }
}