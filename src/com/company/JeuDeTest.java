package com.company;

import com.company.ihm.ClubIHM;
import com.company.ihm.Membre_IHM;
import com.company.events.RoomEvent;
import com.company.exceptions.RoomTakenException;
import com.company.listeners.ConcertListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JeuDeTest {
    public static void main(String[] args) {
        /*Membre m1 = new Membre("DASSEUX", "Damien", 4.0);
        Membre m2 = new Membre("MALACARNE", "Etienne", 10.0);

        Room s1 = new Room("s1", 10);

        Club b1 = new Club("PolyBot");

        b1.addMembre(m1);
        b1.addMembre(m2);

        Concert concert = new Concert("concert Orelsan", Calendar.getInstance().getTime(), 5.0, s1);
        Concert concert2 = new Concert("concert Twenty one pilots", Calendar.getInstance().getTime(), 15.0, s1);
        Concert concert3 = new Concert("concert Eminem", Calendar.getInstance().getTime(), 35.0, s1);

        b1.addConcert(concert);
        b1.addConcert(concert2);
        b1.addConcert(concert3);


        m1.reserverBillet(concert, b1);
        //m1.reserverBillet(concert2, b1);
        //m2.reserverBillet(concert,b1);
        m2.reserverBillet(concert3, b1);



        List<Membre> l_m=new ArrayList<>();
        l_m.add(m1);
        l_m.add(m2);

        List<Concert> l_c=new ArrayList<>();
        l_c.add(concert);
        l_c.add(concert2);
        l_c.add(concert3);
        Membre_IHM me1=new Membre_IHM(l_m,b1,l_c);*/

        Membre m1 = new Membre("DASSEUX", "Damien", 4.0);
        Membre m2 = new Membre("MALACARNE", "Etienne", 10.0);

        Club b1 = new Club("PolyBot");
        b1.addMembre(m1);
        b1.addMembre(m2);

        Room s1 = new Room("s1", 5);
        Room s2 = new Room("s2", 7);
        Room s3 = new Room("s3", 9);
        Room s4 = new Room("s4", 15);
        Room s5 = new Room("s5", 20);

        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(s1);
        rooms.add(s2);
        rooms.add(s3);
        rooms.add(s4);
        rooms.add(s5);

        RoomManager gestionnaireSalle = new RoomManager(rooms);

        Concert concert = null;
        try {
            gestionnaireSalle.reserveRoom(new RoomEvent(new Object(), s1));
            concert = new Concert("MonConcertCool", Calendar.getInstance().getTime(), 5.0, s1);
            b1.addConcert(concert);

        } catch (RoomTakenException e) {
            System.out.println("Cette salle est déja utilisé");
        }

        try {
            gestionnaireSalle.reserveRoom(new RoomEvent(new Object(), s1));
            concert = new Concert("MonConcertCool2", Calendar.getInstance().getTime(), 5.0, s1);
            b1.addConcert(concert);

        } catch (RoomTakenException e) {
            System.out.println("Cette salle est déja utilisé");
        }

        Concert concert2 = null;
        try {
            gestionnaireSalle.reserveRoom(new RoomEvent(new Object(), s2));
            concert2 = new Concert("MoinsCool", Calendar.getInstance().getTime(), 5.0, s2);
            b1.addConcert(concert2);
        } catch (RoomTakenException e) {
            System.out.println("Cette salle est déja utilisé");
        }



        Club b2 = new Club("CaraList");
        Club b3 = new Club("Les Sigmas");
        ArrayList<Club> clubs = new ArrayList<>();
        clubs.add(b1);
        clubs.add(b2);
        clubs.add(b3);


        m1.reserverBillet(concert,b1);
        m2.reserverBillet(concert2,b2);

        List<Concert> l_c=new ArrayList<>();
        l_c.add(concert);
        l_c.add(concert2);

        ClubIHM clubIHM=new ClubIHM(clubs, gestionnaireSalle);

        Membre_IHM membre_ihm=new Membre_IHM(clubs,l_c);

        List<ConcertListener> concertListeners = new ArrayList<>();
        concertListeners.add(clubIHM);
        concertListeners.add(membre_ihm);
        GlobalEventManager globalEventManager = new GlobalEventManager(concertListeners);

        b1.addSubscriber(globalEventManager);
        b2.addSubscriber(globalEventManager);
        b3.addSubscriber(globalEventManager);

    }
}
