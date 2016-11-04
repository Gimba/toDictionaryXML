import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by martinrosellen on 04/11/2016.
 */
public class entry_reader  {

    public entry_reader() throws FileNotFoundException {
        FileReader fr = new FileReader("output/entries.xml");

        BufferedReader bf = new BufferedReader(fr);
        List<String> line_list = new LinkedList<String>();

        try {
            String line;
            while ((line = bf.readLine()) != null){
                line_list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(line_list);
    }

}
