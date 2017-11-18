import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;

public class NobelScraper {

    public static void main (String[] args) throws Exception {
        NobelScraper scraper = new NobelScraper();

        JsonElement jsonElement = new JsonParser().parse(new InputStreamReader(new URL("http://api.nobelprize.org/v1/laureate.json").openStream()));
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray laureates = jsonObject.get("laureates").getAsJsonArray();
        Iterator laureatesIterator = laureates.iterator();
        while (laureatesIterator.hasNext()) {
            JsonElement laureate = (JsonElement) laureatesIterator.next();
            JsonObject laureateObject = laureate.getAsJsonObject();
            JsonObject prize = laureateObject.get("prizes").getAsJsonArray().get(0).getAsJsonObject();
            String[] splitName = org.apache.commons.lang3.StringUtils.stripAccents(laureateObject.get("surname").getAsString().toLowerCase()).split(" ");
            String name = splitName[splitName.length - 1].replaceAll("[^A-Za-z]+", "");
            try {
                scraper.scrape(prize.get("category").getAsString(), prize.get("year").getAsInt(), name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private void scrape(String subject, int year, String lastName) throws Exception {
        final String[] sections = {
                "bio",
                "lecture"
        };
        URL url;

        for (String section : sections) {
            url = new URL("https://www.nobelprize.org/nobel_prizes/" + subject + "/laureates/" + year + "/" + lastName + "-" + section + ".html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("<!-- Start Main Content -->")) {
                    break;
                }
            }
            PrintWriter writer = new PrintWriter("Scraper/out/" + lastName + "-" + section + ".html");
            while ((line = reader.readLine()) != null) {
                if (section.equals("lecture") && line.startsWith("  <h2>")) {
                    writer.println(line);
                    break;
                }
                if (section.equals("bio") && line.contains("Biography")) {
                    writer.println(line);
                    break;
                }
                if (section.equals("facts") && line.contains("Facts")) {
                    writer.println(line);
                    break;
                }
            }
            while ((line = reader.readLine()) != null) {
                if (line.equals("    <!-- Blue band START -->")) {
                    while (!(reader.readLine()).equals("<!-- Blue band END -->"));
                    line = reader.readLine();
                }
                if (!line.equals("  <!--eri-no-index-->")) {
                    writer.println(line);
                } else {
                    break;
                }
            }
            writer.close();
        }
    }

    private void scrape(URL url) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.equals("<!-- Start Main Content -->")) {
                break;
            }
        }
        PrintWriter writer = new PrintWriter("testScrape.html");
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("  <h2>")) {
                writer.println(line);
                break;
            }
        }
        while ((line = reader.readLine()) != null) {
            if (!line.equals("  <!--eri-no-index-->")) {
                writer.println(line);
            } else {
                break;
            }
        }
        writer.close();
    }
}
