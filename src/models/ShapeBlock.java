package models;

import java.awt.*;
import java.util.ArrayList;

public class ShapeBlock {
    public Color color;
    public ArrayList<Shape> shapes;

    public ShapeBlock(Color color) {
        this.color = color;
        this.shapes = new ArrayList<>();
    }
}
