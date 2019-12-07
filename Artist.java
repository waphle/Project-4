import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Artist {

  private final String name;
  private final List<Artwork> artworks;

  public Artist(String name, String artworkTitle) {
    this.name = name;
    artworks = new ArrayList<>();
    artworks.add(new Artwork(artworkTitle, name));
  }

  public void addArtwork(String artworkTitle) {
    artworks.add(new Artwork(artworkTitle, name));
  }

  public Map<String, Integer> getTokenCounts() {
    Map<String, Integer> tokenCounts = new HashMap<>();
    for (Artwork artwork : artworks) {
      Map<String, Integer> counts = artwork.getTokenCounts();
      for (String key : counts.keySet()) {
        tokenCounts.put(key, tokenCounts.getOrDefault(key, 0) + 1);
      }
    }
    return tokenCounts;
  }

  public String getName() {
    return name;
  }

  public List<Artwork> getArtworks() {
    return artworks;
  }

  public static void main(String[] args) {
    Artist artist = new Artist("Jules Janssen", "Observatoire d'Astronomie Physique de Paris sis à Meudon, Seine-et-Oise: Atlas de Photographies Solaires, 1er Fascicule, 1903");
    artist.addArtwork("Région Central (Granulations), June 30, 1893, 7h 18m 54s");
    System.out.println(artist.getTokenCounts());
  }
}
