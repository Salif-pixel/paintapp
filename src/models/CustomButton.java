package models;


import state.ToolBarState;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton implements ToolBarState.ToolBarStateListener {
    private String currentColor = "#333333";
    private String currentBgColor = "#121212";
    public CustomButton(String iconPath, String tooltip, Color backgroundColor, Color foregroundColor, ToolBarState toolBarState) {
        super();
        toolBarState.addListener(this);


        if (iconPath != null && !iconPath.isEmpty()) {
            ImageIcon icon = new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
            this.setIcon(icon);
        }


        this.setToolTipText(tooltip);
        this.setMargin(new Insets(10, 20, 10, 20));
        this.setBackground(backgroundColor != null ? backgroundColor : Color.decode(currentColor));
        this.setForeground(foregroundColor != null ? foregroundColor : Color.decode(currentBgColor));
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setFocusable(false);
        this.setOpaque(true);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public CustomButton(String tooltip, Color backgroundColor, Color foregroundColor) {
        this(null, tooltip, backgroundColor, foregroundColor, null);
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
