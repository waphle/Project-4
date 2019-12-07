import java.util.HashMap;
import java.util.Map;


public class Artwork {

  private String title;
  private String artistName;

  // token -> frequency mapping in the title of this artwork
  private final Map<String, Integer> titleTokenCounts;

  public Artwork(String title, String artistName) {
    this.title = title;
    this.artistName = artistName;
    titleTokenCounts = new HashMap<>();
    // TODO: populate counts

  }
}
