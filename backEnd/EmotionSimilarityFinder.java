import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EmotionSimilarityFinder {

    public static void main(String args[])
    {
        htmlToString("output.json");
    }

    private static void htmlToString(String fileName){
        StringBuilder contentBuilder = new StringBuilder();
        ArrayList<Double> indicators = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        HashMap<String,Emotion> allLaureats = new HashMap<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = in.readLine()) != null) {
//                System.out.println(str);

                if(str.contains("anger") || str.contains("disgust") ||
                        str.contains("fear") || str.contains("joy") || str.contains("sadness")){
                    String[] strs = str.split(":");
                    str = strs[1].substring(2);
                    if(str.charAt(str.length()-1)==','){
                        str = str.substring(0,str.length()-1);
                    }
                    str = "0." + str;
                    indicators.add(Double.parseDouble(str));

                }
                else if(str.contains("text")){
                    String[] strs = str.split(":");
                    str = strs[1].substring(1,strs[1].length()-2);
                    names.add(str);
                }

            }
            in.close();
        } catch (IOException e) {
        }

        int j = 0;

        for (int i = 4; i <= indicators.size()+5; i = i + 5){
            Emotion e = new Emotion(indicators.get(i-4),indicators.get(i-3),indicators.get(i-2),
                        indicators.get(i-1),indicators.get(i));
                allLaureats.put(names.get(j),e);
                j++;
                if(j==names.size()){
                    break;
                }
        }
        Iterator it = allLaureats.entrySet().iterator();
        
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Emotion v = (Emotion)((Map.Entry) it.next()).getValue();
            System.out.print(v.anger + " " + v.disgust + " " + v.fear + " " + v.joy + " " + v.sadness + " ");
            System.out.println(pair.getKey() );

        }
        return;
    }
}