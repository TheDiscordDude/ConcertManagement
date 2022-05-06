import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClubIHM extends JFrame implements ActionListener {
    private JButton selectClub;
    private JButton createConcert;
    private List<Club> clubs;
    private GridBagConstraints constraints;

    public ClubIHM(List<Club> clubs){
        this.clubs = clubs;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(500, 500);
        this.setTitle("Club - Concert Management");
        List<String> clubNames = new ArrayList<>();
        for (Club c : clubs){
            clubNames.add(c.getName());
        }

        Container container=getContentPane();
        container.setLayout(new GridBagLayout());
        this.constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        this.selectClub = new JButton("Déconnection un Club");
        this.selectClub.addActionListener(this);
        this.createConcert = new JButton("Organiser un concert");
        this.createConcert.addActionListener(this);
        container.add(selectClub, constraints);
        container.add(createConcert, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;

        Club selectedClub = (Club) JOptionPane.showInputDialog(
                null,
                "Quel Club voulez vous gérer ?",
                "Choix club",
                JOptionPane.QUESTION_MESSAGE,
                null,
                clubs.toArray(),
                clubs.toArray()[0]);

        constraints.gridy = 1;
        ClubInfosIHM clubInfosIhm = new ClubInfosIHM(selectedClub);
        container.add(clubInfosIhm, constraints);
        //this.setContentPane(clubInfosIhm);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.selectClub){
            Club selectedClub = (Club) JOptionPane.showInputDialog(
                    null,
                    "Quel Club voulez vous gérer ?",
                    "Choix club",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    this.clubs.toArray(),
                    this.clubs.toArray()[0]);
            Container container=getContentPane();
            for(Component component : container.getComponents()){
                if(component instanceof ClubInfosIHM || component instanceof ClubCreateConvertEventIHM){
                    //component = new ClubInfosIHM(selectedClub);
                    //container.revalidate();
                    //container.repaint();

                    container.remove(component);
                    container.revalidate();
                    container.repaint();
                    break;
                }
            }

            container.add(new ClubInfosIHM(selectedClub), this.constraints);

        } else if (e.getSource() == this.createConcert){

        }
    }
}
