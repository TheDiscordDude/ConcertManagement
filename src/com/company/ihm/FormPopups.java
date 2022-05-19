package com.company.ihm;

import javax.swing.*;
import java.awt.*;

public class FormPopups {
    /**
     * Informs the user that a field is blank
     *
     * @param source The source element of the event
     * @param fieldName the name of the field
     */
    static void fieldMustNotBeBlank(Component source, String fieldName){
        showError(null, String.format("%1$s Error", fieldName),String.format("%1$s field must not be blank", fieldName));
    }

    /**
     * Shows an error to the User
     *
     * @param source The source element of the event
     * @param title   The title of the error window
     * @param content The text content of the error
     */
    static void showError(Component source, String title, String content){
        JOptionPane.showMessageDialog(source,
                content,
                title,
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows the user an informational popup to the user informing him of the success of an operation
     *
     * @param source  The source element of the event
     * @param title   The title of the popup window
     * @param content The content of the popup window
     */
    static void success(Component source, String title, String content){
        JOptionPane.showMessageDialog(null,
                content,
                title,
                JOptionPane.INFORMATION_MESSAGE);
    }
}
