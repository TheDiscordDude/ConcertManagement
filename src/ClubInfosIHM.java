import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class ClubInfosIHM extends JPanel implements ListSelectionListener {
    private Club club;
    private JList<ConcertEvent> concertList;
    private GridBagConstraints constraints = new GridBagConstraints();
    public ClubInfosIHM(Club club){
        this.setLayout(new GridBagLayout());

        this.club = club;

        //this.constraints.fill = GridBagConstraints.BOTH;
        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        this.constraints.ipady = 20;      //make this component tall
        this.constraints.weightx = 0.0;
        this.constraints.gridwidth = 3;
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        JLabel titre = new JLabel(club.getName());
        //gridbag.setConstraints(titre, c);
        this.add(titre,this.constraints);


        List<ConcertEvent> concertEventList = this.club.getConcertEvents();
        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        this.constraints.gridx = 0;
        this.constraints.gridy = 1;
        this.constraints.ipady = 10;
        this.concertList = new JList(concertEventList.toArray());
        this.concertList.addListSelectionListener(this);
        this.add(this.concertList, this.constraints);

        this.constraints.gridy = 2;
        JLabel numberOfMembers = new JLabel("Je possède " + this.club.getMembers().size()+" abonnés");
        this.add(numberOfMembers, this.constraints);



    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        for (Component c : this.getComponents()){
            if(c instanceof ConcertInfosIHM){
                this.remove(c);
            }
        }
        this.constraints.gridy++;
        this.add(new ConcertInfosIHM(this.concertList.getSelectedValue().getConcert()), this.constraints);

        this.revalidate();
        this.repaint();
    }

}
