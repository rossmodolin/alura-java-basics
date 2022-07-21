import java.net.URL;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ImdbContentExtractor implements ContentExtractor {

    public List<Content> contentExtractor(String json) {

        // Extract relevant data
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        // Populate content list
        for (Map<String, String> attributes : attributesList) {
            String title = attributes.get("title");
            String imgUrl = attributes.get("image")
                    .replaceAll("(@+)(.*).jpg$", "$1.jpg");
            String rating = attributes.get("imDbRating");
            int intRating = (int) Math.round(Double.parseDouble(rating));

            try (InputStream inputStream = new URL(imgUrl).openStream()) {
                Content content = new Content(title, imgUrl, intRating);
                contents.add(content);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return contents;
    }
    
    
}
