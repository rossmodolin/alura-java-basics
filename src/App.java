import java.net.URI;
import java.util.Map;
import java.util.List;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class App {
    public static void main(String[] args) throws Exception {

        // Get Top 250 Movies from IMDB API (the API was down, so I had to use the same data stored elsewhere)
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
        for (Map<String,String> movie : movieList) {
            String title = movie.get("title");
            String image = movie.get("image");
            String rating = movie.get("imDbRating");
            System.out.println("\u001b[1m" + title + "\u001b[m");
            System.out.println("Image URL: " + image);
            System.out.println("Rating: " + rating);
            System.out.println();
        }

    }
}
