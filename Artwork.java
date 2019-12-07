import java.util.HashMap;
import java.util.Map;


public class Artwork {

  private String title;
  private String artist;

  // token -> frequency mapping in the title of this artwork
  private final Map<String, Integer> titleTokenCounts = new HashMap<>();

  public static void count() {

  }
}
