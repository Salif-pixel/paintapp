package panels;

import models.CustomButton;
import models.ToolItem;
import state.DrawingState;
import state.ToolBarState;

import javax.swing.*;
import java.awt.*;

public class ToolPanel extends JPanel implements ToolBarState.ToolBarStateListener {
    private String currentColor = "#cccccc";
    private String currentBgColor = "#121212";
    private JPanel rightWrapper;
    private JPanel leftWrapper ;
    private JPanel clearWrapper ;
    private JPanel eraseWrapper;
    private JPanel fillWrapper;
    private JPanel colorsWrapper ;
    private JPanel themeWrapper;
    public ToolPanel(DrawingState state, ToolBarState tool) {
        tool.addListener(this);
        ColorsPanel colorsPanel = new ColorsPanel(state, tool);
        BgColorsPanel bgColorsPanel = new BgColorsPanel(state, tool);
        ThemeColorsPanel themeColorsPanel = new ThemeColorsPanel(tool);
        this.setPreferredSize(new Dimension(1000,70));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.decode(currentBgColor));
        CustomButton clearButton = new CustomButton("C:/Users/DELL/IdeaProjects/paint2/src/icons/clear.png","clear",Color.decode("#121212"),Color.decode("#333333"),tool);
        CustomButton leftButton = new CustomButton("C:/Users/DELL/IdeaProjects/paint2/src/icons/left.png","left",Color.decode("#121212"),Color.decode("#333333"),tool);
        CustomButton rightButton = new CustomButton("C:/Users/DELL/IdeaProjects/paint2/src/icons/right.png","right",Color.decode("#121212"),Color.decode("#333333"),tool);
        CustomButton eraseButton = new CustomButton("C:/Users/DELL/IdeaProjects/paint2/src/icons/erase.png","erase",Color.decode("#121212"),Color.decode("#333333"),tool);


        clearButton.addActionListener(e -> state.clearDrawing());
        eraseButton.addActionListener(e -> state.setCurrentColor(Color.WHITE));
        leftButton.addActionListener(e -> state.undo());
        rightButton.addActionListener(e -> state.redo());



         rightWrapper = ToolItem.createButtonPanel("Right", rightButton);
         leftWrapper = ToolItem.createButtonPanel("Left", leftButton);
         clearWrapper = ToolItem.createButtonPanel("Clear", clearButton);
         eraseWrapper = ToolItem.createButtonPanel("Erase", eraseButton);
         fillWrapper = ToolItem.CreatePanel("Fill", bgColorsPanel);
         colorsWrapper = ToolItem.CreatePanel("Colors", colorsPanel);
         themeWrapper = ToolItem.CreatePanel("Theme", themeColorsPanel);




        this.add(leftWrapper);
        this.add(rightWrapper);
        this.add(clearWrapper);
        this.add(eraseWrapper);
        this.add(fillWrapper);
        this.add(colorsWrapper);
        this.add(themeWrapper);

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

        changeLabelColorInWrapper(rightWrapper, newColor);
        changeLabelColorInWrapper(leftWrapper, newColor);
        changeLabelColorInWrapper(clearWrapper, newColor);
        changeLabelColorInWrapper(eraseWrapper, newColor);
        changeLabelColorInWrapper(fillWrapper, newColor);
        changeLabelColorInWrapper(colorsWrapper, newColor);
        changeLabelColorInWrapper(themeWrapper, newColor);



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

        rightWrapper.setBackground(Color.decode(newColor));
        leftWrapper.setBackground(Color.decode(newColor));
        clearWrapper.setBackground(Color.decode(newColor));
        eraseWrapper.setBackground(Color.decode(newColor));
        fillWrapper.setBackground(Color.decode(newColor));
        colorsWrapper.setBackground(Color.decode(newColor));
        themeWrapper.setBackground(Color.decode(newColor));
    }
    private void changeLabelColorInWrapper(JPanel wrapper, String newColor) {

        Component[] components = wrapper.getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                label.setForeground(Color.decode(newColor));
            }
        }
    }
}

