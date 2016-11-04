import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class entry_reader  {
    String [] string_array;

    public entry_reader() throws FileNotFoundException {
//        FileReader fr = new FileReader("output/entries.xml");
//
//        BufferedReader bf = new BufferedReader(fr);
//
//
//        try {
//            String line;
//            List<String> line_list = new LinkedList<String>();
//            while ((line = bf.readLine()) != null){
//                line_list.add(line);
//            }
//            // string_array = new String[line_list.size()];
//            string_array = line_list.toArray(new String[0]);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        File file = new File("output/entries.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("d:entry");
            System.out.print(nList.getLength());
            string_array = new String[nList.getLength()];

            for (int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                Element eElement = (Element) nNode;
                string_array[i]= eElement.getAttribute("id") + "   :   " + eElement.getAttribute("d:title");
//                System.out.println(eElement.getAttribute("id"));
//                System.out.println(eElement.getAttribute("d:title"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
