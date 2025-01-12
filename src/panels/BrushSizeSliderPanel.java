package panels;

import state.DrawingState;
import state.ToolBarState;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class BrushSizeSliderPanel extends JPanel implements ToolBarState.ToolBarStateListener {
    private final JSlider slider;
    private String currentColor = "#cccccc";
    private String currentBgColor = "#121212";
    public void setCurrentColor(String color) {
        this.currentColor = color;
    }
    public void setCurrentBgColor(String color) {
        this.currentBgColor = color;
    }
    public BrushSizeSliderPanel(DrawingState state,ToolBarState tool) {
        tool.addListener(this);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.decode(currentBgColor));

        // Label
        JLabel label = new JLabel("Brush Size", JLabel.CENTER);
        label.setForeground(Color.decode(currentColor));

        // Slider configuration
        slider = new JSlider(JSlider.VERTICAL, 1, 80, 10);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(Color.decode(currentBgColor));
        slider.setForeground(Color.decode(currentColor));


        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newSize = slider.getValue();
                state.setBrushSize(newSize);
            }
        });

        // Add components to the panel
        this.add(label, BorderLayout.NORTH);
        this.add(slider, BorderLayout.CENTER);
    }

    @Override
    public void onColorChange(String newColor) {
        setCurrentColor(newColor);
        this.setForeground(Color.decode(newColor));
        slider.setForeground(Color.decode(newColor));
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
        slider.setBackground(Color.decode(newColor));
    }
}
