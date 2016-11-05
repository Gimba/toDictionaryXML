import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class IndexList {
    JPanel entry_list = new JPanel();
    JScrollPane scrollPane;
    entry_reader xml_reader;
    JTextField id;
    JTextField value;
    JTextField title;
    JTextArea text;
    public IndexList(JTextField id, JTextField value, JTextField title, JTextArea text){
        this.id = id;
        this.value = value;
        this.title = title;
        this.text = text;

        // entry_list.setLayout(new GridBagLayout());
        // GridBagConstraints c2 = new GridBagConstraints();
        JList list = new JList();

        try {

            this.xml_reader = new entry_reader();
            // String[] lines = xml_reader.entriesList;
            list = new JList(xml_reader.entriesList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        entry_list.add(list);

        scrollPane = new JScrollPane(entry_list,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(100,200);

        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list_temp = (JList)e.getComponent();
                int index = list_temp.locationToIndex(e.getPoint());
                System.out.print(index);
                System.out.println(xml_reader.eList.item(index));
                Node eNode = xml_reader.eList.item(index);
                Element eElement = (Element) eNode;
                text.setText(xml_reader.textList[index]);


                id.setText(eElement.getAttribute("id"));
                title.setText(eElement.getAttribute("d:title"));

                Node iNode = xml_reader.iList.item(index);
                Element iElement = (Element) iNode;
                value.setText(iElement.getAttribute("d:value"));
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
