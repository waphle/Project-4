import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Artist {

  private final List<Artwork> artworks = new ArrayList<>();

  // token -> frequency mapping for all the titles of all the artworks by this artist
  private final Map<String, Integer> titleTokenCounts = new HashMap<>();

  private static void count() {

  }


}
