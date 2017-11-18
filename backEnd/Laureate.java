import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Laureate {

    private String name;
    private Emotion emotion;
    private Map<String, Double> connections = new HashMap<>();

    public Laureate(String name, Emotion emotion, Map<String, Double> connections) {
        this.name = name;
        this.emotion = emotion;
        this.connections = connections;
    }

    public String getName() {
        return name;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public Map<String, Double> getConnections() {
        return connections;
    }

    public List<String> getBestConnections(int amount) {
        List<String> bestConnections = new ArrayList<>(amount);
        Map<String, Double> sorted = sortByValue(connections);
        int i = 0;
        for (Map.Entry<String, Double> entry : sorted.entrySet()) {
            if (i == amount) {
                break;
            }
            if (!entry.getKey().equals(getName())) {
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
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
