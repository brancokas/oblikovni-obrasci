package ooup;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;

public class TextEditor extends JComponent {
    private TextEditorModel model;

    public TextEditor(TextEditorModel model) {
        this.model = model;


    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.black);

        FontMetrics fm = g.getFontMetrics();
        int y = fm.getAscent();

        Iterator<String> iterator = model.allLines();;

        while (iterator.hasNext()) {
            g.drawString(iterator.next(), 0, y);
            y += fm.getHeight();
        }

        g.setColor(Color.RED);
        TextEditorModel.Location location = model.getCursorLocation();

        int width = 0;
        String s = model.getLines().get(location.y);
        for (int i = 0; i < location.x; i++) {
            width += fm.charWidth(s.charAt(i));
        }

        g.drawLine(width, (location.y+1) * fm.getHeight(),
                width, location.y * fm.getHeight());

    }


}
