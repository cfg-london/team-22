import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

public class NobelScraper {

    public static void main (String[] args) throws Exception {
        NobelScraper scraper = new NobelScraper();
        scraper.scrape("peace", 1964, "king");
    }

    private void scrape(String subject, int year, String lastName) throws Exception {
        final String[] sections = {
                "bio",
                "lecture",
                "facts"
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
            PrintWriter writer = new PrintWriter(lastName + "-" + section + ".html");
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
