package window;

import panels.BrushSizeSliderPanel;
import panels.ColorsPanel;
import panels.DrawingPanel;
import panels.ToolPanel;
import state.DrawingState;
import state.ToolBarState;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        this.setTitle("Drawing App");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawingState state = new DrawingState();
        ToolBarState tool = new ToolBarState();
        JPanel BodyPanel = new JPanel();
        BrushSizeSliderPanel brushSizeSliderPanel = new BrushSizeSliderPanel(state,tool);
        BodyPanel.setLayout(new BorderLayout());

        DrawingPanel drawingPanel = new DrawingPanel(state);
        drawingPanel.setPreferredSize(new Dimension(1000, 700));
        drawingPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#444444"), 50));
        ToolPanel toolPanel = new ToolPanel(state,tool);
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        controlPanel.setPreferredSize(new Dimension(115, 200));
        controlPanel.setBackground(Color.decode("#121212"));
        controlPanel.add(brushSizeSliderPanel, BorderLayout.CENTER);
        BodyPanel.setBackground(Color.decode("#1F1B24"));
        BodyPanel.add(controlPanel, BorderLayout.WEST);
        BodyPanel.add(drawingPanel, BorderLayout.CENTER);


        this.setLayout(new BorderLayout());
        this.add(toolPanel, BorderLayout.NORTH);
        this.add(BodyPanel);
        this.setVisible(true);

    }
    public static void main(String[] args) {
        new MainWindow();
    }


}
