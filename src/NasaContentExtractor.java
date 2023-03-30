import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {
  public List<Content> contentExtractor(String json) {
    JsonParser parser = new JsonParser();
    List<Map<String, String>> attrsList = parser.parse(json);

    List<Content> contentList = new ArrayList<>();

    for (Map<String, String> attr : attrsList) {
      String title = attr.get("title");
      String imageUrl = attr.get("url");
      Content content = new Content(title, imageUrl);

      contentList.add(content);
    }

    return contentList;
  }
}
