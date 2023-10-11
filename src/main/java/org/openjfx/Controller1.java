package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller1 {

   //create the stage and scene
   private Stage stage;
   private Scene scene;

   //number of slices the user wants to see
   int numberOfSlices;

   //create nodes
   @FXML
   private Label myLabel;
   @FXML
   private TextField myTextField;
   @FXML
   private Button myButton;
   
   //event: when continue button is clicked, go to next scene
   public void submit(ActionEvent event) throws Exception {
      try {
         myLabel.setVisible(false);

         //sets the user's input as a variable, throws exception if not 1 - M (6 FOR NUMBER OF AVAILABLE GRADES)
         numberOfSlices = Integer.parseInt(myTextField.getText());
         
         if (numberOfSlices < 1 || numberOfSlices > 6) {myLabel.setVisible(true);}
         if (numberOfSlices < 1 || numberOfSlices > 6) throw new Exception("Please choose a value from 1 - 6");

         //load the next scene
         FXMLLoader loader = new FXMLLoader(getClass().getResource("scene2.fxml"));
         loader.load();

         //get access to scene 2's methods
         Controller2 controller2 = loader.getController();
         scene = new Scene(controller2.Histogram(numberOfSlices));

         //set the new scene and stage
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.setTitle("Pie chart of: " + numberOfSlices);
         stage.show();

      } catch(NumberFormatException e) {
         myLabel.setVisible(true);
         System.out.println("Please enter only a number");
      } 

      myTextField.setText("");
   }
}
