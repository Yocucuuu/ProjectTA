package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    public static Object[][] getJSONdata(String JSON_path, String JSON_data, String[] JSON_attributes) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader((JSON_path)));
        JSONObject jo = (JSONObject) obj;
        JSONArray js = (JSONArray) jo.get(JSON_data);

        Object[][] arr = new String[js.size()][JSON_attributes.length];
        for (int i = 0; i < js.size(); i++) {
            JSONObject obj1 = (JSONObject) js.get(i);
            for(int j=0;j< JSON_attributes.length ;j++){
                arr[i][j] = String.valueOf(obj1.get(JSON_attributes[j]));
            }
        }
        return arr;
    }
}
