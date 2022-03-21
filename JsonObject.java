package Java_Projects.Project.JSON_Parser;

import java.util.HashMap;
import java.util.Map;

public class JsonObject extends JsonData {
    private Map<String, String> pairs;

    JsonObject() {
        pairs = new HashMap<String, String>();
    }

    JsonObject(Map<String, String> pairs) {
        this.pairs = pairs;
    }

    public JsonObject getJsonObject(String json){
        String data = pairs.get(json);
        return JsonParser.handler.handle(data);
    }

    public JsonArray<String> getJsonArray(String json){
        // array might contain objects, so, splitting with "," will also split data objects too
        // try manually
        // String[] arValues = data.substring(1, data.length()-1).split(",");
        String data = pairs.get(json);
        return JsonParser.handler.handleJsonArray(data);
    }

    @Override
    public JsonData getValue(String k){
        String data = pairs.get(k);
        // System.out.println(data);
        if(data.charAt(0) == '[' && isValidArray(data.charAt(0), data.charAt(data.length()-1))){
            // value is array
            return getJsonArray(data);
        }else if(data.charAt(0) == '{' && isValidJson(data.charAt(0), data.charAt(data.length()-1))){
            // value is object
            return getJsonObject(data);
        }else {
            return getPrintableValue(data);
        }
    }

    public JsonValue<String> getPrintableValue(String val){
        return new JsonValue<String>(val.substring(1,val.length()-1));
    }

    public void setValue(String k, String v){
        pairs.put(k, v);
    }

    private boolean isValidArray(char start, char end){
        return start == '[' && end == ']';
    }

    private boolean isValidJson(char start, char end){
        return start == '{' && end == '}';
    }

    @Override
    public String toString() {
        return pairs.toString();
    }
}