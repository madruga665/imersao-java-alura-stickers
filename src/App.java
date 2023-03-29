import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

  static void showRating(Double rating) {
    for (int i = 0; i < rating; i++) {
      System.out.print("* ");
    }
  }

  public static void main(String[] args) throws Exception {
    String topMoviesUrl = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json";
    URI url = URI.create(topMoviesUrl);
    HttpClient clientHttp = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder(url).GET().build();
    HttpResponse<String> response = clientHttp.send(request, BodyHandlers.ofString());
    String body = response.body();
    JsonParser parser = new JsonParser();
    List<Map<String, String>> moviesList = parser.parse(body);
    var generate = new StickerGenerator();

    for (Map<String, String> movie : moviesList) {
      System.out.println(movie.get("title"));
      System.out.println(movie.get("image"));
      System.out.println(movie.get("imDbRating"));
      Double imDbRating = Double.parseDouble(movie.get("imDbRating"));
      showRating(imDbRating);

      String movieImage = movie.get("image");
      InputStream imageUrl = new URL(movieImage).openStream();
      generate.create(imageUrl, "Hello World", movie.get("title") + ".png");

      System.out.println();
    }
  }
}
