import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.List;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class App {
    public static void main(String[] args) throws Exception {

        // Get Top 10 Movies from IMDB API (the API was down, so I had to use the same data stored elsewhere)
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        URI address = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extract relevant data
        JsonParser parser = new JsonParser();
        List<Map<String, String>> movieList = parser.parse(body);

        // Display and manipulate data
        var generator = new StickerGenerator();
        for (Map<String,String> movie : movieList) {
            
            String urlImage = movie.get("image");
            String title = movie.get("title");
            String rating = movie.get("imDbRating");
            int intRating = (int) Math.round(Double.parseDouble(rating));

            InputStream inputStream = new URL(urlImage).openStream();
            String filename = title.replace(":", "-") + ".png";

            generator.create(inputStream, filename, intRating);

            System.out.println("Saving: " + "\u001b[1m" + title + "\u001b[m");
            System.out.println();
        }

    }

    
}
