import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.util.Random;
import java.util.HashMap; 
import java.util.Map; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 

public class ArtistAnalyzer extends Application {

  ATRepos atDatabase = new ATRepos();
  public HashMap<String, Integer> testCountMap = new HashMap<String, Integer>();
  String dataFileName = "Artworks_new.csv";
  
  @Override
  public void start(Stage primaryStage) throws Exception {
  
    // Load the "Artworks Simplified" file into the AT data structure
    System.out.printf("Loading data from %s...\n", dataFileName);
    adDatabase.loadADs(dataFileName);
    System.out.println("Loading file is done.");
    
    // Prepare test data
    generateDummyData();

    // Layout the application GUI
    // Side offsets for outter pane in the scene
    FlowPane outterPane = new FlowPane();
    outterPane.setPadding(new Insets(Constants.SCENE_SIDE_MARGIN, Constants.SCENE_SIDE_MARGIN, 
                          Constants.SCENE_SIDE_MARGIN, Constants.SCENE_SIDE_MARGIN));
    outterPane.setHgap(Constants.PANE_H_GAP);
    outterPane.setVgap(Constants.PANE_V_GAP);
    
    // Search button
    StackPane btnPane = new StackPane();
    Button btnSearch = new Button("Search");
    btnSearch.setFont(Font.font("Arial", 18));
    btnSearch.setPrefWidth(Constants.BUTTON_WIDTH);
    btnPane.getChildren().add(btnSearch);
    
    // Search text field
    TextField srchText = new TextField();
    srchText.setPrefColumnCount(1);
    srchText.setPromptText("Enter a token key word to search in the data file");
    srchText.setPrefWidth(Constants.TEXTFIELD_WIDTH);
    srchText.setPrefHeight(Constants.TEXTFIELD_HEIGHT);
    srchText.setFont(Font.font("Arial", 16));
    
    // Search result bar chart
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
    barChart.setHorizontalGridLinesVisible(false);
    barChart.setVerticalGridLinesVisible(false);
    barChart.setLegendVisible(false);
    barChart.setCategoryGap(2);
    barChart.setBarGap(0);
    barChart.setMinWidth(Constants.SCENE_WIDTH*0.90);
    barChart.setMaxWidth(Constants.SCENE_WIDTH);
    
    xAxis.setLabel("State");
    yAxis.setLabel("Match Count");
    
    // Initialize the bar chart with empty or random data
    SearchResult result = new SearchResult();
//     result.set(testCountMap); // Uncomment this line to initialize the bar chart with random data
    updateBarChart(barChart, result);

    // Add event handler to the Search button
    EventHandler<ActionEvent> btnEvent = new EventHandler<ActionEvent>() { 
       public void handle(ActionEvent e) {
          // To make the search case insensive, change the key word to lower case
          String keyWord = srchText.getText().toLowerCase();
          
          // Reset the text field to empty
          srchText.setText("");
          
          // Search the key word throughout the ad database
          SearchEngine searchEng = new SearchEngine(adDatabase);
          SearchResult result = new SearchResult();
          searchEng.find(keyWord, result);
          
          // Update the bar chart
          updateBarChart(barChart, result);
       } 
    }; 
    
    // When button is pressed 
    btnSearch.setOnAction(btnEvent); 
   
    // Place nodes in the outter pane
    outterPane.getChildren().addAll(barChart, srchText, btnPane);
    
    // Construct the scene
    Scene scene = new Scene(outterPane, Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);
    
    // Stage
    primaryStage.setTitle("Rescue");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  // Generate dummy random count data
  private void generateDummyData() {
      Random random = new Random();
      for(int i = 0; i < Constants.NUMBER_OF_STATES; i++) {
         testCountMap.put(Constants.STATE_NAME_CODES[i], random.nextInt(100));
      }
  }
  
  // Update BarChart data with given result
  private void updateBarChart(BarChart<String, Number> bc, SearchResult result) {
      bc.getData().clear();
      XYChart.Series series = new XYChart.Series();
      for (int i = 0; i < Constants.NUMBER_OF_STATES; i++) {
         String state = result.getStateCodes().get(i);
         int count = result.getMatchCounts().get(i);
         series.getData().add(new XYChart.Data(state, count));
      }
      bc.getData().addAll(series);
  }
}