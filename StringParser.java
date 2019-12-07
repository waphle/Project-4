import java.util.HashMap;
import java.util.Map;


public class StringParser {

  /**
   * Parses string into a map of word to counts. Each word only consists of alphanumeric characters (a-zA-Z0-9).
   * @param str string to parse.
   * @return map of word to counts.
   */
  public static Map<String, Integer> parse(String str) {
    Map<String, Integer> tokenCounts = new HashMap<>();
    // split str by one or more spaces
    String[] words = str.split("\\s+");
    for (int i = 0; i < words.length; i++) {
      words[i] = words[i].replaceAll("[^\\w]", "");
      if (!words[i].isEmpty()) {
        tokenCounts.put(words[i], tokenCounts.getOrDefault(words[i], 0) + 1);
      }
    }
    return tokenCounts;
  }

  public static void main(String[] args) {
    Map<String, Integer> result = StringParser.parse("Catalytic text: 20 January 2017. First line: “The problem is ours.” An ad in The New York Times, 7 September 2018, on page A5.");
    System.out.println(result);
  }
}
