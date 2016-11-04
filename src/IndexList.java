import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import static java.awt.SystemColor.text;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class IndexList {
    JPanel entry_list = new JPanel();
    JScrollPane scrollPane;
    entry_reader xml_reader;
    public IndexList(){


        // entry_list.setLayout(new GridBagLayout());
        // GridBagConstraints c2 = new GridBagConstraints();

        JScrollPane scrollPane = new JScrollPane(entry_list);
        scrollPane.setSize(100,200);

        JList list = new JList();

        try {

            this.xml_reader = new entry_reader();
            // String[] lines = xml_reader.string_array;
            list = new JList(xml_reader.string_array);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        entry_list.add(list);
        this.scrollPane = new JScrollPane(entry_list);

        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list_temp = (JList)e.getComponent();
                int index = list_temp.locationToIndex(e.getPoint());
                System.out.print(index);
                System.out.println(xml_reader.string_array[index]);

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }
}
