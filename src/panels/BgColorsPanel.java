package panels;


import models.CustomButton;
import state.DrawingState;
import state.ToolBarState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BgColorsPanel extends JPanel   implements ToolBarState.ToolBarStateListener {
    private String currentColor = "#3333333";
    private String currentBgColor = "#121212";
    public BgColorsPanel(DrawingState drawingState , ToolBarState tool) {
        tool.addListener(this);
        this.setBackground(Color.decode("#121212"));
        JPanel LayoutBg = new JPanel();
        LayoutBg.setLayout(new GridLayout(2, 5));
        CustomButton bgButton = new CustomButton("C:/Users/DELL/IdeaProjects/paint2/src/icons/font.png","fill",Color.decode("#121212"),Color.decode("#333333"),tool);
        this.add(bgButton);
        this.add(LayoutBg);

        Color[] colors = {
                Color.WHITE,
                Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.BLACK, Color.ORANGE,
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
            LayoutBg.add(buttons[i]);
            int finalI = i;
            buttons[i].addActionListener(e -> drawingState.setCurrentbgColor(colors[finalI]));

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
