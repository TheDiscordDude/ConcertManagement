import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClubIHM extends JFrame implements ActionListener {
    private JButton selectClubButton;
    private Club selectedClub;
    private JButton createConcertButton;
    private ArrayList<Club> clubs;
    private ArrayList<Salle> salles;
    private GridBagConstraints constraints;

    public ClubIHM(ArrayList<Club> clubs, ArrayList<Salle> salles){
        this.clubs = clubs;
        this.salles = salles;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(500, 500);
        this.setTitle("Club - Concert Management");

        Container container=getContentPane();
        container.setLayout(new GridBagLayout());
        this.constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        this.selectClubButton = new JButton("Déconnection un Club");
        this.selectClubButton.addActionListener(this);
        this.createConcertButton = new JButton("Organiser un concert");
        this.createConcertButton.addActionListener(this);

        container.add(selectClubButton, constraints);
        container.add(createConcertButton, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        this.selectedClub = (Club) JOptionPane.showInputDialog(
                null,
                "Quel Club voulez vous gérer ?",
                "Choix club",
                JOptionPane.QUESTION_MESSAGE,
                null,
                clubs.toArray(),
                clubs.toArray()[0]);

        constraints.gridy = 1;
        ClubInfosIHM clubInfosIhm = new ClubInfosIHM(this.selectedClub);
        container.add(clubInfosIhm, constraints);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Container container=getContentPane();
        if(e.getSource().equals(this.selectClubButton)){
            this.selectedClub = (Club) JOptionPane.showInputDialog(
                    null,
                    "Quel Club voulez vous gérer ?",
                    "Choix club",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    this.clubs.toArray(),
                    this.clubs.toArray()[0]);
            for(Component component : container.getComponents()){
                if(component instanceof ClubInfosIHM || component instanceof ClubCreateConcertEventIHM){
                    container.remove(component);

                    //break;
                }
            }
            container.revalidate();
            container.repaint();

            container.add(new ClubInfosIHM(this.selectedClub), this.constraints);

        }
        else if(e.getSource().equals(this.createConcertButton)) {
            container.add(new ClubCreateConcertEventIHM(this.selectedClub, this.salles));
            container.revalidate();
            container.repaint();
        }
    }
}
