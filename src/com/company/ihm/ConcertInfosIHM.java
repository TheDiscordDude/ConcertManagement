package com.company.ihm;

import com.company.Concert;
import com.company.events.ConcertEvent;
import com.company.listeners.ConcertListener;

import javax.swing.*;
import java.awt.*;

public class ConcertInfosIHM extends JPanel implements ConcertListener {
    private Concert concert;
    private JLabel ticketLabel;
    public ConcertInfosIHM(Concert concert){
        this.concert = concert;

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
        this.ticketLabel = new JLabel("Il y a " + concert.availableTickets() + " place(s) disponible(s)");
        this.add(ticketLabel,c);
    }

    @Override
    public void newConcertEvent(ConcertEvent concertEvent) {
    }

    @Override
    public void cancelConcertEvent(ConcertEvent concertEvent) {

    }

    /**
     * When a new ticket is bought, we update the display
     * @param concertEvent The event we are sending with the source and the concert inside
     */
    @Override
    public void newTicket(ConcertEvent concertEvent) {
        if (concertEvent.getConcert() == this.concert){
            this.ticketLabel.setText("Il y a " + concertEvent.getConcert().availableTickets() + " place(s) disponible(s)");

            this.revalidate();
            this.repaint();
        }
    }

    /**
     * When a new ticket is canceled, we update the display
     * @param concertEvent The event we are sending with the source and the concert inside
     */
    @Override
    public void ticketRemoved(ConcertEvent concertEvent) {
        if (concertEvent.getConcert() == this.concert){
            this.ticketLabel.setText("Il y a " + concertEvent.getConcert().availableTickets() + " place(s) disponible(s)");

            this.revalidate();
            this.repaint();
        }
    }
}
