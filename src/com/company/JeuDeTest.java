package com.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JeuDeTest {
    public static void main(String[] args) {
        Membre m1 = new Membre("DASSEUX", "Damien", 4.0);
        Membre m2 = new Membre("MALACARNE", "Etienne", 10.0);

        Salle s1 = new Salle("s1", 10);

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
        Membre_IHM me1=new Membre_IHM(l_m,b1,l_c);



    }
}
