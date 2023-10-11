package org.openjfx.DatabaseManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StudentsDatabase implements DatabaseInterface, TableInterface {

   String URL, userName, passWord;
   Connection connection;

   //constructor
   public StudentsDatabase(String URL, String userName, String passWord) throws SQLException {

      this.URL = URL;
      this.userName = userName;
      this.passWord = passWord;
      this.connection = GetConnection(URL, userName, passWord);

      //make a database
      TableInterface.DropDB(connection, "ScheduleDataBase");
      TableInterface.CreateDB(connection, "ScheduleDataBase");
      TableInterface.UseDB(connection, "ScheduleDataBase");
      System.out.println("Database Created!");
   }

   //get connection between java environment and mySql database
   public Connection GetConnection(String URL, String userName, String passWord) {

      Connection connection = null;

      try {
         connection = DriverManager.getConnection(URL, userName, passWord);
         System.out.println("\nSuccesfully connected to MySQL Database!\n");
      } catch (SQLException e) {
         System.out.println(e);
      }

      return connection;
   }

   @Override
   public Connection getConnection(String URL, String userName, String passWord) {
      return this.connection;
   }

   public class Schedule {

      ResultSet ScheduleResults;

      public Schedule() throws SQLException {
         //create the table
         TableInterface.DropTableIfExists(connection, "Schedule");
         TableInterface.CreateTable(connection, DatabaseInterface.DDLCreateSchedule);

         //populate table
         TableInterface.PopulateTable(connection, "Schedule", "Schedule");
         System.out.println("Schedule Made and Populated!");

         ScheduleResults = TableInterface.GetTable(connection, "Schedule");
      }

      public void UpdateInstructor(String NewInstructor, String CourseID, String SectionNo) throws SQLException {
         TableInterface.UpdateInstructor(connection, NewInstructor, CourseID, SectionNo);
      }

      public ResultSet GetScheduleResults() {
         return this.ScheduleResults;
      }
   }

   public class Courses {
      
      ResultSet CoursesResults;

      public Courses() throws SQLException {
         //create the table
         TableInterface.DropTableIfExists(connection, "Courses");
         TableInterface.CreateTable(connection, DatabaseInterface.DDLCreateCourses);

         //populate table
         TableInterface.InsertIntoCourses(connection, "Courses");
         System.out.println("Courses Made and Populated!");

         CoursesResults = TableInterface.GetTable(connection, "Courses");
      }

      public ResultSet GetCoursesResults() {
         return this.CoursesResults;
      }
   }

   public class Students {

      ResultSet StudentsResults;

      public Students() throws SQLException {
         //create the table
         TableInterface.DropTableIfExists(connection, "Students");
         TableInterface.CreateTable(connection, DatabaseInterface.DDLCreateStudents);

         //populate table
         TableInterface.PopulateTable(connection, "Students", "Students");
         System.out.println("Students Made and Populated!");

         StudentsResults = TableInterface.GetTable(connection, "Students");
      }

      public ResultSet GetStudentsResults() {
         return this.StudentsResults;
      }
   }

   public class Classes {
      
      ResultSet ClassesResults;

      public Classes() throws SQLException {
         //create the table
         TableInterface.DropTableIfExists(connection, "Classes");
         TableInterface.CreateTable(connection, DatabaseInterface.DDLCreateClasses);

         //populate table
         TableInterface.PopulateTable(connection, "Classes", "Classes");
         System.out.println("Classes Made and Populated!");

         ClassesResults = TableInterface.GetTable(connection, "Classes");
      }

      public void UpdateGrade(String NewGrade, String StudentID) throws SQLException {
         TableInterface.UpdateGrade(connection, NewGrade, StudentID);
      }

      public ResultSet GetClassesResults() {
         return this.ClassesResults;
      }
   }

   public class AggregateGrades {
      
      ResultSet GradesResults;

      public AggregateGrades() throws SQLException {
         //create the table
         TableInterface.DropTableIfExists(connection, "AggregateGrades");
         TableInterface.CreateTable(connection, DatabaseInterface.DDLCreateAggregate);

         //populate table
         TableInterface.CountGrades(connection);
         System.out.println("Grades Made and Populated!");

         GradesResults = TableInterface.GetTable(connection, "Classes");
      }

      public ResultSet GetGradesResults() {
         return this.GradesResults;
      }
   }

   public Map<Character, Integer> GetAggregateGrades(String TableName) {
      Map<Character, Integer> AggregateGradesMap = new HashMap<>();

      try {
         ResultSet RS = TableInterface.GetTable(connection, TableName);

         while (RS.next()) {
            AggregateGradesMap.put(RS.getString("Grade").charAt(0), RS.getInt("NumberOfStudents"));
         }
      } catch(SQLException e) {
         System.out.println(e);
      }

      return AggregateGradesMap;
   }
}