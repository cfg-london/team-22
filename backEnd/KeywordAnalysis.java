import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;

public class KeywordAnalysis {

    public static void main(String[] args){
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
                "f7341f49-e333-48ba-ad4d-6765440a8562",
                "6kdbQDL4cmAg"
        );

//        String url = "Scraper/out/abrikosov-bio.html";
        String url = "www.cnn.com";
        KeywordsOptions keywords= new KeywordsOptions.Builder()
                .sentiment(true)
                .emotion(true)
                .limit(3)
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
        System.out.println(response);
    }

}
