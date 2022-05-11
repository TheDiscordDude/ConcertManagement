package UI_Elements;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class JDateField extends JFormattedTextField {
    public JDateField() {
        super();
        try{
            MaskFormatter formatter = new MaskFormatter("##/##/####");
            formatter.setPlaceholder("#");
            DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter);
            this.setFormatterFactory(factory);
            //this.setFormatter(formatter);


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
