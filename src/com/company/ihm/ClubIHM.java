package com.company.ihm;

import com.company.Club;
import com.company.Membre;
import com.company.RoomManager;
import com.company.events.ConcertEvent;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.disconnectMenu)){
            this.disconnect();
        }
        else if(e.getSource().equals(this.createConcertMenu)) {
            this.displayConcertForm();
        }
        else if(e.getSource().equals(this.connectButton)){
            this.connect();
        }
        else if(e.getSource().equals(this.createRoomMenu)){
            this.displayRoomForm();
        }
        else if(e.getSource().equals(this.refreshMenu)){
            this.refresh();
        }
        else if(e.getSource().equals(this.banMemberMenu)) {
            this.banMember();
        }
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

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

    private void refresh(){
        for(Component c : this.getContentPane().getComponents()){
            this.getContentPane().remove(c);
        }
        this.constraints.gridy = 0;
        ClubInfosIHM clubInfosIhm = new ClubInfosIHM(this.selectedClub);
        this.getContentPane().add(clubInfosIhm, constraints);
    }

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

    @Override
    public void newConcertEvent(ConcertEvent concertEvent) {
        for(Component c : this.getContentPane().getComponents()){
            if(c instanceof ConcertListener){
                ((ConcertListener)c).newConcertEvent(concertEvent);
            }
        }
    }

    @Override
    public void cancelConcertEvent(ConcertEvent concertEvent) {
        for(Component c : this.getContentPane().getComponents()){
            if(c instanceof ConcertListener){
                ((ConcertListener)c).cancelConcertEvent(concertEvent);
            }
        }
    }

    @Override
    public void newTicket(ConcertEvent concertEvent) {
        for(Component c : this.getContentPane().getComponents()){
            if(c instanceof ConcertListener){
                ((ConcertListener)c).newTicket(concertEvent);
            }
        }
    }

    @Override
    public void ticketRemoved(ConcertEvent concertEvent) {
        for(Component c : this.getContentPane().getComponents()){
            if(c instanceof ConcertListener){
                ((ConcertListener)c).ticketRemoved(concertEvent);
            }
        }
    }
}
