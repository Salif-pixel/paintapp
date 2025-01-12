package panels;

import models.ShapeBlock;
import state.DrawingState;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

public class DrawingPanel extends JPanel implements DrawingState.DrawingStateListener {
    private final Stack<BufferedImage> undoStack = new Stack<>();
    private final Stack<BufferedImage> redoStack = new Stack<>();
    private final ArrayList<ShapeBlock> blocks = new ArrayList<>();
    private ShapeBlock currentBlock;
    private int lastX, lastY;
    private Color currentColor = Color.BLACK;
    private BufferedImage image;
    private Graphics2D g2d;
    private DrawingState stateinstance;


    public DrawingPanel(DrawingState state) {
        stateinstance = state;
        state.addListener(this);
        this.setBackground(Color.WHITE);

        currentBlock = new ShapeBlock(currentColor);
        blocks.add(currentBlock);
        image = new BufferedImage(2000, 900, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addCircle(e.getX(), e.getY());
                lastX = e.getX();
                lastY = e.getY();
                saveToUndoStack();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentBlock = new ShapeBlock(currentColor);
                blocks.add(currentBlock);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int numCircles = (int) Math.hypot(x - lastX, y - lastY);
                for (int i = 1; i <= numCircles; i++) {
                    int intermediateX = (int) (lastX + i * (x - lastX) / (double) numCircles);
                    int intermediateY = (int) (lastY + i * (y - lastY) / (double) numCircles);
                    addCircle(intermediateX, intermediateY);
                }
                lastX = e.getX();
                lastY = e.getY();

            }
        });
    }

    private void addCircle(int x, int y) {
        Shape circle = new Ellipse2D.Float(x, y, stateinstance.getBrushSize(), stateinstance.getBrushSize());
        currentBlock.shapes.add(circle);
        g2d.setColor(currentBlock.color);
        g2d.fill(circle);
        g2d.draw(circle);
        repaint();

    }
    private void saveToUndoStack() {
        BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = copy.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        undoStack.push(copy);
    }
    @Override
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(image);
            image = undoStack.pop();
            g2d = image.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            repaint();
        }
    }
    @Override
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(image);
            image = redoStack.pop();
            g2d = image.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void onColorChange(Color newColor) {
        this.currentColor = newColor;
        currentBlock.color = newColor;
    }


    @Override
    public void onClear() {
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.setBackground(Color.WHITE);
        undoStack.clear();
        redoStack.clear();
        g2d.setColor(Color.WHITE);
        blocks.clear();
        currentBlock = new ShapeBlock(currentColor);
        blocks.add(currentBlock);
        repaint();
    }

    @Override
    public void onBgColorChange(Color newColor) {
        this.setBackground(newColor);
    }

}
