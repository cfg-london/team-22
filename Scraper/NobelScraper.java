import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

public class NobelScraper {

    public static void main (String[] args) throws Exception {
        URL url;
        url = new URL("https://www.nobelprize.org/nobel_prizes/peace/laureates/1964/king-lecture.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.equals("<!-- Start Main Content -->")) {
                break;
            }
        }
        PrintWriter writer = new PrintWriter("testScrape.html");
        while ((line = reader.readLine()) != null) {
            if (!line.equals("<!--eri-no-index-->")) {
                writer.write(line);
            } else {
                break;
            }
        }
        writer.close();
    }
}
