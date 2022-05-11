package UI_Elements;

import javax.swing.*;
import java.awt.*;

public class JTitle extends JLabel {
    public JTitle(String text) {
        super(text, SwingConstants.CENTER);
        this.setFont(Style.title);
        this.setForeground(Color.RED);
    }
}
