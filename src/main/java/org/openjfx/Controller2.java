package org.openjfx;

import org.openjfx.DatabaseManagement.StudentsDatabase;
import org.openjfx.Histogram.*;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Controller2 {
   
   //create a canvas 
   @FXML
   private Canvas canvas;

   //make the piechart in the second scene
   public StackPane Histogram(int numberOfSlices) throws SQLException {
      int width = 600, height = 400;
      int radius = 150;

      canvas = new Canvas(width, height);
      GraphicsContext GC = canvas.getGraphicsContext2D();

      //UPDATE THIS WITH YOUR SQL INFO
      StudentsDatabase db = new StudentsDatabase("EXMAPLE: jdbc:mysql://localhost:3306", "root", "PASSWORD");

      StudentsDatabase.Schedule schedule = db.new Schedule();

      //update the instructor of a course
      schedule.UpdateInstructor("Hesham Auda", "10400 PR1", "32125");

      StudentsDatabase.Courses courses = db.new Courses();
      StudentsDatabase.Students students = db.new Students();
      StudentsDatabase.Classes classes = db.new Classes();

      //update the grades of students in a class
      classes.UpdateGrade("W", "001");
      classes.UpdateGrade("C", "013");

      StudentsDatabase.AggregateGrades grades = db.new AggregateGrades();

      //create the histogram 
      HistogramAlphaBet Histogram = new HistogramAlphaBet();
      Histogram.MakehistogramFromMap(db.GetAggregateGrades("AggregateGrades"));

      //create and draw the pie chart
      HistogramAlphaBet.MyPieChart pie = Histogram.new MyPieChart(numberOfSlices, width / 2, height / 2, radius);
      pie.Draw(GC);

      //make a stack pane
      StackPane SP = new StackPane(canvas);
      SP.setStyle("-fx-background-color: silver");

      //store the piechart as a child node in the stack pane
      return SP;
   }
}


