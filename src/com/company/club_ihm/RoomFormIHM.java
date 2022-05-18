package com.company.club_ihm;

import com.company.Room;
import com.company.RoomManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomFormIHM extends JPanel implements ActionListener {

    private JTextField nameField;

    private JTextField capacityField;

    private RoomManager roomManager;

    private GridBagConstraints constraints = new GridBagConstraints();

    public RoomFormIHM(RoomManager roomManager){
        this.roomManager = roomManager;
        this.setLayout(new GridBagLayout());
        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;

        JLabel nameLabel = new JLabel("Nom de la Salle :");
        this.add(nameLabel, this.constraints);

        this.nameField = new JTextField();
        this.constraints.gridy = 1;
        this.add(this.nameField, this.constraints);

        JLabel capacityLabel = new JLabel("Capacité de la salle :");
        this.constraints.gridy ++;
        this.add(capacityLabel, this.constraints);

        this.capacityField = new JTextField();
        this.constraints.gridy ++;
        this.add(this.capacityField, this.constraints);

        JButton button = new JButton("Créer");
        button.addActionListener(this);
        this.constraints.gridy ++ ;
        this.add(button, this.constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String roomName = this.nameField.getText();

        String capacityString = this.capacityField.getText();
        int roomCapacity = Integer.parseInt(capacityString);

        Room newRoom = new Room(roomName, roomCapacity);

        this.roomManager.addRoom(newRoom);
    }
}
