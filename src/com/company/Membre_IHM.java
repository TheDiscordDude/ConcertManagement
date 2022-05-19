package com.company;

import com.company.events.ConcertEvent;
import com.company.listeners.ConcertListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Membre_IHM extends JFrame implements ActionListener, ListSelectionListener, ConcertListener {
    private int compt=0;
    private JPanel pan = new JPanel();
    private Label l1;
    private Label l2;
    private Label l3;

    private Label empty1;
    private Label empty2;
    private Label empty3;
    private Label empty4;
    private TextArea area;
    private JButton button;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JList list1;
    private JList list2;
    private JList list3;
    private List<Membre> l_membre;
    private List<Concert> l_c;
    private List<Club> l_club;
    private Club cb1;
    private GridBagConstraints constr;

    public Membre_IHM(List<Membre> l_membre,List<Club> cb1,List<Concert> l_c){
        this.l_membre=l_membre;
        this.l_club=cb1;
        this.l_c=l_c;
        this.setLayout(new GridBagLayout());
        this.constr=new GridBagConstraints();
        this.constr.fill = GridBagConstraints.HORIZONTAL;
        this.constr.insets.top = 5;
        this.constr.insets.bottom = 5;
        this.constr.insets.left = 5;
        this.constr.insets.right = 5;

        this.setTitle("Animation");
        this.setSize(750, 500);

        // CHOIX DES MEMBRES
        this.constr.gridx=0;
        this.constr.gridy=0;
        this.constr.gridwidth=1;
        this.constr.gridheight=1;

        List<String> l_name=new ArrayList<>();
        for(Membre m:l_membre){
            l_name.add(m.getNom()+" "+m.getPrenom());
        }
        this.list1=new JList(l_name.toArray());
        this.list1.addListSelectionListener(this);
        this.pan.add(this.list1,this.constr);



        //lISTES DES BILLETS DE LA PERSONNE
        this.constr.gridx=1;
        this.constr.gridy=0;
        this.constr.gridwidth=1;
        this.constr.gridheight=1;

        this.l1=new Label("Les billets : ");
        this.pan.add(this.l1,this.constr);


        this.constr.gridx=2;
        this.constr.gridy=0;
        this.constr.gridwidth=2;
        this.constr.gridheight=1;

        this.list2=new JList();
        this.pan.add(this.list2,this.constr);
        this.empty1=new Label("                  ");
        this.pan.add(this.empty1);

        this.constr.gridx=0;
        this.constr.gridy=1;
        this.constr.gridwidth=1;
        this.constr.gridheight=1;
        this.button2=new JButton("Voir les places disponibles");
        this.button2.addActionListener(this);
        this.pan.add(this.button2,this.constr);


        this.constr.gridx=2;
        this.constr.gridy=1;
        this.constr.gridwidth=1;
        this.constr.gridheight=1;
        this.l3=new Label();
        this.pan.add(this.l3,this.constr);




        // lISTE DES CONCERTS
        this.constr.gridx=0;
        this.constr.gridy=2;
        this.constr.gridwidth=1;
        this.constr.gridheight=1;
        this.empty2=new Label();
        this.pan.add(this.empty2);
        this.button3=new JButton("Voir la liste des concerts pour reserver un billet ou annuler");
        this.button3.addActionListener(this);
        this.pan.add(this.button3,this.constr);
        this.empty3=new Label();
        this.pan.add(this.empty3);
        this.constr.gridx=0;
        this.constr.gridy=3;
        this.constr.gridwidth=1;
        this.constr.gridheight=1;
        List<String>l_temp=new ArrayList<>();
        this.list3=new JList(l_temp.toArray());
        this.pan.add(this.list3,this.constr);
        this.constr.gridx=0;
        this.constr.gridy=4;
        this.button=new JButton();
        this.button.addActionListener(this);
        this.button.setOpaque(false);
        this.button.setContentAreaFilled(false);
        this.button.setBorderPainted(false);
        this.pan.add(this.button,this.constr);
        this.constr.gridx=1;
        this.constr.gridy=4;
        this.button4=new JButton();
        this.button4.addActionListener(this);
        this.button4.setOpaque(false);
        this.button4.setContentAreaFilled(false);
        this.button4.setBorderPainted(false);
        this.pan.add(this.button4,this.constr);





        this.setContentPane(this.pan);
        this.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Voir les places disponibles"){
            String str=this.list1.getSelectedValue().toString();
            String concert=this.list2.getSelectedValue().toString();
            String nb_ticket="";
            for(Membre m : this.l_membre){
                String personne=m.getNom()+" "+m.getPrenom();
                if(personne.equals(str)){
                    for(Concert c1: this.l_c){
                        String concert2=c1.getName()+" "+c1.getDate();
                        if(concert2.equals(concert)){
                            nb_ticket="Il reste "+c1.availableTickets()+" disponibles pour ce concert";
                        }
                    }
                    this.pan.remove(this.l3);
                    this.pan.remove(this.list3);
                    this.pan.remove(this.button3);
                    this.pan.remove(this.empty2);
                    this.l3=new Label(nb_ticket);
                    this.constr.gridx=2;
                    this.constr.gridy=1;
                    this.constr.gridwidth=1;
                    this.constr.gridheight=1;
                    this.pan.add(this.l3,this.constr);
                    this.constr.gridx=0;
                    this.constr.gridy=3;
                    this.constr.gridwidth=1;
                    this.constr.gridheight=1;
                    this.empty2=new Label("                                              ");
                    this.pan.add(this.empty2);
                    this.button3=new JButton("Voir la liste des concerts pour reserver un billet ou annuler");
                    this.button3.addActionListener(this);
                    this.pan.add(this.button3,this.constr);

                    this.pan.repaint();
                    this.pan.validate();

                }
            }
        } else if (e.getActionCommand()=="Voir la liste des concerts pour reserver un billet ou annuler") {

            List<String> l_concert=new ArrayList<>();
            for(Concert c1: this.l_c){
                //System.out.println(Calendar.);
                l_concert.add(c1.getName()+" // "+c1.getDate()+" //  prix de la place :"+c1.getCost()+"€");
            }
            this.remove(this.empty3);

            this.pan.remove(this.list3);
            this.empty3=new Label("                                     ");
            this.pan.add(this.empty3);
            this.list3=new JList(l_concert.toArray());
            this.pan.add(this.list3);
            this.empty4=new Label("                                                       ");
            this.pan.add(this.empty4);
            this.constr.gridx=0;
            this.constr.gridy=4;
            this.button=new JButton("Acheter un billet");
            this.button.addActionListener(this);
            this.pan.add(this.button,this.constr);
            this.constr.gridx=1;
            this.constr.gridy=4;
            this.button4=new JButton("Vendre un billet");
            this.button4.addActionListener(this);
            this.pan.add(this.button4,this.constr);


            this.pan.repaint();
            this.pan.validate();
        } else if (e.getActionCommand()=="Acheter un billet") {
            /*String str=this.list1.getSelectedValue().toString();
            String concert=this.list3.getSelectedValue().toString();

            for(Membre m :this.l_membre){
                String personne=m.getNom()+" "+m.getPrenom();
                if(personne.equals(str)){
                    for(Concert c1: this.l_c){
                        String concert2=c1.getName()+" // "+c1.getDate()+" //  prix de la place :"+c1.getCost()+"€";
                        if(concert2.equals(concert)){
                            for(Club c: this.l_club){
                                c.getMembers()
                            }
                            m.reserverBillet(c1,this.cb1);
                        }
                    }
                }
            }*/
            //List<ConcertListener> l_test=this.l_club.get(0).getMembers();
            //ConcertListener m=l_test.get(0);

            //System.out.println(m);

        }
        else if (e.getActionCommand()=="Annuler un billet") {
            String str=this.list1.getSelectedValue().toString();
            String concert=this.list2.getSelectedValue().toString();
            System.out.println(concert);

            for(Membre m :this.l_membre){
                String personne=m.getNom()+" "+m.getPrenom();
                if(personne.equals(str)){
                    for(Concert c1: this.l_c){
                        String concert2=c1.getName()+" "+c1.getDate();
                        System.out.println(concert2);
                        if(concert2.equals(concert)){
                            System.out.println("NNNNNNNNO");
                            m.annulerBillet(c1,this.cb1);
                        }
                    }
                }
            }

            this.pan.repaint();
            this.pan.validate();


        }
        List<ConcertEvent> ev= this.l_club.get(0).getConcertEvents();
        for(ConcertEvent c: ev){
            System.out.println(c.getConcert());
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        this.pan.remove(this.l3);
        String str=this.list1.getSelectedValue().toString();

        for(Membre m : this.l_membre){
            String personne=m.getNom()+" "+m.getPrenom();

            if(personne.equals(str)){
                //System.out.println("COUCOU");
                List<String> l_c=new ArrayList<>();
                //String str_res="";
                for(Concert c1: m.getL_concert()){
                    System.out.println(c1);
                    l_c.add(c1.getName()+" "+c1.getDate());
                    //str_res+=c1.getName()+" "+c1.getDate()+" \n";
                }

                System.out.println(l_c);
                this.pan.remove(this.list2);
                this.pan.remove(this.button3);
                this.pan.remove(this.empty3);
                this.pan.remove(this.list3);
                this.pan.remove(this.button);
                this.pan.remove(this.button4);
                this.constr.gridx=2;
                this.constr.gridy=0;
                this.constr.gridwidth=2;
                this.constr.gridheight=1;
                //this.pan.remove(this.list2);
                this.list2=new JList(l_c.toArray());
                this.pan.add(this.list2,this.constr);
                this.pan.remove(this.empty1);
                this.empty1=new Label("                           ");
                this.pan.add(this.empty1);
                this.pan.remove(this.button2);
                this.button2=new JButton("Voir les places disponibles");
                this.button2.addActionListener(this);
                this.pan.add(this.button2);

                this.pan.remove(this.empty2);
                this.empty2=new Label("                                                        ");
                this.pan.add(this.empty2);
                this.constr.gridx=0;
                this.constr.gridy=3;
                this.constr.gridwidth=1;
                this.constr.gridheight=1;
                this.button3=new JButton("Voir la liste des concerts pour reserver un billet ou annuler");
                this.button3.addActionListener(this);
                this.pan.add(this.button3,this.constr);
                this.pan.repaint();
                this.pan.validate();


            }
        }

        this.pan.repaint();
        this.pan.validate();


    }

    @Override
    public void newConcertEvent(ConcertEvent concertEvent) {

    }

    @Override
    public void cancelConcertEvent(ConcertEvent concertEvent) {

    }

    @Override
    public void newTicket(ConcertEvent concertEvent) {

    }

    @Override
    public void ticketRemoved(ConcertEvent concertEvent) {

    }
}