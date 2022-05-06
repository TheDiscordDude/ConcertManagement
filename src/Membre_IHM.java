import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalMenuBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

public class Membre_IHM extends JFrame implements ActionListener, ListSelectionListener {
    private int compt=0;
    private JPanel pan = new JPanel();
    private Label l1;
    private Label l2;
    private TextArea area;
    private JButton button;
    private JList list1;
    private JList list2;
    private JList list3;
    private List<Membre> l_membre;
    private List<Concert> l_c;
    private Club cb1;

    public Membre_IHM(List<Membre> l_membre,Club cb1,List<Concert> l_c){
        this.l_membre=l_membre;
        this.cb1=cb1;
        this.l_c=l_c;
        this.setLayout(new GridBagLayout());
        GridBagConstraints constr = new GridBagConstraints();
        this.setTitle("Animation");
        this.setSize(500, 500);
        constr.gridx=0;
        constr.gridy=0;
        List<String> l_name=new ArrayList<>();
        for(Membre m:l_membre){
            l_name.add(m.getNom()+" "+m.getPrenom());
        }
        this.list1=new JList(l_name.toArray());
        this.list1.addListSelectionListener(this);
        this.pan.add(this.list1,constr);

        constr.gridx=1;
        constr.gridy=0;
        this.l1=new Label("Les billets : ");
        this.pan.add(l1,constr);
        constr.gridx=1;
        constr.gridy=1;

        this.list2=new JList();
        this.pan.add(this.list2,constr);

        constr.gridx=1;
        constr.gridy=2;
        List<String>l_temp=new ArrayList<>();
        this.list3=new JList(l_temp.toArray());
        this.list3.addListSelectionListener(this);
        this.pan.add(this.list3,constr);
        constr.gridx=2;
        constr.gridy=3;
        this.button=new JButton("Acheter un biller");
        this.button.addActionListener(this);
        this.pan.add(this.button,constr);
        constr.gridx=5;
        constr.gridy=5;
        this.l2=new Label("Place disponible");
        this.pan.add(this.l2,constr);

        this.setContentPane(this.pan);
        this.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String concert=this.list3.getSelectedValue().toString();
        String personne=this.list1.getSelectedValue().toString();
        for(Membre m :this.l_membre){
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
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println(e.getSource());
        System.out.println("JE DETCETCE");
        String str=this.list1.getSelectedValue().toString();
        String message_ticket="";

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
                this.pan.remove(this.list2);
                this.list2=new JList(l_c.toArray());
                message_ticket=m.getAvailable_ticket_str();
                this.pan.add(this.list2);

                this.pan.repaint();
                this.pan.validate();


            }
        }
        List<String> l_concert=new ArrayList<>();
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
        }*/


        this.list3=new JList(l_concert.toArray());



        this.pan.add(this.list3);

        this.pan.repaint();
        this.pan.validate();


    }
}

