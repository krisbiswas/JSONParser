package Java_Projects.Project.JSON_Parser;

public class JsonValue <T> extends JsonData {
    T value;
    JsonValue(T v){
        value = v;
    }
    @Override
    public JsonData getValue(){
        return this;
    }
    @Override
    public String toString() {
        return value.toString();
    }
}
