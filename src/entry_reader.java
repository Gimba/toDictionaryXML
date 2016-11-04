import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class entry_reader  {
    String [] entriesList;
    NodeList eList;
    NodeList iList;
    String [] textList;
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
//            // entriesList = new String[line_list.size()];
//            entriesList = line_list.toArray(new String[0]);
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

            //Node n = doc.getFirstChild();
            //NodeList nl = n.getChildNodes();
            //System.out.println(doc.getDocumentElement());

            iList = doc.getElementsByTagName("d:index");
            eList = doc.getElementsByTagName("d:entry");
            // System.out.print(eList.getLength());
            entriesList = new String[eList.getLength()];
            textList = new String[eList.getLength()];

            for (int i = 0; i < eList.getLength(); i++){
                Node nNode = eList.item(i);
                textList[i] = nNode.getTextContent();
                Element eElement = (Element) nNode;
                entriesList[i]= eElement.getAttribute("id") + "   :   " + eElement.getAttribute("d:title");
//                System.out.println(eElement.getAttribute("id"));
//                System.out.println(eElement.getAttribute("d:title"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
