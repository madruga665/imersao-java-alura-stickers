import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
  public static void main(String[] args) throws Exception {
    String topMoviesUrl = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json";
    ClientHttp client = new ClientHttp();
    ContentExtractor contentExtractor = new ImdbContentExtractor();
    String json = client.getData(topMoviesUrl);

    List<Content> imdbContent = contentExtractor.contentExtractor(json);
    StickerGenerator stickerGenerator = new StickerGenerator();

    for (int index = 0; index < 3; index += 1) {
      Content content = imdbContent.get(index);
      String image = content.getImageUrl();
      String title = content.getTitle();
      InputStream imageUrl = new URL(image).openStream();
      stickerGenerator.create(imageUrl, "Hello World", title + ".png");

      System.out.println(title);
      System.out.println();
    }
  }
}
