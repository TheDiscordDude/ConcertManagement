import UI_Elements.JDateField;
import org.w3c.dom.CDATASection;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ClubCreateConcertEventIHM extends JPanel implements ActionListener {
    private JTextField nameField;
    private JDateField dateField;
    private JTextField costField;
    private JComboBox roomField;
    private Club club;
    private GridBagConstraints constraints = new GridBagConstraints();


    public ClubCreateConcertEventIHM(Club club, ArrayList<Salle> salles){
        this.club = club;

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

        this.roomField = new JComboBox<>(salles.toArray(new Salle[0]));
        this.constraints.gridy ++;
        this.add(this.roomField, this.constraints);

        JButton createButton = new JButton("Créer");
        createButton.addActionListener(this);
        this.constraints.gridy ++;
        this.add(createButton, this.constraints);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String concertName = this.nameField.getText();
        String dateString = this.dateField.getText();
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            Date date = dateFormat.parse(dateString);
            System.out.println(date);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null,
                    "Date is in wrong format",
                    "Date Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }


        double cost = Double.parseDouble(this.costField.getText());
        Salle salle = (Salle) this.roomField.getSelectedItem();

        Concert newConcert = new Concert(concertName, dateString, cost, salle);
        this.club.addConcert(newConcert);
    }
}
