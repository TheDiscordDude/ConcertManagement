package com.company.ihm;

import com.company.Room;
import com.company.RoomManager;
import com.company.ui_elements.JTitle;

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

        JTitle title = new JTitle("Ajouter un salle: ");
        this.add(title, this.constraints);

        JLabel nameLabel = new JLabel("Nom de la Salle :");
        this.constraints.gridy ++;
        this.add(nameLabel, this.constraints);

        this.nameField = new JTextField();
        this.constraints.gridy ++;
        this.add(this.nameField, this.constraints);

        JLabel capacityLabel = new JLabel("Capacité de la salle :");
        this.constraints.gridy ++;
        this.add(capacityLabel, this.constraints);

        this.capacityField = new JTextField();
        this.constraints.gridy ++;
        this.add(this.capacityField, this.constraints);

        JButton button = new JButton("Créer");
        button.addActionListener(this);
        this.constraints.gridy ++;
        this.add(button, this.constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String roomName = this.nameField.getText();

        if(roomName.isBlank()){
            FormPopups.fieldMustNotBeBlank(this, "Room name");
            return;
        }

        String capacityString = this.capacityField.getText();
        if(capacityString.isBlank()){
            FormPopups.fieldMustNotBeBlank(this, "Capacity");
            return;
        }

        int roomCapacity ;
        try{
            roomCapacity = Integer.parseInt(capacityString);
            if(roomCapacity < 1){
                FormPopups.showError(this, "Capacity Error","capacity too small");
                return;
            }
        } catch (NumberFormatException err){
            FormPopups.showError(this, "Capacity Error","Wrong number format");
            err.printStackTrace();
            return;
        }

        Room newRoom = new Room(roomName, roomCapacity);
        this.roomManager.addRoom(newRoom);

        FormPopups.success(this, "Salle ajoutée", "La salle a été ajoutée");
    }
}
