import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class entry_dialog extends JFrame {
    public entry_dialog() {
        setSize(800,500);

        entry_pane listPane = new entry_pane();
        add(listPane, BorderLayout.CENTER);

        JPanel entry_list = new JPanel();
        entry_list.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();

        JScrollPane scrollPane = new JScrollPane(entry_list);
        scrollPane.setSize(200,200);

        JList list = new JList();

        try {

            entry_reader xml_reader = new entry_reader();
            // String[] lines = xml_reader.string_array;
            list = new JList(xml_reader.string_array);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        c2.gridx = 0;
        c2.gridy = 0;
        entry_list.add(list,c2);

        add(scrollPane,BorderLayout.EAST);
        // pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
