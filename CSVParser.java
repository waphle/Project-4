import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CSVParser {

  private static final String FILE_PATH = "/Users/thzhang/Workspace/Project-4/Artworks_new.csv";
  private static final char DEFAULT_SEPARATOR = ',';
  private static final char DEFAULT_QUOTE = '"';

  public static void parse() throws Exception {
    Scanner scanner = new Scanner(new File(FILE_PATH));
    while (scanner.hasNext()) {
      List<String> line = parseLine(scanner.nextLine());
      
    }
    scanner.close();
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
    parse();
  }
}
