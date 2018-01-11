package json;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private ArrayList<JsonPair> jsonObj;

    public JsonObject(JsonPair... jsonPairs) {
        jsonObj = new ArrayList();
        jsonObj.addAll(Arrays.asList(jsonPairs));
    }

    @Override
    public String toJson() {
        StringBuilder objString = new StringBuilder("{");
        int count = 0;
        for (JsonPair pair : jsonObj) {
            String key = pair.key;
            objString.append(String.format("%s: %s", key, pair.value.toJson()));
            count++;
            if (count < jsonObj.size()) {
                objString.append(", ");
            }
        }
        objString.append("}");
        return objString.toString();
    }


    public void add(JsonPair jsonPair) {
        jsonObj.add(jsonPair);
    }

    public Json find(String name) {
        for(JsonPair pair : jsonObj){
            if (name.equals(pair.key)){
                return pair.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {

        JsonObject proj = new JsonObject();
        for (String name : names) {
            if (this.find(name) != null) {
                proj.add(new JsonPair(name, this.find(name)));
            }
        }
        return proj;
    }
}