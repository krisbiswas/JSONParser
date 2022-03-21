package Java_Projects.Project.JSON_Parser;

import java.util.List;

public class JsonArray <T> extends JsonData {
    List<T> values;
    @Override
    public JsonData getValue(int index){
        return new JsonValue<T>(values.get(index));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(T v : values){
            sb.append(v).append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
