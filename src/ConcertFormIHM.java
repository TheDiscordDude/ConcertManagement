import UI_Elements.JDateField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Panel containing a form to create a concert
 */
public class ConcertFormIHM extends JPanel implements ActionListener {
    private JTextField nameField;
    private JDateField dateField;
    private JTextField costField;
    private JComboBox<Salle> roomField;
    private Club club;
    private ClubInfosIHM clubInfosIHM;
    private GridBagConstraints constraints = new GridBagConstraints();


    /**
     * Create the actual form.
     * We need quite a lot of info, because when a concert is created,
     * an event must be sent to the club and the clubInfoIHM to update the display
     * @param club The club hosting the
     * @param rooms The available rooms in which a concert can be held
     * @param clubInfosIHM The other panel to update when a concert is created
     */
    public ConcertFormIHM(Club club, ArrayList<Salle> rooms, ClubInfosIHM clubInfosIHM){
        this.club = club;
        this.clubInfosIHM = clubInfosIHM;

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

        this.roomField = new JComboBox<>(rooms.toArray(new Salle[0]));
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
            fieldMustNotBeBlank("Concert name");
            return;
        }

        String dateString = this.dateField.getText();
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dateFormat.setLenient(false);
            date = dateFormat.parse(dateString);
            System.out.println(date);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null,
                    "Date is in wrong format",
                    "Date Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the costField is blank
        if(this.costField.getText().isBlank()){
            fieldMustNotBeBlank("Cost");
            return;
        }

        double cost = Double.parseDouble(this.costField.getText());

        Salle salle = (Salle) this.roomField.getSelectedItem();

        Concert newConcert = new Concert(concertName, date, cost, salle);
        this.club.addConcert(newConcert);

        this.clubInfosIHM.newConcertEvent(new ConcertEvent(this, newConcert));
    }

    /**
     * Informs the user that a field is blank
     * @param fieldName the name of the field
     */
    static void fieldMustNotBeBlank(String fieldName){
        JOptionPane.showMessageDialog(null,
                String.format("%1$s field must not be blank", fieldName),
                 String.format("%1$s Error", fieldName),
                JOptionPane.ERROR_MESSAGE);
    }
}
