import java.io.*;

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
}
