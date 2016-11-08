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

        IndexList indexList = new IndexList(listPane.id, listPane.value, listPane.title, listPane.text);

        add(indexList.scrollPane,BorderLayout.EAST);
        // pack();

        add(listPane, BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listPane.setIndexList(indexList);
    }

}
