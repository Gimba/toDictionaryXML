import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class entry_generator {
    String fileName = "output/entries.xml";
    public entry_generator(String id, String title, String value, String text) throws IOException {
//      <d:entry id="distal" d:title="Distal">
//		<d:index d:value="Distal"/>
//		<h1>Distal</h1>
//                situated away from the centre of the body or from the point of attachment
//      </d:entry>

        deleteLastLine();
        String xml_entry = "<d:entry id=\"" + id + "\" d:title=\"" + title + "\">\n<d:index d:value=\"" + value + "\"/>\n"
                + text +  "\n</d:entry>\n</d:dictionary>";

        File file = new File(fileName);

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }


        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(xml_entry);
        bw.close();

    }
    public entry_generator(){};

    public void deleteLastLine(){
        RandomAccessFile f = null;
        try {
            f = new RandomAccessFile(fileName, "rw");
            long length = f.length() - 1;
            byte b;
            do {
                length -= 1;
                f.seek(length);
                b = f.readByte();
            } while(b != 10 && length>0);
            f.setLength(length+1);
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addToList(List<String[]> list, String id, String title, String value, String text){
            list.add(new String[]{id, title,value,text});
    }

    public String listToXMLString(List<String[]> list){

        String out = "";

        for(int i = 0; i < list.size(); i++){
            out = out + "<d:entry id=\"" + list.get(i)[0] + "\" d:title=\"" +list.get(i)[1] + "\">";
            out = out + "\n<d:index d:value=\"" + list.get(i)[2] + "\" />";
            out = out + list.get(i)[3] + "\n</d:entry>\n";
        }
        return out;
    }

    public String addDocTags(String in){
        String out = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<d:dictionary xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:d=\"http://www.apple.com/DTDs/DictionaryService-1.0.rng\">\n";
        out = out + in;
        out = out + "\n</d:dictionary>";

        return out;
    }

    public void writeXMLFile(String xmlString){
        try {
            File file = new File(fileName + "test");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(xmlString);
            bw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public List<String[]> toList(NodeList eList, NodeList iList){
        List<String[]> outList = new LinkedList<>();
        for (int i = 0; i < eList.getLength(); i++){
            Element entry = (Element) eList.item(i);
            Element value = (Element) iList.item(i);
            outList.add(new String[]{entry.getAttribute("id"), entry.getAttribute("d:title"), value.getAttribute("d:value"), entry_reader.innerXml(entry)});
        }
        return outList;
    }


}
