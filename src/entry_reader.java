import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class entry_reader  {
    String [] string_array;
    public entry_reader() throws FileNotFoundException {
        FileReader fr = new FileReader("output/entries.xml");

        BufferedReader bf = new BufferedReader(fr);


        try {
            String line;
            List<String> line_list = new LinkedList<String>();
            while ((line = bf.readLine()) != null){
                line_list.add(line);
            }
            // string_array = new String[line_list.size()];
            string_array = line_list.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
