import java.util.Map;


public class ArtistAnalyzer {

  

  public static void main(String[] args) throws Exception {
    Map<String, Artist> artists = CSVParser.parse();
    if (args.length == 0) {
      // find pair of artists that are most similar

    } else if (args.length == 1) {
      // for the given artist, find most similar artist
    } else if (args.length == 2) {
      // return numerical representation of similarity of words the two artists use in their artwork titles
    } else {
      throw new IllegalArgumentException("Invalid arguments.");
    }
  }
}