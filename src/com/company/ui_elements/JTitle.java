package com.company.ui_elements;

import javax.swing.*;
import java.awt.*;

/**
 * It's just a JLabel in red
 */
public class JTitle extends JLabel {
    public JTitle(String text) {
        super(text, SwingConstants.CENTER);
        this.setFont(Style.title);
        this.setForeground(Color.RED);
    }
}
