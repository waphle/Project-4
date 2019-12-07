import java.util.Scanner;
import java.util.ArrayList;

public class ATRepos { // Artist and Title initials form AT
   // Atrributes
   ArrayList<AT> atRops = new ArrayList<AT>();
   
   // Constructors
   public ATRepos() {};
   
   // Getters and setters
   public ArrayList<AT> getATRepos() { return atRops; }
   
   // Methods
   public boolean loadATs(String fileName) throws Exception {
   
      if (fileName.isEmpty()) {
         System.out.println("Input file name is empty.");
         return false;
      }
      
      java.io.File file = new java.io.File(fileName);
      Scanner fileInput = new Scanner(file, "UTF-8");

      // Load all ads from the data file into the database
      int count = 0;
      while (fileInput.hasNext()) {
         String line = fileInput.nextLine();
         
         // Drop the first line that is the header line of the csv file
         if (count == 0) {
            count++;
            continue;
         }
         
         AT ad = new AT();
            
         // Read each of a piece of ad and put its fields into an AT object
         // Get id
         int startPos = 0;
         int delimPos = line.indexOf(Constants.AT_FIELD_DELIMITER, startPos);
         int id = Integer.parseInt(line.substring(0, delimPos));
         ad.setID(id);
         startPos = delimPos + 1;
         
         // Get state code
         delimPos = line.indexOf(Constants.AT_FIELD_DELIMITER, startPos);
         String stateCode = line.substring(startPos, delimPos);
         ad.setStateCode(stateCode);
         startPos = delimPos + 1;
         
         // Get text   
         delimPos = line.lastIndexOf("\""); // Get the position of the last '"' character in the description string
         // The data file was not created perfectly sanitary: some description text may not always end with '"' char.
         // In these defect cases, we need to handle the end position alternatively
         byte[] text;
         String sText = "";
         if (delimPos != -1) {   // If the description is not empty
            if (delimPos == startPos) { // No '"' at the end of the description text
               delimPos = line.length();
            }
            sText = line.substring(startPos, delimPos); // Note the last '"' char is exclusive
            if (sText.charAt(0) == '\"')
               sText = sText.substring(1); // Remove the first '"' char in the description string
         }
         text = sText.toLowerCase().getBytes(); // To make the search case insensive
         ad.setText(text);
         
         // Get Empty
         ad.setEmpty(sText.isEmpty());
         
         // Save ad into the Ad repository
         adRops.add(ad);
         
         count++;
      }
      
      return true; // Loading file is successful
   }
}