import UI_Elements.JTitle;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

public class ClubInfosIHM extends JPanel implements ListSelectionListener {
    private JList<ConcertEvent> concertList;
    private GridBagConstraints constraints = new GridBagConstraints();
    public ClubInfosIHM(Club club){
        this.setLayout(new GridBagLayout());

        //this.constraints.fill = GridBagConstraints.BOTH;
        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        this.constraints.ipady = 20;      //make this component tall
        this.constraints.weightx = 0.0;
        this.constraints.gridwidth = 3;
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        JTitle titre = new JTitle(club.getName());
        this.add(titre,this.constraints);


        List<ConcertEvent> concertEventList = club.getConcertEvents();
        this.constraints.fill = GridBagConstraints.HORIZONTAL;
        this.constraints.gridx = 0;
        this.constraints.gridy = 1;
        this.constraints.ipady = 10;
        this.concertList = new JList<>(concertEventList.toArray(new ConcertEvent[0]));
        this.concertList.addListSelectionListener(this);
        this.add(this.concertList, this.constraints);

        this.constraints.gridy = 2;
        JLabel numberOfMembers = new JLabel("Je possède " + club.getMembers().size()+" abonnés");
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
