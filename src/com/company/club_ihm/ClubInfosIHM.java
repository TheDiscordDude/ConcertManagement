package com.company.club_ihm;

import com.company.listeners.ConcertListener;
import com.company.ui_elements.JTitle;
import com.company.Club;
import com.company.events.ConcertEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class ClubInfosIHM extends JPanel implements ListSelectionListener, ConcertListener {
    private JList<ConcertEvent> concertList;
    private DefaultListModel<ConcertEvent> concertEventDefaultListModel;
    private GridBagConstraints constraints = new GridBagConstraints();
    public ClubInfosIHM(Club club){
        this.setLayout(new GridBagLayout());

        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        this.constraints.ipady = 20;      //make this component tall
        this.constraints.weightx = 0.0;
        this.constraints.gridwidth = 3;
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        JTitle titre = new JTitle(club.getName());
        this.add(titre,this.constraints);


        List<ConcertEvent> concertEventList = club.getConcertEvents();
        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        this.constraints.gridx = 0;
        this.constraints.gridy = 1;
        this.constraints.ipady = 10;

        this.concertEventDefaultListModel = new DefaultListModel<>();
        this.concertEventDefaultListModel.addAll(concertEventList);
        this.concertList = new JList<>(concertEventDefaultListModel);
        this.concertList.addListSelectionListener(this);
        this.add(this.concertList, this.constraints);

        this.constraints.gridy = 2;
        JLabel numberOfMembers = new JLabel("Je possède " + club.getMembers().size()+" abonnés");
        this.add(numberOfMembers, this.constraints);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        for (Component c : this.getComponents()){
            if(c instanceof ConcertInfosIHM){
                this.remove(c);
            }
        }
        this.constraints.gridy++;
        this.add(new ConcertInfosIHM(this.concertList.getSelectedValue().getConcert()), this.constraints);

        this.revalidate();
        this.repaint();
    }

    @Override
    public void newConcertEvent(ConcertEvent concertEvent) {
        this.concertEventDefaultListModel.addElement(concertEvent);
    }

    @Override
    public void newTicket(ConcertEvent concertEvent) {

    }

    @Override
    public void ticketRemoved(ConcertEvent concertEvent) {

    }
}
