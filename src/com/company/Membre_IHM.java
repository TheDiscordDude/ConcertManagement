package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Membre_IHM extends JFrame implements ActionListener, ListSelectionListener {
    private int compt=0;
    private JPanel pan = new JPanel();
    private Label l1;
    private Label l2;
    private Label l3;
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
    private Club cb1;
    private GridBagConstraints constr;

    public Membre_IHM(List<Membre> l_membre,Club cb1,List<Concert> l_c){
        this.l_membre=l_membre;
        this.cb1=cb1;
        this.l_c=l_c;
        this.setLayout(new GridBagLayout());
        this.constr=new GridBagConstraints();
        this.constr.fill = GridBagConstraints.HORIZONTAL;


        this.setTitle("Animation");
        this.setSize(500, 500);

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


        this.constr.gridx=0;
        this.constr.gridy=1;
        this.constr.gridwidth=1;
        this.constr.gridheight=1;
        this.button2=new JButton("Voir les places disponibles");
        this.button2.addActionListener(this);
        this.pan.add(this.button2,this.constr);

        this.constr.gridx=2;
        this.constr.gridy=1;
        this.constr.gridwidth=3;
        this.constr.gridheight=1;
        this.l2=new Label("Place disponible");
        this.pan.add(this.l2,this.constr);
        this.constr.gridx=3;
        this.constr.gridy=1;
        this.l3=new Label();
        this.pan.add(this.l3,this.constr);




        /*// lISTE DES CONCERTS
        constr.gridx=1;
        constr.gridy=2;
        this.button3=new JButton("Voir la liste des concerts pour reserver un billet ou annuler");
        this.button3.addActionListener(this);
        this.pan.add(this.button3,constr);
        List<String>l_temp=new ArrayList<>();
        this.list3=new JList(l_temp.toArray());
        this.pan.add(this.list3,constr);
        constr.gridx=2;
        constr.gridy=3;
        this.button=new JButton("Acheter un billet");
        this.button.addActionListener(this);
        this.pan.add(this.button,constr);

        this.button4=new JButton("Annuler un billet");
        this.button4.addActionListener(this);
        this.pan.add(this.button4,constr);*/





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
                    this.l3=new Label(nb_ticket);
                    this.pan.add(this.l3);
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
            this.pan.remove(this.list3);
            this.list3=new JList(l_concert.toArray());
            this.pan.add(this.list3);

            /*for(Membre m :this.l_membre){
                String str=m.getNom()+" "+m.getPrenom();
                if(personne.equals(str)){
                    for(Concert c1: this.l_c){
                        String concert2=c1.getName()+" "+c1.getDate();
                        if(concert2.equals(concert)){
                            m.reserverBillet(c1,this.cb1);
                        }
                        //str_res+=c1.getName()+" "+c1.getDate()+" \n";
                    }
                    if(!this.list3.isSelectionEmpty()){
                        this.pan.remove(this.l2);
                        this.l2=new Label(m.getAvailable_ticket_str());
                        this.pan.add(this.l2);
                    }



                    this.pan.repaint();
                    this.pan.validate();
                }
            }*/

            this.pan.repaint();
            this.pan.validate();
        } else if (e.getActionCommand()=="Acheter un billet") {
            String str=this.list1.getSelectedValue().toString();
            String concert=this.list3.getSelectedValue().toString();

            for(Membre m :this.l_membre){
                String personne=m.getNom()+" "+m.getPrenom();
                if(personne.equals(str)){
                    for(Concert c1: this.l_c){
                        String concert2=c1.getName()+" // "+c1.getDate()+" //  prix de la place :"+c1.getCost()+"€";
                        if(concert2.equals(concert)){
                            m.reserverBillet(c1,this.cb1);
                        }
                    }
                }
            }
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

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        String str=this.list1.getSelectedValue().toString();


        for(Membre m : this.l_membre){
            String personne=m.getNom()+" "+m.getPrenom();

            if(personne.equals(str)){
                //System.out.println("COUCOU");
                List<String> l_c=new ArrayList<>();
                //String str_res="";
                    for(Concert c1: m.getL_concert()){
                        l_c.add(c1.getName()+" "+c1.getDate());
                        //str_res+=c1.getName()+" "+c1.getDate()+" \n";
                    }
                this.constr.gridx=2;
                this.constr.gridy=0;
                this.constr.gridwidth=2;
                this.constr.gridheight=1;
                this.pan.remove(this.list2);
                this.list2=new JList(l_c.toArray());
                this.pan.add(this.list2,this.constr);

                this.pan.repaint();
                this.pan.validate();


            }
        }
       /* List<String> l_concert=new ArrayList<>();
        //Afichage liste de tous les concerts
        for(Concert c: this.l_c){
            l_concert.add(c.getName()+" "+c.getDate());
        }

        this.pan.remove(this.list3);
        //System.out.println(e.getSource());
        /*if(){
            System.out.println("TEEEEEEEEEEEEEEEEEEEEES");
            this.pan.remove(this.l2);
            this.l2=new Label(message_ticket);
            this.pan.add(this.l2);
        }


        this.list3=new JList(l_concert.toArray());



        this.pan.add(this.list3);*/

        this.pan.repaint();
        this.pan.validate();


    }
}

