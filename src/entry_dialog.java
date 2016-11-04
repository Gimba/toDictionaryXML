import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class entry_dialog extends JFrame {

    public entry_dialog() {
        setSize(1200,500);

        entry_pane listPane = new entry_pane();
        add(listPane, BorderLayout.CENTER);

        IndexList indexList = new IndexList(listPane.id, listPane.value, listPane.title, listPane.text);

        add(indexList.scrollPane,BorderLayout.EAST);
        // pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
