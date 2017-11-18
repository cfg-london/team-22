import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KeywordSimilarityFinder {

    public static void main(String[] args){

        HashMap<String,ArrayList<String>> allLaureats = new HashMap<>();
        String key ="";
        try {
            BufferedReader in = new BufferedReader(new FileReader("output-keyword-analysis.json"));
            String str;
            int i = 0;
            while ((str = in.readLine()) != null) {
//                System.out.println(str);
                if(str.contains("retrieved_url")){
                    key = str.substring(str.indexOf("out/") + 4, str.lastIndexOf("-bio.html"));
                    allLaureats.put(key,new ArrayList<>());
                }
                if(str.contains("text")){
                    String tag = str.substring(str.indexOf(": \"") + 3, str.lastIndexOf("\""));
                    allLaureats.get(key).add(tag);
                }
                if (i++ % 100 == 0) {
                    System.out.println(i);
                }
            }
            in.close();
        } catch (IOException e) {
        }
        for (Map.Entry<String, ArrayList<String>> entry: allLaureats.entrySet()){
            System.out.println(entry.getKey() + Arrays.toString(entry.getValue().toArray()));
        }
    }



}
