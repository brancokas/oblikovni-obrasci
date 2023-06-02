package ooup;

import java.util.*;

public class TextEditorModel {
    private int x, y;
    private List<String> lines = new LinkedList<>();
    private LocationRange selectionRange;
    private Location cursorLocation;
    private List<CursorObserver> cursorObservers = new ArrayList<>();
    private List<TextObserver> textObservers = new ArrayList<>();

    public static class Location {
        int x, y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public class LocationRange {
        Location l1, l2;
    }

    public TextEditorModel() {}

    public void addTextObserver(TextObserver observer) {
        textObservers.add(observer);
    }

    public void removeTextObserver(TextObserver observer) {
        textObservers.remove(observer);
    }

    public void addCursorObserver(CursorObserver observer) {
        cursorObservers.add(observer);
    }

    public void removeCursorObserver(CursorObserver observer) {
        cursorObservers.remove(observer);
    }

    public void notifyCursors() {
        for (CursorObserver observer : cursorObservers)
            observer.updateCursorLocation(cursorLocation);
    }

    public TextEditorModel(String text) {
        String s = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                lines.add(s);
                s = "";
            } else {
                s += text.charAt(i);
            }
        }
        lines.add(s);

        int size = lines.size();
        cursorLocation = new Location(lines.get(size-1).length(), size-1);

    }

    public List<String> getLines() {
        return lines;
    }

    public Iterator<String> allLines() {
        return lines.iterator();
    }


    public Location getCursorLocation() {
        return cursorLocation;
    }

    public void moveCursorRight() {}

    public void moveCursorLeft() {}

    public void moveCursorUp() {}

    public void moveCursorDown() {}


}
