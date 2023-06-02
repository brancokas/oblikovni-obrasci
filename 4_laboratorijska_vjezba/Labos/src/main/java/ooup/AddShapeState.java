package ooup;

public class AddShapeState implements State {
    private GraphicalObject prototype;
    private DocumentModel model;

    public AddShapeState(GraphicalObject prototype, DocumentModel model) {
        this.prototype = prototype;
        this.model = model;
    }

    @Override
    public void mouseDown(Point mousePoint, boolean shiftDown, boolean ctrlDown) {
        GraphicalObject object = prototype.duplicate();
        object.setHotPoint(0, mousePoint.translate(object.getHotPoint(0)));
        object.setHotPoint(1, mousePoint.translate(object.getHotPoint(1)));
        model.addGraphicalObject(object);
        // dupliciraj zapamćeni prototip, pomakni ga na poziciju miša i dodaj u model
    }

    @Override
    public void mouseUp(Point mousePoint, boolean shiftDown, boolean ctrlDown) {

    }

    @Override
    public void mouseDragged(Point mousePoint) {

    }

    @Override
    public void keyPressed(int keyCode) {

    }

    @Override
    public void afterDraw(Renderer r, GraphicalObject go) {

    }

    @Override
    public void afterDraw(Renderer r) {

    }

    @Override
    public void onLeaving() {

    }
}
