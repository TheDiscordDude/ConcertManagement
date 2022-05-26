package com.company.ihm;

import com.company.Club;
import com.company.Membre;
import com.company.events.ConcertEvent;
import com.company.listeners.ConcertListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MembreGestionClub_IHM extends JFrame implements ActionListener, ListSelectionListener, ConcertListener {

    private GridBagConstraints constr;
    private JPanel pan = new JPanel();
    private List<Club> l_club;

    private JList<String> list1;
    private JList list2;

    private Label l1;
    private Label l2;
    private Label l3;

    public MembreGestionClub_IHM(List<Club> list_club,String membre) throws HeadlessException {

        this.setLayout(new GridBagLayout());
        this.constr=new GridBagConstraints();
        this.constr.fill = GridBagConstraints.HORIZONTAL;
        this.constr.insets.top = 5;
        this.constr.insets.bottom = 5;
        this.constr.insets.left = 5;
        this.constr.insets.right = 5;

        this.setTitle("test");
        this.setSize(750, 500);

        this.l_club=list_club;
        String person;
        Club club_m=new Club();
        for(Club c:this.l_club){
            List<Membre> l_m=c.getMembers();
            for(Membre m: l_m){
                person=m.getNom()+" "+m.getPrenom();
                if(person.equals(membre)){
                    club_m=c;
                }
            }
        }
        this.l1=new Label(club_m.getName());
        this.pan.add(this.l1);
        List<String> l_membre=new ArrayList<>();
        for(Membre item: club_m.getMembers()){
            l_membre.add(item.getNom()+" "+item.getPrenom());
        }
        this.list1=new JList<>(l_membre.toArray());
        this.pan.add(this.list1);

        this.list2=new JList<>(club_m.getConcertEvents().toArray());
        this.pan.add(this.list2);



        this.setContentPane(this.pan);
        this.setVisible(true);
    }

    @Override
    public void newConcertEvent(ConcertEvent concertEvent) {

    }

    @Override
    public void canceledConcertEvent(ConcertEvent concertEvent) {

    }

    @Override
    public void newTicket(ConcertEvent concertEvent) {

    }

    @Override
    public void ticketRemoved(ConcertEvent concertEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
