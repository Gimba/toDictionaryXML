import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class entry_pane extends JPanel {
    JTextField id;
    JTextField title;
    JTextField value;
    JTextArea text;

    IndexList indexList;
    public entry_pane() {
        this.indexList = indexList;
        setLayout(new BorderLayout());
        // xml fields that can be an input
        JPanel inputFields = new JPanel(new FlowLayout());

        id = new JTextField("id", 10);
        title = new JTextField("title", 10);
        value = new JTextField("value", 10);

        inputFields.add(id, FlowLayout.LEFT);
        inputFields.add(title, FlowLayout.LEFT);
        inputFields.add(value, FlowLayout.LEFT);

        add(inputFields, BorderLayout.NORTH);

        // text field
        JPanel textPanel = new JPanel(new BorderLayout());
        text = new JTextArea("initial velocity", 10, 3);
        textPanel.setSize(200,200);
        textPanel.add(text);
        add(textPanel,BorderLayout.CENTER);

        // control over xml list tags
        JPanel tagControls = new JPanel();
        tagControls.setLayout(new BoxLayout(tagControls, BoxLayout.PAGE_AXIS));

        myButton init_list = new myButton("<ul>");
        // myButton end_list = new myButton("</ul>");
        myButton init_item = new myButton("<li>");
        // myButton end_item = new myButton("</li>");

        tagControls.add(init_list);
        //tagControls.add(end_list);
        tagControls.add(init_item);
        //tagControls.add(end_item);

        add(tagControls, BorderLayout.EAST);

        // save entry and finish button
        JPanel fileControl = new JPanel();

        myButton add = new myButton("Add");
        myButton finish = new myButton("Finish");

        fileControl.add(add);
        fileControl.add(finish);

        add(fileControl, BorderLayout.SOUTH);


        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    entry_generator eg =new entry_generator(id.getText(), title.getText(), value.getText(), text.getText());
                    indexList.xml_reader.loadData();
                    indexList.data = indexList.xml_reader.toObjectArray(indexList.xml_reader.entriesList);
                    System.out.println(indexList.data.length);
                    DefaultTableModel model = new DefaultTableModel(indexList.data, indexList.columnNames);
                    indexList.table.setModel(model);
                    indexList.table.repaint();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {



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

    public void setIndexList(IndexList indexList) {
        this.indexList = indexList;
    }
}
