import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 * parseLine() taken from https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 */
public class CSVParser {

  private static final String FILE_PATH = "Artworks_new.csv";
  private static final char DEFAULT_SEPARATOR = ',';
  private static final char DEFAULT_QUOTE = '"';

  /**
   * Parses Artworks_new.csv into a map of artist name -> artist.
   *
   * @return a map of artist name -> artist
   * @throws Exception
   */
  public static Map<String, Artist> parse() throws Exception {
    // artist name -> artist mapping which contains the parse output
    Map<String, Artist> output = new HashMap<>();

    // parse the file
    Scanner scanner = new Scanner(new File(FILE_PATH));
    while (scanner.hasNext()) {
      List<String> line = parseLine(scanner.nextLine());
      String artworkTitle = line.get(0);
      String artistName = line.get(1);
      if (artistName.isEmpty()) {
        // skip artworks with no artist
        continue;
      }
      if (output.containsKey(artistName)) {
        // artist already exists, add this artwork to this artist
        output.get(artistName).addArtwork(artworkTitle);
      } else {
        // artist does not exist, create new artist with this artwork
        output.put(artistName, new Artist(artistName, artworkTitle));
      }
    }

    // summarize token counts for each artist
    for (Artist artist : output.values()) {
      artist.summarize();
    }

    scanner.close();
    return output;
  }

  private static List<String> parseLine(String cvsLine) {
    List<String> result = new ArrayList<>();

    //if empty, return!
    if (cvsLine == null && cvsLine.isEmpty()) {
      return result;
    }

    StringBuffer curVal = new StringBuffer();
    boolean inQuotes = false;
    boolean startCollectChar = false;
    boolean doubleQuotesInColumn = false;

    char[] chars = cvsLine.toCharArray();

    for (char ch : chars) {

      if (inQuotes) {
        startCollectChar = true;
        if (ch == DEFAULT_QUOTE) {
          inQuotes = false;
          doubleQuotesInColumn = false;
        } else {

          //Fixed : allow "" in custom quote enclosed
          if (ch == '\"') {
            if (!doubleQuotesInColumn) {
              curVal.append(ch);
              doubleQuotesInColumn = true;
            }
          } else {
            curVal.append(ch);
          }
        }
      } else {
        if (ch == DEFAULT_QUOTE) {

          inQuotes = true;

          //Fixed : allow "" in empty quote enclosed
          if (chars[0] != '"' && DEFAULT_QUOTE == '\"') {
            curVal.append('"');
          }

          //double quotes in column will hit this!
          if (startCollectChar) {
            curVal.append('"');
          }
        } else if (ch == DEFAULT_SEPARATOR) {

          result.add(curVal.toString());

          curVal = new StringBuffer();
          startCollectChar = false;
        } else if (ch == '\r') {
          //ignore LF characters
          continue;
        } else if (ch == '\n') {
          //the end, break!
          break;
        } else {
          curVal.append(ch);
        }
      }
    }

    result.add(curVal.toString());

    return result;
  }

  public static void main(String[] args) throws Exception {
    Map<String, Artist> artists = parse();
    for (Artist artist : artists.values()) {
      System.out.println(artist.getName() + ":");
      for (Artwork artwork : artist.getArtworks()) {
        System.out.println(artwork.getTitle());
      }
      System.out.println();
    }
  }
}
