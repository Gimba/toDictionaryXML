import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class InputPanel extends JPanel {
    JTextField id;
    JTextField title;
    JTextField value;
    JTextArea text;
    EntryScrollPane entryScrollPane;
    public InputPanel(XmlReader xmlReader) {
        setLayout(new BorderLayout());
        // xml fields that can be an input
        JPanel inputFields = new JPanel(new FlowLayout());

        id = new JTextField("id", 10);
        title = new JTextField("title", 10);
        value = new JTextField("value", 10);

        JLabel id_label = new JLabel("ID");
        JLabel title_label = new JLabel("Title");
        JLabel value_label = new JLabel("Value");

        inputFields.add(value, FlowLayout.LEFT);
        inputFields.add(value_label, FlowLayout.LEFT);
        inputFields.add(title, FlowLayout.LEFT);
        inputFields.add(title_label, FlowLayout.LEFT);
        inputFields.add(id, FlowLayout.LEFT);
        inputFields.add(id_label, FlowLayout.LEFT);


        add(inputFields, BorderLayout.NORTH);

        // text field
        JPanel textPanel = new JPanel(new BorderLayout());
        text = new JTextArea();
        text.setLineWrap(true);
        textPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        textPanel.setSize(200,200);
        textPanel.add(text);

        add(textPanel,BorderLayout.CENTER);

        // control over xml list tags
        JPanel tagControls = new JPanel();
        tagControls.setLayout(new BoxLayout(tagControls, BoxLayout.PAGE_AXIS));

        MyButton init_list = new MyButton("<ul>");
        // MyButton end_list = new MyButton("</ul>");
        MyButton init_item = new MyButton("<li>");
        // MyButton end_item = new MyButton("</li>");

        tagControls.add(init_list);
        //tagControls.add(end_list);
        tagControls.add(init_item);
        //tagControls.add(end_item);

        add(tagControls, BorderLayout.EAST);

        // save entry and finish button
        JPanel fileControl = new JPanel();

        MyButton add = new MyButton("Add");
        MyButton updateEntry = new MyButton("Update Entry");

        fileControl.add(add);
        fileControl.add(updateEntry);

        add(fileControl, BorderLayout.SOUTH);


        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                    XmlWriter eg =new XmlWriter();
                    xmlReader.addToList(id.getText(), title.getText(), value.getText(), text.getText());
                DefaultTableModel model = (DefaultTableModel) entryScrollPane.table.getModel();
                model.addRow(new Object[]{id.getText(), title.getText()});


                eg.writeXML(xmlReader.getList());
            }
        });

        updateEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XmlWriter eg =new XmlWriter();
                int selected = entryScrollPane.table.getSelectionModel().getLeadSelectionIndex();

                xmlReader.updateEntry(selected, new String[]{id.getText(), title.getText(), value.getText(), text.getText()});

                DefaultTableModel model = (DefaultTableModel) entryScrollPane.table.getModel();
                entryScrollPane.table.setValueAt(id.getText(),selected,0);
                entryScrollPane.table.setValueAt(title.getText(),selected,1);

                eg.writeXML(xmlReader.getList());


            }
        });

        init_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text.replaceSelection("");
                text.insert("\n<ul>\n\n</ul>", text.getCaretPosition());
                text.setCaretPosition(text.getCaretPosition()-6);
            }
        });
//        end_list.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                text.replaceSelection("");
//                text.insert("\n</ul>\n", text.getCaretPosition());
//            }
//        });
        init_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text.replaceSelection("");
                text.insert("<li></li>", text.getCaretPosition());
                text.setCaretPosition(text.getCaretPosition()-5);
            }
        });
//        end_item.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                text.replaceSelection("");
//                text.insert("</li>", text.getCaretPosition());
//            }
//        });
    }

    public void setIndexList(EntryScrollPane entryScrollPane) {
        this.entryScrollPane = entryScrollPane;
    }
}
