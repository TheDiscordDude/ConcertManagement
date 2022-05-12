package com.company.club_ihm;

import com.company.Concert;

import javax.swing.*;
import java.awt.*;

public class ConcertInfosIHM extends JPanel {
    public ConcertInfosIHM(Concert concert){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        JLabel nameLabel = new JLabel("Nom du concert : "+concert.getName());
        this.add(nameLabel,c);

        c.gridy = 1;
        JLabel dateLabel = new JLabel("Date : " + concert.getDate());
        this.add(dateLabel,c);

        c.gridy = 2;
        JLabel costsLabel = new JLabel("L'accès au concert coute : " + concert.getCost() + " euros");
        this.add(costsLabel,c);

        c.gridy = 3;
        JLabel ticketLabel = new JLabel("Il y a " + concert.availableTickets() + " place(s) disponible(s)");
        this.add(ticketLabel,c);

    }
}
