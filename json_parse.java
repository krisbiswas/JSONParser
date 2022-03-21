package Java_Projects.Project.JSON_Parser;

public class json_parse {
    public static void main(String[] args) throws Exception 
    {
        /* 
        String json = "{"+
            "\"example\": ["+
                "{"+
                    "\"firstName\": \"John\","+
                    "\"lastName\": \"Doe\""+
                "},"+
                "{"+
                    "\"firstName\": \"Anna\","+
                    "\"lastName\": \"Smith\""+
                "},"+
                "{"+
                    "\"firstName\": \"Peter\","+
                    "\"lastName\": \"Jones\""+
                "}"+
            "]"+
        "}";
 */
        JsonParser jp = new JsonParser();
        JsonObject obj = jp.parseFile("C:/Users/krisb/VS_Workspace/Java_Projects/Project/sample2.json");
        // JsonObject obj = jp.parse(json);
        // System.out.println("Quiz\n"+obj+"\n");

        JsonValue fName = (JsonValue)obj.getValue("firstName");
        JsonValue lName = (JsonValue)obj.getValue("lastName");
        JsonObject address = (JsonObject) obj.getJsonObject("address");

        System.out.println(fName.toString()+" "+lName);
        System.out.println("Address: "+address);
        
/* 
        JsonObject obj = jp.parse(json);
        JsonArray example = obj.getJsonArray("example");
        // System.out.println(example);
        obj = jp.parse(example.getValue(2).toString());
        // System.out.println(obj);
        System.out.println(obj.getValue("firstName")); */
    }
}
