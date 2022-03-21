package Java_Projects.Project.JSON_Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class JsonHandler {
    //  Problem with spliting
    //  try travesing and spliting manually
    public JsonObject handle(String json){
        JsonObject obj = null;
        if(isValidJson(json.charAt(0), json.charAt(json.length()-1))){
            json = json.substring(1, json.length()-1).strip();
            // Here i'm in the object
            ArrayList<String> kvs = splitComma(new StringBuilder(json));
            
            Map<String,String> map = new HashMap<>();
            for(String kv : kvs){
                List<String> kvPair = splitColon(kv);
                // System.out.println("Key = "+kvPair.get(0));
                // System.out.println("Value = "+kvPair.get(1));
                // System.out.println("DBG END");
                map.put(kvPair.get(0), kvPair.get(1));
            }
            obj = new JsonObject(map);
        }

        return obj;
    }

    public JsonArray<String> handleJsonArray(String jsonArray){
        JsonArray<String> arr = null;
        if(isValidArray(jsonArray.charAt(0), jsonArray.charAt(jsonArray.length()-1))){
            arr = new JsonArray<String>();
            jsonArray = jsonArray.strip().substring(1, jsonArray.length()-1).strip();
            List<String> arrayValues = splitComma(new StringBuilder(jsonArray));
            for(int i = 0;i<arrayValues.size();i++){
                String temp = arrayValues.get(i).strip();
                if(temp.charAt(0) != '[' && temp.charAt(0) != '{'){
                    temp = temp.substring(1,temp.length()-1);
                }
                arrayValues.set(i, temp);
            }
            arr.values = arrayValues;
        }
        return arr;
    }

    private ArrayList<String> splitComma(StringBuilder json){
        StringBuilder buff = new StringBuilder();
        ArrayList<String> kvs = new ArrayList<>();

        Stack<Character> stk = new Stack<>();
        for(int i=0;i<json.length();i++){
            // Possibilty of comma in string value is not considered
            if (json.charAt(i) == '['){
                stk.push(json.charAt(i));
                buff.append(json.charAt(i));
            }else if(json.charAt(i) == '{'){
                stk.push(json.charAt(i));
                buff.append(json.charAt(i));
            }else if (json.charAt(i) == ']'){
                stk.pop();
                buff.append(json.charAt(i));
            }else if(json.charAt(i) == '}'){
                stk.pop();
                buff.append(json.charAt(i));
            } else if (json.charAt(i) == ','){
                if (stk.isEmpty()){
                    // if it has come out of the object
                    kvs.add(buff.toString().strip());
                    buff.replace(0, buff.length(), "");
                }else{
                    buff.append(json.charAt(i));
                }
            }else{
                buff.append(json.charAt(i));
            }
        }
        kvs.add(buff.toString().strip());
        return kvs;
    }

    private List<String> splitColon(String kv){
        int separatorColonAt = kv.indexOf(':', 0);
        String key = kv.substring(0, separatorColonAt).strip();
        key = key.substring(1,key.length()-1).strip();
        String value = kv.substring(separatorColonAt+1).strip();
        // if(value.charAt(0) != '[' && value.charAt(0) != '{'){
        //     if(value.length()>1){
        //         value = value.substring(1,value.length()-1).strip();
        //     }
        // }
        return Arrays.asList(key, value);
    }

/* 
    private JsonData getValue(String s){
        JsonData v = null;
        switch(getValueDataType(s)){
            0:  v = JsonValue<String>(kvPair[1].substring(1, kvPair[0].length-1).strip());
                break;
            1:  v = JsonValue<Boolean>(new Boolean(kvPair[1]));
                break;
            2:  Integer intVal = Integer.getInteger(kvPair[1]);
                v = JsonValue<Number>(intVal);
                break;
            3:  Double doubleVal = Double.valueOf(kvPair[1]);
                v = JsonValue<Number>(doubleVal);
                break;
            4:  v = JsonValue<NullType>(null);
                break;
        }
        return v;
    }
    
    private int getValueDataType(String s){
        if(s.charAt(0) == '\"'){
            // value is a string
            return 0;
        }else if(s.equals("true") || s.equals("false")){
            // boolean value
            return 1;
        }else if(s.equals("null")){
            // null value
            return 4;
        }else{
            // number type value
            boolean type = true;
            try{
                Integer.getInteger(s);
            }catch(NumberFormatException nfe){
                type = false;
            }
            return (type)? 2 : 3;
        }
    }

    boolean isValidArray(char start, char end){
        return start == '[' && end == ']';
    }
 */
    
    private boolean isValidJson(char start, char end){
        return start == '{' && end == '}';
    }
    private boolean isValidArray(char start, char end){
        return start == '[' && end == ']';
    }
}