package com.company.club_ihm;

import com.company.Concert;
import com.company.RoomManager;
import com.company.Room;
import com.company.events.RoomEvent;
import com.company.exceptions.RoomTakenException;
import com.company.ui_elements.JDateField;
import com.company.Club;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Panel containing a form to create a concert
 */
public class ConcertFormIHM extends JPanel implements ActionListener {
    private JTextField nameField;
    private JDateField dateField;
    private JTextField costField;
    private JComboBox<Room> roomField;

    private DefaultComboBoxModel<Room> comboBoxContent;
    private Club club;
    private RoomManager manager;
    private GridBagConstraints constraints = new GridBagConstraints();

    /**
     * Create the actual form.
     * We need quite a lot of info, because when a concert is created,
     * an event must be sent to the club and the clubInfoIHM to update the display
     * @param club The club hosting the
     * @param manager The manager that gives the rooms if they are not already reserved
     */
    public ConcertFormIHM(Club club, RoomManager manager){
        this.club = club;
        this.manager = manager;

        this.setLayout(new GridBagLayout());
        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;

        JLabel nameLabel = new JLabel("Nom du concert :");
        this.add(nameLabel, this.constraints);

        this.nameField = new JTextField();
        this.constraints.gridy = 1;
        this.add(this.nameField, this.constraints);


        JLabel dateLabel = new JLabel("Date du concert : ");
        this.constraints.gridy ++;
        this.add(dateLabel, this.constraints);


        this.dateField = new JDateField();
        this.constraints.gridy ++;
        this.add(this.dateField, this.constraints);


        JLabel costLabel = new JLabel("Prix des tickets");
        this.constraints.gridy ++;
        this.add(costLabel, this.constraints);

        this.costField = new JTextField(25);
        this.constraints.gridy ++;
        this.add(this.costField, this.constraints);


        JLabel salleLabel = new JLabel("La salle su concert");
        this.constraints.gridy ++;
        this.add(salleLabel, this.constraints);

        this.comboBoxContent = new DefaultComboBoxModel<>();
        this.comboBoxContent.addAll(this.manager.getRooms());

        this.roomField = new JComboBox<>();
        this.roomField.setModel(this.comboBoxContent);
        this.constraints.gridy ++;
        this.add(this.roomField, this.constraints);

        JButton createButton = new JButton("Créer");
        createButton.addActionListener(this);
        this.constraints.gridy ++;
        this.add(createButton, this.constraints);

    }

    /**
     * When the button is pressed, this method is called.
     * It checks the fields and then creates the Concert and sends it
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String concertName = this.nameField.getText();

        // Checking if is the name is blank
        if(concertName.isBlank()){
            FormPopups.fieldMustNotBeBlank(this, "Concert name");
            return;
        }

        String dateString = this.dateField.getText();
        Date date ;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dateFormat.setLenient(false);
            date = dateFormat.parse(dateString);
            if(!date.after(Calendar.getInstance().getTime())){
                FormPopups.showError(this, "Date Error", "Date must be in the future");
                return;
            }
        } catch (ParseException ex) {
            FormPopups.showError(this, "Date Error", "Date is in wrong format");
            return;
        }

        // Check if the costField is blank
        if(this.costField.getText().isBlank()){
            FormPopups.fieldMustNotBeBlank(this, "Cost");
            return;
        }

        double cost = Double.parseDouble(this.costField.getText());

        Room room = (Room) this.roomField.getSelectedItem();

        try {
            this.manager.reserveRoom(new RoomEvent(this, room));
        } catch (RoomTakenException ex) {
            throw new RuntimeException(ex);
        }
        this.comboBoxContent.removeAllElements();
        this.comboBoxContent.addAll(this.manager.getRooms());

        Concert newConcert = new Concert(concertName, date, cost, room);
        this.club.addConcert(newConcert);
        FormPopups.success(this, "Concert", "Concert ajouté");
    }
}
