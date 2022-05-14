package com.company.club_ihm;

import com.company.Club;
import com.company.RoomManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// todo : be able to  create a Club / Room
public class ClubIHM extends JFrame implements ActionListener {
    private JMenuItem disconnectMenu;
    private Club selectedClub;
    private JMenuItem createConcertMenu;
    private JButton connectButton;
    private ArrayList<Club> clubs;
    private RoomManager gestionnaire;
    private GridBagConstraints constraints;

    public ClubIHM(ArrayList<Club> clubs, RoomManager gestionnaire){
        this.clubs = clubs;
        this.gestionnaire = gestionnaire;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(500, 500);
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
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
    private void createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu clubMenu = new JMenu("Club");
        this.disconnectMenu = new JMenuItem("Deconnection");
        this.disconnectMenu.addActionListener(this);
        clubMenu.add(this.disconnectMenu);

        JMenu concertMenu = new JMenu("Concert");
        this.createConcertMenu = new JMenuItem("Organiser un concert");
        this.createConcertMenu.addActionListener(this);
        concertMenu.add(this.createConcertMenu);

        menuBar.add(clubMenu);
        menuBar.add(concertMenu);
        this.setJMenuBar(menuBar);
    }

    private void connect(){
        this.selectedClub = (Club) JOptionPane.showInputDialog(
                null,
                "Quel Club voulez vous gérer ?",
                "Choix club",
                JOptionPane.QUESTION_MESSAGE,
                null,
                clubs.toArray(),
                clubs.toArray()[0]);

        this.constraints.gridy = 1;
        ClubInfosIHM clubInfosIhm = new ClubInfosIHM(this.selectedClub);
        this.getContentPane().add(clubInfosIhm, constraints);
        //this.connectButton.setVisible(false);
        this.getContentPane().remove(this.connectButton);
    }

    private void disconnect(){
        Container container = this.getContentPane();
        this.selectedClub = (Club) JOptionPane.showInputDialog(
                null,
                "Quel Club voulez vous gérer ?",
                "Choix club",
                JOptionPane.QUESTION_MESSAGE,
                null,
                this.clubs.toArray(),
                this.clubs.toArray()[0]);
        for(Component component : container.getComponents()){
            if(component instanceof ClubInfosIHM || component instanceof ConcertFormIHM){
                container.remove(component);
            }
        }
        container.add(new ClubInfosIHM(this.selectedClub), this.constraints);
    }

    private void displayConcertForm(){
        if(this.selectedClub != null){
            Container container = this.getContentPane();
            ClubInfosIHM clubInfosIHM = null;
            for(Component component : container.getComponents()){
                if(component instanceof ClubInfosIHM){
                    clubInfosIHM = (ClubInfosIHM) component;
                    break;
                }
            }
            container.add(new ConcertFormIHM(this.selectedClub, this.gestionnaire, clubInfosIHM));
        }

    }
}
