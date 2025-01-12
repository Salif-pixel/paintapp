package panels;


import models.CustomButton;
import state.DrawingState;
import state.ToolBarState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorsPanel extends JPanel    implements ToolBarState.ToolBarStateListener {
    private String currentColor = "#3333333";
    private String currentBgColor = "#121212";
    public ColorsPanel(DrawingState drawingState, ToolBarState tool) {
        tool.addListener(this);
        this.setBackground(Color.decode("#121212"));
        JPanel Layout = new JPanel();
        Layout.setLayout(new GridLayout(2, 5));
        CustomButton bgButton = new CustomButton("C:/Users/DELL/IdeaProjects/paint2/src/icons/paint.png","color",Color.decode("#121212"),Color.decode("#333333"),tool);
        this.add(bgButton);
        this.add(Layout);
        Color[] colors = {
                Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE,
                Color.PINK, Color.CYAN, Color.MAGENTA, Color.GRAY, Color.LIGHT_GRAY
        };
        JButton[] buttons = new JButton[colors.length];
        for (int i = 0; i < colors.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(colors[i]);
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.decode("#121212"), 2));
            buttons[i].setPreferredSize(new Dimension(25, 25));
            buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            Layout.add(buttons[i]);
            int finalI = i;
            buttons[i].addActionListener(e -> drawingState.setCurrentColor(colors[finalI]));

        }

    }


    public void setCurrentColor(String color) {
        this.currentColor = color;
    }
    public void setCurrentBgColor(String color) {
        this.currentBgColor = color;
    }
    @Override
    public void onColorChange(String newColor) {
        setCurrentColor(newColor);
        this.setForeground(Color.decode(newColor));
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public void onBgColorChange(String newColor) {
        setCurrentBgColor(newColor);
        this.setBackground(Color.decode(newColor));
    }

}
