package ooup;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        init();
    }

    private void init() {
        getContentPane().add(new TextEditor(new TextEditorModel("OVO je moj\n\nmodel123\n\naaa")));
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
