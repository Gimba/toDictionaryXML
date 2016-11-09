import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Map;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by martinrosellen on 08/11/2016.
 */
public class DictBuilder {
    public DictBuilder(){
        copyXMLtoDict();
        try {
            executeCommand("make");
            executeCommand("make install");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void copyXMLtoDict(){
        try{
            Files.copy(new File("output/entries.xml").toPath(), new File("myDictionary/MyDictionary.xml").toPath(), REPLACE_EXISTING);
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }
}
