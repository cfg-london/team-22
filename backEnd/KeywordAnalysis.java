import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KeywordAnalysis {

    public static void main(String[] args){
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
                "f7341f49-e333-48ba-ad4d-6765440a8562",
                "6kdbQDL4cmAg"
        );


        try {
            FileWriter writer = new FileWriter("output-keyword-analysis.json");
            File dir = new File("Scraper/out/");
            File[] directoryListing = dir.listFiles();
            int i = 0;
            if (directoryListing != null) {
                for (File child : directoryListing) {

                    try {
                        String filePath = child.toString();
                        String url = "gw66.host.cs.st-andrews.ac.uk/" + filePath;
                        System.out.println(i++ + ": " + url);
                        KeywordsOptions keywords= new KeywordsOptions.Builder()
                                .sentiment(true)
                                .emotion(true)
                                .limit(10)
                                .build();

                        Features features = new Features.Builder()
                                .keywords(keywords)
                                .build();

                        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                                .url(url)
                                .features(features)
                                .build();

                        AnalysisResults response = service
                                .analyze(parameters)
                                .execute();
                        writer.write(String.valueOf(response) + ",\n");
                        writer.flush();

                    }catch (Exception e){}
                }
            } else {
                return;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
