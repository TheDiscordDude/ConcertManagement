import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class ClubCreateConvertEventIHM extends JPanel {
    private JTextField nameField;
    private JFormattedTextField dateField;
    private JTextField costField;
    private JComboBox roomField;
    private GridBagConstraints constraints = new GridBagConstraints();


    public ClubCreateConvertEventIHM(Club club, ArrayList<Salle> salles){
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
        this.constraints.gridy = 2;
        this.add(dateLabel, this.constraints);

        try{
            MaskFormatter formatter = new MaskFormatter("##/##/####");
            formatter.setPlaceholder("#");
            this.dateField = new JFormattedTextField(formatter);
            this.constraints.gridy = 3;
            this.add(this.dateField, this.constraints);

        } catch (ParseException e ){
            e.printStackTrace();
        }

        JLabel costLabel = new JLabel("Prix des tickets");
        this.constraints.gridy = 4;
        this.add(costLabel, this.constraints);

        this.costField = new JTextField(25);
        this.constraints.gridy = 5;
        this.add(this.costField, this.constraints);


        JLabel salleLabel = new JLabel("La salle su concert");
        this.constraints.gridy = 6;
        this.add(salleLabel, this.constraints);


        this.roomField = new JComboBox<Salle>((Salle[]) salles.toArray());
        this.constraints.gridy = 7;
        this.add(this.roomField, this.constraints);


    }
}
