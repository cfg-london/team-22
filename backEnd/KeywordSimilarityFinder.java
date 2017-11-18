import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class KeywordSimilarityFinder {

    public static void main(String[] args){

        HashMap<String,ArrayList<String>> allLaureats = new HashMap<>();
        String key ="";
        try {
            BufferedReader in = new BufferedReader(new FileReader("output-keyword-analysis.json"));
            String str;
            //int i = 0;
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

            }
            in.close();
        } catch (IOException e) {
        }


        ArrayList<KeywordLaureate> laureates = new ArrayList<>();
        for (Map.Entry<String, ArrayList<String>> laureateEntry : allLaureats.entrySet()) {
            HashMap<String, Integer> connections = new HashMap<>();
            for (Map.Entry<String, ArrayList<String>> other : allLaureats.entrySet()) {
                int strength = 0;
                for (String otherKeyWord : other.getValue()) {
                    for (String keyword : laureateEntry.getValue()) {
                        if (keyword.contains(otherKeyWord) || otherKeyWord.contains(keyword)) {
                            strength++;
                        }
                    }
                }
                connections.put(other.getKey(), strength);
            }
            laureates.add(new KeywordLaureate(laureateEntry.getKey(), laureateEntry.getValue(), connections));
        }

        System.out.println("[");
        Iterator<KeywordLaureate> iterator = laureates.iterator();
        while (iterator.hasNext()) {
            KeywordLaureate laureate = iterator.next();
            System.out.println("{");
            System.out.println("\"surname\": \"" + laureate.name + "\",");
            System.out.print("\"connections\": ");
            System.out.print("[");
            Iterator<String> it = laureate.getBestConnections(5).iterator();
            while (it.hasNext()) {
                System.out.print("\"" + it.next() + "\"");
                if (it.hasNext()) System.out.print(", ");
            }
            System.out.println("]");
            System.out.print("}");
            if (iterator.hasNext())
                System.out.println(",");
            else
                System.out.println();
        }
        System.out.println("]");
    }


    public static class KeywordLaureate {
        private String name;
        private ArrayList<String> keywords;
        private HashMap<String, Integer> connections;

        public KeywordLaureate(String name, ArrayList<String> keywords, HashMap<String, Integer> connections) {
            this.name = name;
            this.keywords = keywords;
            this.connections = connections;
        }

        public List<String> getBestConnections(int amount) {
            List<String> bestConnections = new ArrayList<>(amount);
            Map<String, Integer> sorted = sortByValue(connections);
            int i = 0;
            for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
                if (i == amount) {
                    break;
                }
                if (!entry.getKey().equals(name)) {
                    bestConnections.add(entry.getKey());
                    i++;
                }
            }
            return bestConnections;
        }

        private <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
            List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
            Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
                public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                    return (o2.getValue()).compareTo( o1.getValue() );
                }
            });

            Map<K, V> result = new LinkedHashMap<K, V>();
            for (Map.Entry<K, V> entry : list) {
                result.put(entry.getKey(), entry.getValue());
            }
            return result;
        }
    }



}
