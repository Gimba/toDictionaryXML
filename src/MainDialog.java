import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class MainDialog extends JFrame {

    public MainDialog() {
        setSize(1200,500);

        XmlReader xmlReader = null;
        try {
            xmlReader = new XmlReader();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputPanel inputPanel = new InputPanel(xmlReader);

        EntryScrollPane indexList = new EntryScrollPane(xmlReader, inputPanel.id, inputPanel.value, inputPanel.title, inputPanel.text);

        inputPanel.setIndexList(indexList);

        add(indexList.scrollPane,BorderLayout.EAST);
        // pack();

        add(inputPanel, BorderLayout.CENTER);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputPanel.setIndexList(indexList);
    }

}
