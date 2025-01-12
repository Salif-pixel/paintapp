package models;

import javax.swing.*;
import java.awt.*;

public class ToolItem extends JPanel {
    public ToolItem(){

    }
    public static JPanel CreatePanel(String labelText, JComponent component) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.decode("#121212"));


        JLabel label = new JLabel(labelText, JLabel.CENTER);
        label.setForeground(Color.decode("#cccccc"));


        JPanel separator = new JPanel();
        separator.setPreferredSize(new Dimension(1, 50));  // Hauteur du séparateur
        separator.setBackground(Color.decode("#333333"));


        panel.add(label);
        panel.add(component);
        panel.add(separator);
        return panel;
    }

    // Méthode pour créer un panneau avec un bouton et son label
    public static JPanel createButtonPanel(String labelText, JButton button) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#121212"));
        panel.setLayout(new FlowLayout());

        // Créer le label
        JLabel label = new JLabel(labelText, JLabel.CENTER);
        label.setForeground(Color.decode("#cccccc"));



        // Créer un séparateur
        JPanel separator = new JPanel();
        separator.setPreferredSize(new Dimension(1, 50));  // Hauteur du séparateur
        separator.setBackground(Color.decode("#333333"));

        // Ajouter le label et le bouton
        panel.add(label);
        panel.add(button);
        panel.add(separator);

        return panel;
    }
}
