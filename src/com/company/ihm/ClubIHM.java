package com.company.ihm;

import com.company.Club;
import com.company.Membre;
import com.company.RoomManager;
import com.company.events.ConcertEvent;
import com.company.events.RoomEvent;
import com.company.listeners.ConcertListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// todo : be able to  create a Club / Room
public class ClubIHM extends JFrame implements ActionListener, ConcertListener {
    private JMenuItem disconnectMenu;
    private Club selectedClub;
    private JMenuItem createConcertMenu;
    private JMenuItem createRoomMenu;
    private JMenuItem refreshMenu;
    private JMenuItem banMemberMenu;
    private JButton connectButton;
    private ArrayList<Club> clubs;
    private RoomManager manager;
    private GridBagConstraints constraints;

    public ClubIHM(ArrayList<Club> clubs, RoomManager manager){
        this.clubs = clubs;
        this.manager = manager;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(400, 600);
        this.setTitle("Club - Concert Management");

        Container container=getContentPane();
        container.setLayout(new GridBagLayout());
        this.constraints = new GridBagConstraints();

        this.createMenuBar();

        constraints.gridy = 0;
        this.connectButton = new JButton("Se connecter");
        this.connectButton.addActionListener(this);
        this.add(this.connectButton, constraints);

        this.setVisible(true);
    }

    /**
     * Creates the menu bar for the Frame
     */
    private void createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu clubMenu = new JMenu("Club");
        this.disconnectMenu = new JMenuItem("Déconnection");
        this.refreshMenu = new JMenuItem("Rafraïchir");
        this.refreshMenu.addActionListener(this);
        this.disconnectMenu.addActionListener(this);
        clubMenu.add(this.disconnectMenu);
        clubMenu.add(this.refreshMenu);

        JMenu concertMenu = new JMenu("Concert");
        this.createConcertMenu = new JMenuItem("Organiser un concert");
        this.createConcertMenu.addActionListener(this);
        concertMenu.add(this.createConcertMenu);

        JMenu roomMenu = new JMenu("Salles");
        this.createRoomMenu = new JMenuItem("Ajouter une salle");
        this.createRoomMenu.addActionListener(this);
        roomMenu.add(this.createRoomMenu);

        JMenu memberMenu = new JMenu("Membres");
        this.banMemberMenu = new JMenuItem("Bannir un membre");
        this.banMemberMenu.addActionListener(this);
        memberMenu.add(this.banMemberMenu);

        menuBar.add(clubMenu);
        menuBar.add(concertMenu);
        menuBar.add(roomMenu);
        menuBar.add(memberMenu);
        this.setJMenuBar(menuBar);
    }

    /** Here, we treat all the events coming from the menu bar and the connect button
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // We can't use switch because these are not basic types

        // I fwe click on the disconnect button, we call the disconnect method
        if(e.getSource().equals(this.disconnectMenu)){
            this.disconnect();
        }
        // If we click "Organiser un concert" on the menu bar, we display the concert form
        else if(e.getSource().equals(this.createConcertMenu)) {
            this.displayConcertForm();
        }
        // If we click on the connect button, we call the connect method
        else if(e.getSource().equals(this.connectButton)){
            this.connect();
        }
        // If we click "Ajouter une salle" in the Menu, we display the correct form
        else if(e.getSource().equals(this.createRoomMenu)){
            this.displayRoomForm();
        }
        // If we click "Rafraîchir" in the menu, we refresh the entire display
        else if(e.getSource().equals(this.refreshMenu)){
            this.refresh();
        }
        // If we click "Bannir un membre" in the menu, we display a new window to choose which user to ban
        else if(e.getSource().equals(this.banMemberMenu)) {
            this.banMember();
        }

        // We then refresh the UI
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * This method is used to display a window and ban the chosen member
     */
    private void banMember() {
        if(this.selectedClub != null){
            Membre membre = (Membre) JOptionPane.showInputDialog(
                    this,
                    "Quel membre doit être banni ?",
                    "Banissement",
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    this.selectedClub.getMembers().toArray(),
                    this.selectedClub.getMembers().toArray()[0]);
            this.selectedClub.removeMember(membre);
        }
    }

    /**
     * The method deletes all elements of the frame and then rebuilds the whole thing
     */
    private void refresh(){
        for(Component c : this.getContentPane().getComponents()){
            this.getContentPane().remove(c);
        }
        this.constraints.gridy = 0;
        ClubInfosIHM clubInfosIhm = new ClubInfosIHM(this.selectedClub);
        this.getContentPane().add(clubInfosIhm, constraints);
    }

    /**
     * This method display an option panel. This panel allows the user to choose which club to connect to
     */
    private void connect(){
        this.selectedClub = (Club) JOptionPane.showInputDialog(
                this,
                "Quel Club voulez vous gérer ?",
                "Choix club",
                JOptionPane.QUESTION_MESSAGE,
                null,
                clubs.toArray(),
                clubs.toArray()[0]);

        if(this.selectedClub == null){
            return;
        }

        this.constraints.gridy = 1;
        ClubInfosIHM clubInfosIhm = new ClubInfosIHM(this.selectedClub);
        this.getContentPane().add(clubInfosIhm, constraints);
        this.getContentPane().remove(this.connectButton);
    }

    /**
     * Just as the connect method, this method displays a panel allowing the user to choose which club to connect to
     */
    private void disconnect(){
        Container container = this.getContentPane();
        Club bucket = (Club) JOptionPane.showInputDialog(
                null,
                "Quel Club voulez vous gérer ?",
                "Choix club",
                JOptionPane.QUESTION_MESSAGE,
                null,
                this.clubs.toArray(),
                this.clubs.toArray()[0]);

        if(bucket == null){

            return;
        }
        this.selectedClub = bucket;

        for(Component component : container.getComponents()){
            container.remove(component);
        }
        this.constraints.gridy ++;
        container.add(new ClubInfosIHM(this.selectedClub), this.constraints);
    }

    /**
     * This method displays an instance of ConcertFormIHM. This Panel allows the user to create a new Concert
     */
    private void displayConcertForm(){
        if(this.selectedClub != null){
            for(Component c : this.getContentPane().getComponents()){
                if(c instanceof ConcertFormIHM){
                    this.getContentPane().remove(c);
                    break;
                }
            }
            Container container = this.getContentPane();
            this.constraints.gridy = 2;
            container.add(new ConcertFormIHM(this.selectedClub, this.manager), this.constraints);
        }
    }

    /**
     * THis method displays an instance of RoomFormIHM. This Panel allows the user to create a new Room
     */
    private void displayRoomForm(){
        if(this.selectedClub != null){
            for(Component c : this.getContentPane().getComponents()){
                if(c instanceof RoomFormIHM){
                    this.getContentPane().remove(c);
                    break;
                }
            }

            this.constraints.gridy = 3;

            Container container = this.getContentPane();
            container.add(new RoomFormIHM(this.manager),this.constraints);

        }
    }

    /**
     * This method is used as a bridge between ConcertListeners :
     * All the "newConcertEvent" received from the GlobalEventManager are passed down
     * to other Listeners.
     * @param concertEvent The event we are sending with the source and the concert inside
     */
    @Override
    public void newConcertEvent(ConcertEvent concertEvent) {
        for(Component c : this.getContentPane().getComponents()){
            if(c instanceof ConcertListener){
                ((ConcertListener)c).newConcertEvent(concertEvent);
            }
        }
    }

    /**
     * This method is used as a bridge between ConcertListeners :
     * All the "cancelConcertEvent" received from the GlobalEventManager are passed down
     * to other Listeners.
     * @param concertEvent The concert we are canceling
     */
    @Override
    public void cancelConcertEvent(ConcertEvent concertEvent) {

        this.manager.roomAvailable(new RoomEvent(this, concertEvent.getConcert().getRoom()));

        for(Component c : this.getContentPane().getComponents()){
            if(c instanceof ConcertListener){
                ((ConcertListener)c).cancelConcertEvent(concertEvent);
            }
        }
    }

    /**
     * This method is used as a bridge between ConcertListeners :
     * All the "newTicket" received from the GlobalEventManager are passed down
     * to other Listeners.
     * @param concertEvent The event we are sending with the source and the concert inside
     */
    @Override
    public void newTicket(ConcertEvent concertEvent) {
        for(Component c : this.getContentPane().getComponents()){
            if(c instanceof ConcertListener){
                ((ConcertListener)c).newTicket(concertEvent);
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
        for(Component c : this.getContentPane().getComponents()){
            if(c instanceof ConcertListener){
                ((ConcertListener)c).ticketRemoved(concertEvent);
            }
        }
    }
}
