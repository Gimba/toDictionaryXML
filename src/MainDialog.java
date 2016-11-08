import javax.swing.*;
import java.awt.*;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class MainDialog extends JFrame {

    public MainDialog() {
        setSize(1200,500);

        InputPanel listPane = new InputPanel();

        EntryScrollPane indexList = new EntryScrollPane(listPane.id, listPane.value, listPane.title, listPane.text);

        add(indexList.scrollPane,BorderLayout.EAST);
        // pack();

        add(listPane, BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listPane.setIndexList(indexList);
    }

}
