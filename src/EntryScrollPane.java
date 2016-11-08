import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class EntryScrollPane {
    JPanel entry_list = new JPanel();
    JScrollPane scrollPane;
    XmlReader xml_reader;
    JTextField id;
    JTextField value;
    JTextField title;
    JTextArea text;
    String[] columnNames = {"id",
            "title"};
    JTable table = new JTable();
    Object [][] data;
    public EntryScrollPane(JTextField id, JTextField value, JTextField title, JTextArea text){
        this.id = id;
        this.value = value;
        this.title = title;
        this.text = text;

        // entry_list.setLayout(new GridBagLayout());
        // GridBagConstraints c2 = new GridBagConstraints();
//        JTable table = new JTable();

        try {

            this.xml_reader = new XmlReader();
            // String[] lines = xml_reader.entriesList;
            data = xml_reader.getTableData();
            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            table = new JTable();
            table.setModel(model);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        entry_list.add(table);

        scrollPane = new JScrollPane(entry_list,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setSize(100,200);

        table.getSelectionModel().addListSelectionListener(new RowListener());
        

    }
    private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }

            //System.out.println(table.getSelectionModel().getLeadSelectionIndex());
            int index = table.getSelectionModel().getLeadSelectionIndex();

            //System.out.println(xml_reader.eList.item(index));
            Node eNode = xml_reader.eList.item(index);
            Element eElement = (Element) eNode;
            text.setText(xml_reader.textList[index]);


            id.setText(eElement.getAttribute("id"));
            title.setText(eElement.getAttribute("d:title"));

            Node iNode = xml_reader.iList.item(index);
            Element iElement = (Element) iNode;
            value.setText(iElement.getAttribute("d:value"));

            //xml_reader.entriesList.remove(index);
            //data = xml_reader.toObjectArray(xml_reader.entriesList);
            // System.out.println(data.length);

        }
    }
}
