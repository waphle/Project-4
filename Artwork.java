import java.util.Map;


public class Artwork {

  private String title;
  private String artistName;

  // token -> frequency mapping in the title of this artwork
  private final Map<String, Integer> tokenCounts;

  public Artwork(String title, String artistName) {
    this.title = title;
    this.artistName = artistName;
    tokenCounts = StringParser.parse(title);
  }

  public Map<String, Integer> getTokenCounts() {
    return tokenCounts;
  }

  public String getTitle() {
    return title;
  }

  public String getArtistName() {
    return artistName;
  }
}
