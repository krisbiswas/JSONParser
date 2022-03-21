package Java_Projects.Project.JSON_Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.StringBuilder;

/* enum SPECIALCHAR{
    C_OPEN_BRACKET("{"),
    C_CLOSE_BRACKET("}"),
    REC_OPEN_BRACKET("["),
    REC_CLOSE_BRACKET("]"),
    COMMA(","),
    COLON(":"),
    BAR("|")
} */

public class JsonParser {
    private JsonObject topJsonObject;
    static JsonHandler handler = new JsonHandler();

    public JsonObject parseFile(String filePath) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        parse(stringBuilder.toString());
        return topJsonObject;
    }

    public JsonObject parse(String json){
        topJsonObject = handler.handle(json);
        return topJsonObject;
    }

}