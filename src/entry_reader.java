import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class entry_reader  {
    List<String[]> entriesList = new ArrayList<>();
    NodeList eList;
    NodeList iList;
    String [] textList;
    String xmlString;
    File file = new File("output/entries.xml");
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
        loadData();


    }

    // get text with styling tags from xml
    public String innerXml(Node node) {
        DOMImplementationLS lsImpl = (DOMImplementationLS)node.getOwnerDocument().getImplementation().getFeature("LS", "3.0");
        LSSerializer lsSerializer = lsImpl.createLSSerializer();
        lsSerializer.getDomConfig().setParameter("xml-declaration", false);
        NodeList childNodes = node.getChildNodes();
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < childNodes.getLength(); i++) {
            sb.append(lsSerializer.writeToString(childNodes.item(i)));
        }

        return sb.toString();
    }

    // remove trailing and leading whitespaces
    public String trimLines (String text){
        String[] lines = text.split(System.getProperty("line.separator"));
        String out = "";
        // i = 1 to remove first blank line
        for (int i = 1; i < lines.length; i++){
            out = out + lines[i].trim() + "\n";
        }
        return out;
    }

    // ArrayList to ObjectArray
    public Object[][] toObjectArray(List list){
        Object[][] array = new String[list.size()][2];
        for(int i = 0; i <list.size(); i++){
            array[i][0] = ((Object[])list.get(i))[0];
            array[i][1] = ((Object[])list.get(i))[1];
        }
        return array;
    }


    public void loadData (){

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

            entry_generator eg = new entry_generator();
            eg.nodeListToString(eList, iList);
            eg.addDocTags(eg.nodeListToString(eList, iList));

            textList = new String[eList.getLength()];

            for (int i = 0; i < eList.getLength(); i++){
                Node nNode = eList.item(i);
                String temp = innerXml(nNode);
                temp = trimLines(temp);
                textList[i] = temp;
                Element eElement = (Element) nNode;
                entriesList.add(new String []{eElement.getAttribute("id"),eElement.getAttribute("d:title")});

//                System.out.println(eElement.getAttribute("id"));
//                System.out.println(eElement.getAttribute("d:title"));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
