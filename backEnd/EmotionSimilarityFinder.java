import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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

        System.out.println(Arrays.toString(names.toArray()));

        int j = 0;

        for (int i = 4; i <= indicators.size()+5; i = i + 10){
            Emotion e = new Emotion(indicators.get(i-4),indicators.get(i-3),indicators.get(i-2),
                        indicators.get(i-1),indicators.get(i));
                allLaureats.put(names.get(j),e);
                j++;
                if(j==names.size()){
                    break;
                }
        }


        List<Laureate> laureates = new ArrayList<>(allLaureats.size());
        for (Map.Entry<String, Emotion> entry : allLaureats.entrySet()) {
            HashMap<String, Double> connections = new HashMap<>();
            for (Map.Entry<String, Emotion> other : allLaureats.entrySet()) {
                double connection = 0;
                connection += Math.abs(entry.getValue().anger - other.getValue().anger);
                connection += Math.abs(entry.getValue().disgust - other.getValue().disgust);
                connection += Math.abs(entry.getValue().fear - other.getValue().fear);
                connection += Math.abs(entry.getValue().joy - other.getValue().joy);
                connection += Math.abs(entry.getValue().sadness - other.getValue().sadness);
                connections.put(other.getKey(), connection);
            }
            laureates.add(new Laureate(entry.getKey(), entry.getValue(), connections));
        }

        for (Laureate laureate : laureates) {
            System.out.println(laureate.getName());
            System.out.println(Arrays.toString(laureate.getBestConnections(10).toArray()));
        }
    }
}