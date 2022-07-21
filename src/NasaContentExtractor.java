import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class NasaContentExtractor implements ContentExtractor {

    public List<Content> contentExtractor(String json) {

        // Extract relevant data
        JsonParser parser = new JsonParser();
        List<Map<String, String>> attributesList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        // Populate content list
        for (Map<String, String> attributes : attributesList) {
            String title = attributes.get("title");
            String imgUrl = attributes.get("url");
            var content = new Content(title, imgUrl);

            contents.add(content);

        }

        return contents;
    }
    
}
