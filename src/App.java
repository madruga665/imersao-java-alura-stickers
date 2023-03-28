import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
	public static void main(String[] args) throws Exception {
		String topMoviesUrl = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json";
		URI url = URI.create(topMoviesUrl);
		HttpClient clientHttp = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(url).GET().build();
		HttpResponse<String> response = clientHttp.send(request, BodyHandlers.ofString());
		String body = response.body();
		JsonParser parser = new JsonParser();
		List<Map<String, String>> moviesList = parser.parse(body);

		for (Map<String, String> movie : moviesList) {
			System.out.println(movie.get("title"));
			System.out.println(movie.get("image"));
			System.out.println(movie.get("imDbRating"));
			Double imDbRating = Double.parseDouble(movie.get("imDbRating"));

			for (int i = 0; i < imDbRating; i++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}
}
