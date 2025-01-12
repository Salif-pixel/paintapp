package state;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class DrawingState {
    private Color currentColor = Color.BLACK;
    private Color currentbgColor = Color.WHITE;
    private int brushSize = 20;
    private final List<DrawingStateListener> listeners = new ArrayList<>();
    public int getBrushSize() {
        return brushSize;
    }

    public Color getCurrentColor() {
        return currentColor;
    }
    public Color getCurrentbgColor() {
        return currentbgColor;
    }
    public void setCurrentbgColor(Color newColor) {
        this.currentbgColor = newColor;
        notifyListenersBg();
    }

    public void setCurrentColor(Color newColor) {
        this.currentColor = newColor;
        notifyListeners();
    }

    public void clearDrawing() {
        for (DrawingStateListener listener : listeners) {
            listener.onClear();
        }
    }

    public void addListener(DrawingStateListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (DrawingStateListener listener : listeners) {
            listener.onColorChange(currentColor);

        }
    }
    private void notifyListenersBg() {
        for (DrawingStateListener listener : listeners) {
            listener.onBgColorChange(currentbgColor);

        }
    }

    public void setBrushSize(int newSize) {
        this.brushSize = newSize;
    }
    public void undo() {
        for (DrawingStateListener listener : listeners) {
            listener.undo();
        }
    }
    public void redo() {
        for (DrawingStateListener listener : listeners) {
            listener.redo();
        }
    }

    public interface DrawingStateListener {
        void onColorChange(Color newColor);
        void onClear();
        void undo();
        void redo();
        void onBgColorChange(Color newColor);
    }
}
