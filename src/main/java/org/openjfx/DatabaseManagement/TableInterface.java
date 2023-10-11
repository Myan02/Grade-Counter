package org.openjfx.DatabaseManagement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface TableInterface {
   
   Connection getConnection(String URL, String userName, String passWord);

   static void DropDB(Connection connection, String DataBaseName) throws SQLException {
      PreparedStatement myStatement = connection.prepareStatement(DatabaseInterface.DDLDropDatabase(DataBaseName));

      try {
         myStatement.executeUpdate();
      } catch(SQLException e) {
         System.out.println(e);
      }
   }

   static void CreateDB(Connection connection, String DataBaseName) throws SQLException {
      PreparedStatement myStatement = connection.prepareStatement(DatabaseInterface.DDLCreateDatabase(DataBaseName));

      try {
         myStatement.executeUpdate();
      } catch(SQLException e) {
         System.out.println(e);
      }
   }

   static void UseDB(Connection connection, String DataBaseName) throws SQLException {
      PreparedStatement myStatement = connection.prepareStatement(DatabaseInterface.DDLUseDatabase(DataBaseName));

      try {
         myStatement.executeUpdate();
      } catch(SQLException e) {
         System.out.println(e);
      }
   }

   static void CreateTable(Connection connection, String DDLCreateTable) throws SQLException {
      
      PreparedStatement myStatement = connection.prepareStatement(DDLCreateTable);

      try {
         myStatement.executeUpdate();
      } catch(SQLException e) {
         System.out.println(e);
      }
   }

   static void DropTableIfExists(Connection connection, String TableName) throws SQLException {
      
      PreparedStatement myStatement = connection.prepareStatement("DROP TABLE IF EXISTS " + TableName + ";");

      try {
         myStatement.executeUpdate();
      } catch(SQLException e) {
         System.out.println(e);
      }
   }

   static void PopulateTable(Connection connection, String FileName, String TableToInsertInto) throws SQLException {

      PreparedStatement myStatement = connection.prepareStatement(DatabaseInterface.DDLLoad(FileName, TableToInsertInto));

      try {
         myStatement.executeUpdate();
      } catch(SQLException e) {
         System.out.println(e);
      }
   }

   static void InsertIntoCourses(Connection connection, String TableName) throws SQLException {
      
      PreparedStatement myStatement = connection.prepareStatement(DatabaseInterface.DDLInsertCourses(TableName));

      try {
         myStatement.executeUpdate();
      } catch(SQLException e) {
         System.out.println(e);
      }
   }

   static void UpdateGrade(Connection connection, String NewGrade, String StudentID) throws SQLException {
      
      PreparedStatement myStatement = connection.prepareStatement(DatabaseInterface.DDLUpdateGrade(NewGrade, StudentID));

      try {
         myStatement.executeUpdate();
      } catch(SQLException e) {
         System.out.println(e);
      }
   }
   
   static void UpdateInstructor(Connection connection, String NewInstructor, String CourseID, String SectionNo) throws SQLException {
      
      PreparedStatement myStatement = connection.prepareStatement(DatabaseInterface.DDLUpdateInstructor(NewInstructor, CourseID, SectionNo));

      try {
         myStatement.executeUpdate();
      } catch(SQLException e) {
         System.out.println(e);
      }
   }

   static void CountGrades(Connection connection) throws SQLException {

      PreparedStatement myStatement = connection.prepareStatement(DatabaseInterface.DDLCountGrades);

      try {
         myStatement.executeUpdate();
      } catch(SQLException e) {
         System.out.println(e);
      }
   }

   static ResultSet GetTable(Connection connection, String TableName) throws SQLException {
      
      ResultSet RS = null;

      PreparedStatement myStatement = connection.prepareStatement("SELECT * FROM " + TableName);

      try {
         RS = myStatement.executeQuery();
      } catch(SQLException e) {
         System.out.println(e);
      }

      return RS;
   }

   
}