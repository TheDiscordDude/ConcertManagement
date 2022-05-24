package com.company.ihm;

import com.company.listeners.ConcertListener;
import com.company.ui_elements.JTitle;
import com.company.Club;
import com.company.events.ConcertEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClubInfosIHM extends JPanel implements ListSelectionListener, ConcertListener, ActionListener {
    private JList<ConcertEvent> concertList;
    private DefaultListModel<ConcertEvent> concertEventDefaultListModel;
    private GridBagConstraints constraints = new GridBagConstraints();
    private Club club;
    public ClubInfosIHM(Club club){
        this.club = club;

        // We prefer using GridBagLayout to create panel
        this.setLayout(new GridBagLayout());

        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        this.constraints.ipady = 20;      //make this component tall
        this.constraints.weightx = 0.0;
        this.constraints.gridwidth = 3;
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;

        JTitle titre = new JTitle(club.getName());
        this.add(titre,this.constraints);

        // Displaying all then concerts :
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

        // Displaying the cancel button to cancel a concert
        JButton deleteButton = new JButton("Annuler le concert");
        deleteButton.addActionListener(this);
        this.constraints.gridy = 2;
        this.add(deleteButton, this.constraints);

        // We then display the number of member of the Club.
        this.constraints.gridy = 3;
        JLabel numberOfMembers = new JLabel("Je possède " + club.getMembers().size()+" abonnés");
        this.add(numberOfMembers, this.constraints);
    }

    /**
     * When we click on a concert in the UI, this method is called
     * It create an instance of ConcertInfosIHM containing the selected concert.
     * It then displays all the infos about the concert
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        for (Component c : this.getComponents()){
            if(c instanceof ConcertInfosIHM){
                this.remove(c);
            }
        }
        this.constraints.gridy = 4;
        if(this.concertList.getSelectedValue() != null)
            this.add(new ConcertInfosIHM(this.concertList.getSelectedValue().getConcert()), this.constraints);

        // We refresh the UI here
        this.revalidate();
        this.repaint();
    }


    /**
     * When a new concert is created, we update the UI and show the new Concert in the list
     * @param concertEvent The event we are sending with the source and the concert inside
     */
    @Override
    public void newConcertEvent(ConcertEvent concertEvent) {
        this.concertEventDefaultListModel.addElement(concertEvent);
    }

    /**
     * When a concert is canceled, we update the UI and remove the Concert from the list
     * @param concertEvent The concert we are canceling
     */
    @Override
    public void cancelConcertEvent(ConcertEvent concertEvent) {
        this.concertEventDefaultListModel.removeElement(concertEvent);


        this.revalidate();
        this.repaint();
    }

    /**
     * This method is used as a bridge between ConcertListeners :
     * All the "newTicket" received from the GlobalEventManager are passed down
     * to other Listeners.
     * @param concertEvent The event we are sending with the source and the concert inside
     */
    @Override
    public void newTicket(ConcertEvent concertEvent) {
        for(Component component : this.getComponents()){
            if(component instanceof ConcertListener){
                ((ConcertListener)component).newTicket(concertEvent);
            }
        }
    }

    /**
     * This method is used as a bridge between ConcertListeners :
     * All the "ticketRemoved" received from the GlobalEventManager are passed down
     * to other Listeners.
     * @param concertEvent The event we are sending with the source and the concert inside
     */
    @Override
    public void ticketRemoved(ConcertEvent concertEvent) {
        for(Component component : this.getComponents()){
            if(component instanceof ConcertListener){
                ((ConcertListener)component).ticketRemoved(concertEvent);
            }
        }
    }


    /**
     * This method is called when the cancel concert button is clicked
     * The event is then relayed to the selected club
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ConcertEvent result = this.concertList.getSelectedValue();
        if(result != null){
            this.club.cancelConcert(result);
        }
    }
}
