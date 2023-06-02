package ooup;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<GraphicalObject> graphicalObjects = new ArrayList<>();

        graphicalObjects.add(new LineSegment());
        graphicalObjects.add(new Oval());

        SwingUtilities.invokeLater(() -> new GUI(graphicalObjects).setVisible(true));
    }
}
