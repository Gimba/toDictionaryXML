import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class entry_pane extends JPanel {
    public entry_pane() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        JTextArea text = new JTextArea("initial velocity", 10, 3);
        JTextField id = new JTextField("id", 10);
        JTextField title = new JTextField("title", 10);
        JTextField value = new JTextField("value", 10);


        myButton add = new myButton("add");
        myButton finish = new myButton("finish");
        myButton init_list = new myButton("<ul>");
        myButton end_list = new myButton("</ul>");
        myButton init_item = new myButton("<li>");
        myButton end_item = new myButton("</li>");
        JPanel controls = new JPanel();
        controls.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();

        JPanel entry_list = new JPanel();
        entry_list.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();

        JList list = new JList();



        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(id, c);
        c.gridx = 2;
        c.gridy = 1;
        add(title);
        c.gridx = 3;
        c.gridy = 1;
        add(value);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        add(text, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        add(add, c);
        c.gridx = 1;
        c.gridy = 3;
        add(finish, c);
        c.gridx = 4;
        c.gridy = 2;
        add(controls,c);

        c1.gridx = 0;
        c1.gridy = 0;
        controls.add(init_list,c1);
        c1.gridx = 0;
        c1.gridy = 1;
        controls.add(end_list,c1);
        c1.gridx = 0;
        c1.gridy = 2;
        controls.add(init_item,c1);
        c1.gridx = 0;
        c1.gridy = 3;
        controls.add(end_item,c1);

        c.gridx = 5;
        c.gridy = 1;
        c.gridheight = 3;
        add(entry_list,c);
        c.gridheight = 1;


        try {

            entry_reader xml_reader = new entry_reader();
            String[] lines = xml_reader.string_array;
            list = new JList(lines);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        c2.gridx = 0;
        c2.gridy = 0;
        entry_list.add(list,c2);

        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    new entry_generator(id.getText(), title.getText(), value.getText(), text.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        init_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text.replaceSelection("");
                text.insert("\n<ul>\n", text.getCaretPosition());
            }
        });
        end_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text.replaceSelection("");
                text.insert("\n</ul>\n", text.getCaretPosition());
            }
        });
        init_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text.replaceSelection("");
                text.insert("<li>", text.getCaretPosition());
            }
        });
        end_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text.replaceSelection("");
                text.insert("</li>", text.getCaretPosition());
            }
        });
    }
}
