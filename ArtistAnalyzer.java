import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class ArtistAnalyzer {

  public static void main(String[] args) throws Exception {
    Map<String, Artist> artists = CSVParser.parse();
    Artist a1 = null;
    Artist a2 = null;

    // to compute similarity between artists, we use number of words present in both artist token collections
    if (args.length == 0) {
      // find pair of artists that are most similar

      int bestSimilarityScore = 0;
      Set<String> processedArtists = new HashSet<>();
      int count = 0;
      for (Artist t1 : artists.values()) {
        System.out.println("Processing " + ++count + " of " + artists.size());
        processedArtists.add(t1.getName());
        for (Artist t2 : artists.values()) {
          if (processedArtists.contains(t2.getName()) || t1.getName().equals(t2.getName())) {
            // either already processed or same artist, skip
            continue;
          }
          int score = computeSimilarity(t1, t2);
          if (score > bestSimilarityScore) {
            bestSimilarityScore = score;
            a1 = t1;
            a2 = t2;
          }
        }
      }

      if (a1 != null && a2 != null) {
        System.out.println("The two artists most similar to one another are " + a1.getName() + " and " + a2.getName());
      } else {
        System.out.println("Cannot find pair of artists most similar to one another.");
      }
    } else if (args.length == 1) {
      // for the given artist, find most similar artist
      if (!artists.containsKey(args[0])) {
        throw new IllegalArgumentException("Artist not found.");
      }

      a1 = artists.get(args[0]);
      int bestSimilarityScore = 0;
      for (Artist artist : artists.values()) {
        if (artist.getName().equals(args[0])) {
          // same artist, skip
          continue;
        }

        int score = computeSimilarity(a1, artist);
        if (score > bestSimilarityScore) {
          bestSimilarityScore = score;
          a2 = artist;
        }
      }

      if (a2 != null) {
        System.out.println("The artist that is most similar to " + args[0] + " is " + a2.getName());
      } else {
        System.out.println("No artist found similar to " + args[0]);
      }
    } else if (args.length == 2) {
      // return numerical representation of similarity of words the two artists use in their artwork titles
      if (!artists.containsKey(args[0]) || !artists.containsKey(args[1])) {
        throw new IllegalArgumentException("Artist not found.");
      }

      a1 = artists.get(args[0]);
      a2 = artists.get(args[1]);
      System.out.println("Similarity score: " + computeSimilarity(a1, a2));
    } else {
      throw new IllegalArgumentException("Invalid arguments.");
    }

    if (a1 != null && a2 != null) {
      printResults(a1, a2);
    }
  }

  private static int computeSimilarity(Artist a1, Artist a2) {
    int similarity = 0;
    for (String a1Token : a1.getTokenCounts().keySet()) {
      if (a2.getTokenCounts().keySet().contains(a1Token)) {
        similarity++;
      }
    }
    return similarity;
  }

  /**
   * Print out results indicating why two artists are similar.
   * @param a1 the first artist.
   * @param a2 the second artist.
   */
  private static void printResults(Artist a1, Artist a2) {
    Set<String> wordsInBoth = new HashSet<>();
    Set<String> wordsInA1 = new HashSet<>();
    Set<String> wordsInA2 = new HashSet<>();

    for (String a1Token : a1.getTokenCounts().keySet()) {
      if (a2.getTokenCounts().containsKey(a1Token)) {
        // a1Token exist in both a1 and a2
        wordsInBoth.add(a1Token);
      } else {
        // a1Token only exist in a1
        wordsInA1.add(a1Token);
      }
    }

    for (String a2Token : a2.getTokenCounts().keySet()) {
      if (a1.getTokenCounts().containsKey(a2Token)) {
        // a2Token exist in both a1 and a2
        wordsInBoth.add(a2Token);
      } else {
        // a2Token only exist in a2
        wordsInA2.add(a2Token);
      }
    }

    System.out.println(
        "There are " + wordsInBoth.size() + " tokens that are shared between " + a1.getName() + " and " + a2.getName()
            + ".");
    for (String word : wordsInBoth) {
      System.out.println(word);
    }

    System.out.println();

    System.out.println("There are " + wordsInA1.size() + " tokens that " + a1.getName() + " used that " + a2.getName()
        + " did not use.");
    for (String word : wordsInA1) {
      System.out.println(word);
    }

    System.out.println();

    System.out.println("There are " + wordsInA2.size() + " tokens that " + a2.getName() + " used that " + a1.getName()
        + " did not use.");
    for (String word : wordsInA2) {
      System.out.println(word);
    }
  }
}