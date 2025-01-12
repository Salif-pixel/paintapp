package panels;

import models.CustomButton;
import state.DrawingState;
import state.ToolBarState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemeColorsPanel  extends JPanel implements ToolBarState.ToolBarStateListener {
        private String currentColor = "#3333333";
        private String currentBgColor = "#121212";
        private final String[][] themes = {
                {"#121212", "#cccccc"},
                {"#1F1B24", "#ffffff"},
                {"#191919", "#2C3E50"},
                {"#12171a", "#F0F8FF"},
                {"#0c0c04", "#FFFFFF"},
                {"#FFFFFF", "#2F4F4F"},
                {"#262b2f", "#a4d4c4"},
                {"#04140c", "#f4d47c"},
                {"#0d0d0d", "#D3D3D3"},
                {"#141414", "#8cf4e5"},
        };

        public ThemeColorsPanel(ToolBarState tool) {
            tool.addListener(this);
            this.setBackground(Color.decode(currentBgColor));

            CustomButton thButton = new CustomButton("C:/Users/DELL/IdeaProjects/paint2/src/icons/theme.png","theme",Color.decode(currentBgColor),Color.decode(currentColor),tool);
            this.add(thButton);
            JPanel LayoutBg = new JPanel();
            LayoutBg.setLayout(new GridLayout(2, 5));

            // Créer un bouton pour chaque thème
            for (int i = 0; i < themes.length; i++) {
                JButton themeButton = new JButton("Theme " + (i + 1));
                themeButton.setBackground(Color.decode(themes[i][0]));
                themeButton.setForeground(Color.decode(themes[i][1]));
                themeButton.setBorder(BorderFactory.createLineBorder(Color.decode(tool.getCurrentbgColor()), 2));
                themeButton.setPreferredSize(new Dimension(25, 25));
                themeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                LayoutBg.add(themeButton);


                int finalI = i;
                themeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        tool.setCurrentbgColor(themes[finalI][0]);
                        tool.setCurrentColor(themes[finalI][1]);

                    }
                });
            }

            this.add(LayoutBg);
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


