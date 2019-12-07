import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Artist {

  private final String name;
  private final List<Artwork> artworks;

  // token -> frequency mapping for all the titles of all the artworks by this artist
  private final Map<String, Integer> titleTokenCounts;

  public Artist(String name, String artworkTitle) {
    this.name = name;
    artworks = new ArrayList<>();
    addArtwork(artworkTitle);
    titleTokenCounts = new HashMap<>();
  }

  public void addArtwork(String artworkTitle) {
    artworks.add(new Artwork(artworkTitle, name));
  }

  public void summarize() {
    titleTokenCounts.clear();
    // TODO: summarize counts for each token so far
  }
}
