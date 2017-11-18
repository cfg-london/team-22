import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.FileWriter;

public class EmotionAnalysis {

    public void doEmotionalAnalysis() throws IOException {

        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
                "f7341f49-e333-48ba-ad4d-6765440a8562",
                "6kdbQDL4cmAg"
        );

        ArrayList<AnalysisResults> resul = new ArrayList<>();

        File dir = new File("Scraper/outLiterature/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {

                try {
                    String filePath = child.toString();
                    String htmlText = htmlToString(filePath);

                    if (htmlText.length() < 100) {
                        continue;
                    }

                    List<String> targets = new ArrayList<>();
                    String[] split_target = child.toString().split("-");
                    String target = split_target[0].substring(22, 23).toUpperCase() + split_target[0].substring(23);
                    System.out.println(target);

                    targets.add(target);

                    EmotionOptions emotion = new EmotionOptions.Builder()
                            .targets(targets)
                            .build();

                    Features features = new Features.Builder()
                            .emotion(emotion)
                            .build();

                    AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                            .html(htmlText)
                            .features(features)
                            .build();

                    AnalysisResults response = service
                            .analyze(parameters)
                            .execute();

                    resul.add(response);

                }catch (Exception e){}
            }
        } else {
            return;
        }
        FileWriter writer = new FileWriter("output.json");
        for(AnalysisResults str: resul) {
            writer.write(String.valueOf(str));
        }
        writer.close();

    }

    public static void main(String[] args) throws IOException {

        EmotionAnalysis e = new EmotionAnalysis();
        e.doEmotionalAnalysis();
    }

    private String htmlToString(String fileName){
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException e) {
        }
        String content = contentBuilder.toString();

        return content;
    }
}
