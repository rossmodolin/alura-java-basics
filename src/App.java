import java.net.URL;
import java.util.List;
import java.io.InputStream;

public class App {
    public static void main(String[] args) throws Exception {

        // Get Top 10 Movies from IMDB API (the API was down, so I had to use the same data stored elsewhere)
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        
        var http = new HttpClientCustom();
        String json = http.dataSearch(url);

        // Extract and manipulate data
        ContentExtractor extractor = new ImdbContentExtractor();
        List<Content> contents = extractor.contentExtractor(json);

        var generator = new StickerGeneratorImdb();

        for (int i = 0; i < contents.size(); i++) {

            Content content = contents.get(i);
            // The following line needs to be commented for StickerGeneratorNasa
            int rating = content.getRating();
            
            InputStream inputStream = new URL(content.getImgUrl()).openStream();
            String filename = "stickers/" + content.getTitle().replace(":", "-") + ".png";

            generator.create(inputStream, filename, rating);

            System.out.println("Saving: " + "\u001b[1m" + content.getTitle() + "\u001b[m");
            System.out.println();
        }

    }

}
