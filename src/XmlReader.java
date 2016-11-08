import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class XmlReader {
    File file = new File("output/entries.xml");

    NodeList eList;
    NodeList iList;


    private List<String[]> list;
    String xmlString;

    public XmlReader() throws FileNotFoundException {

        loadData();
    }

    // get text with styling tags from xml
    public static String innerXml(Node node) {
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

    // List to ObjectArray
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

            iList = doc.getElementsByTagName("d:index");
            eList = doc.getElementsByTagName("d:entry");

            XmlWriter eg = new XmlWriter();

            list = toList(eList, iList);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Object[][] getTableData(){
        String[][] tableDataArray = new String[list.size()][2];
        for(int i = 0; i <tableDataArray.length; i++){
            tableDataArray[i][0] = list.get(i)[0];
            tableDataArray[i][1] = list.get(i)[1];
        }
        return tableDataArray;
    }

    public String getID(int i){
        return list.get(i)[0];
    }

    public String getTitle(int i){
        return list.get(i)[1];
    }

    public String getValue(int i){
        return list.get(i)[2];
    }

    public String getText(int i){
        return trimLines(list.get(i)[3]);
    }

    public List<String[]> toList(NodeList eList, NodeList iList){
        List<String[]> outList = new LinkedList<>();
        for (int i = 0; i < eList.getLength(); i++){
            Element entry = (Element) eList.item(i);
            Element value = (Element) iList.item(i);
            outList.add(new String[]{entry.getAttribute("id"), entry.getAttribute("d:title"), value.getAttribute("d:value"), XmlReader.innerXml(entry)});
        }
        return outList;
    }

    public void addToList(String id, String title, String value, String text){
        list.add(new String[]{id, title,value,text});
    }

    public void updateListItem(int index, String id, String title, String value, String text){
        list.set(index, new String[]{id, title,value,text});
    }

    public List getList(){
        return list;
    }

    public void updateEntry(int index, String[] values){
        list.set(index, values);
    }

}
