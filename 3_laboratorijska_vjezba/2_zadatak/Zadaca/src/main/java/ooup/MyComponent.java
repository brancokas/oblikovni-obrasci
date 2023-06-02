package ooup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class MyComponent extends JComponent {
    private int x, y;
    public MyComponent() {}

    public MyComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawLine(x, y, x, y+10);
        g.drawLine(x, (y + y+10)/2, x + 10, (y + y+10)/2);

        FontMetrics fm = g.getFontMetrics();
        int startx = x;
        int starty = y + 10;
        g.drawString("Text1", startx, starty + fm.getAscent());
        g.drawString("Text2", startx, starty + fm.getAscent() + fm.getHeight());

        Action close = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame frame = (JFrame) SwingUtilities.getRoot(component);
                frame.dispose();
            }
        };

        getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "close");
        getActionMap().put("close", close);

    }
}
