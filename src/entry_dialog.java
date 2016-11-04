import javax.swing.*;
import java.awt.*;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class entry_dialog extends JFrame {
    public entry_dialog() {
        entry_pane listPane = new entry_pane();
        add(listPane);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
