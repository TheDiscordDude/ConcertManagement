package com.company.ui_elements;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class JDateField extends JFormattedTextField {
    public JDateField() {
        super();
        try{
            MaskFormatter formatter = new MaskFormatter("##/##/#### ##:##");
            formatter.setPlaceholder("");
            DefaultFormatterFactory factory = new DefaultFormatterFactory(formatter);
            this.setFormatterFactory(factory);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
