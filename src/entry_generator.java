/**
 * Created by martinrosellen on 04/11/2016.
 */
public class entry_generator {
    public entry_generator(String id, String title, String value, String text){
//      <d:entry id="distal" d:title="Distal">
//		<d:index d:value="Distal"/>
//		<h1>Distal</h1>
//                situated away from the centre of the body or from the point of attachment
//      </d:entry>
        String xml_entry = "<d:entry id=\"" + id + "\" d:title=\"" + title + "\">\n<d:index d:value=\"" + value + "\"/>\n"
                + text +  "\n</d:entry>\n";
        System.out.print(xml_entry);
    }
}
