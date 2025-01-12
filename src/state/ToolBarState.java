package state;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ToolBarState {
    private String currentColor = "#ccccc";
    private String currentbgColor = "#121212";
    private final List<ToolBarState.ToolBarStateListener> listeners = new ArrayList<>();
    public String getCurrentColor() {
        return currentColor;
    }
    public String getCurrentbgColor() {
        return currentbgColor;
    }
    public void setCurrentbgColor(String newColor) {
        this.currentbgColor = newColor;
        notifyListenersBg();
    }
    public void setCurrentColor(String newColor) {
        this.currentColor = newColor;
        notifyListeners();
    }
    public void addListener(ToolBarState.ToolBarStateListener listener) {
        listeners.add(listener);
    }
    private void notifyListeners() {
        for (ToolBarState.ToolBarStateListener listener : listeners) {
            listener.onColorChange(currentColor);
        }
    }
    private void notifyListenersBg() {
        for (ToolBarState.ToolBarStateListener listener : listeners) {
            listener.onBgColorChange(currentbgColor);
        }
    }
    public interface ToolBarStateListener {
        void onColorChange(String newColor);
        void undo();
        void redo();
        void onBgColorChange(String newColor);
    }
}
