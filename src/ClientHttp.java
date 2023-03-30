import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClientHttp {
  public String getData(String url) {
    URI address = URI.create(url);
    HttpClient clientHttp = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder(address).GET().build();
    HttpResponse<String> response;

    try {
      response = clientHttp.send(request, BodyHandlers.ofString());
      String body = response.body();

      return body;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
