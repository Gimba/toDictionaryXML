import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class XmlWriter {
    String fileName = "output/entries.xml";
    public XmlWriter(String id, String title, String value, String text) throws IOException {
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
    public XmlWriter(){};

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

    public static String listToXMLString(List<String[]> list){

        String out = "";

        for(int i = 0; i < list.size(); i++){
            out = out + "<d:entry id=\"" + list.get(i)[0] + "\" d:title=\"" +list.get(i)[1] + "\">";
            out = out + "\n<d:index d:value=\"" + list.get(i)[2] + "\" />";
            out = out + list.get(i)[3] + "\n</d:entry>\n";
        }
        return out;
    }

    public static String addDocTags(String in){
        String out = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<d:dictionary xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:d=\"http://www.apple.com/DTDs/DictionaryService-1.0.rng\">\n";
        out = out + in;
        out = out + "\n</d:dictionary>";

        return out;
    }


    public static void writeXMLFile(String xmlString){
        try {
            String fileName = "output/entries.xml";
            File file = new File(fileName);

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

    public static void writeXML(List<String[]> list){
        String str = listToXMLString(list);
        str = addDocTags(str);
        writeXMLFile(str);
        System.out.println("write");
    }

}
