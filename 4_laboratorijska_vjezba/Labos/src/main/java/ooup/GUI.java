package ooup;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GUI extends JFrame {
    private State currentState = new IdleState();
    private List<ToolButton> toolButtons = new ArrayList<>();
    private Canvas canvas;
    private DocumentModel model;
    private List<GraphicalObject> objects;

    public GUI(List<GraphicalObject> graphicalObjects) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        objects = graphicalObjects;
        setLocation(100,100);
        setSize(500, 500);

        initGui();
    }

    private void initGui() {

        Container pane = getContentPane();

        model = new DocumentModel();
        canvas = new Canvas();

        JToolBar toolBar = new JToolBar();

        addButtons(toolBar, objects);

        toolBar.addSeparator();


        pane.add(toolBar, BorderLayout.NORTH);
        pane.add(canvas, BorderLayout.CENTER);


    }


    private void addButtons(JToolBar toolBar, List<GraphicalObject> graphicalObjects) {
        for (GraphicalObject object : graphicalObjects) {
            toolButtons.add(new ToolButton(new AddShapeState(object, model), object.getShapeName()));
        }
        toolButtons.add(new ToolButton(new SelectShapeState(model), "Selektiraj"));
        toolButtons.forEach(toolButton -> toolBar.add(toolButton));
    }

    private class ToolButton extends JButton {

        public ToolButton(State state, String name) {
            super(name);
            addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentState = state;
                }
            });
        }
    }

    private class Canvas extends JComponent {
        public Canvas() {
            currentState = new IdleState();
            model.addDocumentModelListener(() -> repaint());
            setFocusable(true);
            initializeMouseEvents();
            initializeMouseDragEvents();
            initializeKeyPressed();
        }

        private void initializeKeyPressed() {
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    currentState.keyPressed(e.getKeyCode());
                }
            });
        }

        private void initializeMouseDragEvents() {
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    currentState.mouseDragged(new Point(e.getX(), e.getY()));
                }
            });
        }

        private void initializeMouseEvents() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    currentState.mouseUp(
                            new Point(e.getX(), e.getY()),
                            e.isShiftDown(),
                            e.isControlDown()
                    );
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    currentState.mouseDown(
                            new Point(e.getX(), e.getY()),
                            e.isShiftDown(),
                            e.isControlDown()
                    );
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D graphics2D = (Graphics2D) g;

            Renderer renderer = new G2DRendererImpl(graphics2D);
            List<GraphicalObject> objects = model.list();
            for (GraphicalObject object : objects) {
                object.render(renderer);
                currentState.afterDraw(renderer, object);
            }
            currentState.afterDraw(renderer);
        }
    }
}
