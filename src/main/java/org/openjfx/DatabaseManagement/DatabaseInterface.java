package org.openjfx.DatabaseManagement;

public interface DatabaseInterface { 

    //abstract string constants

    String DDLCreateSchedule =  "CREATE TABLE Schedule(" +
                                    "CourseID VARCHAR(12) NOT NULL UNIQUE," +
                                    "SectionNo INT NOT NULL UNIQUE," +
                                    "Title VARCHAR(128) NOT NULL," +
                                    "Year INT," +
                                    "Semester VARCHAR(6)," +
                                    "Instructor VARCHAR(64)," +
                                    "Department VARCHAR(32)," +
                                    "Program VARCHAR(64)," +
                                    "PRIMARY KEY(CourseID, SectionNo)" + 
                                ");";

    String DDLCreateCourses =   "CREATE TABLE Courses(" +
                                    "CourseID VARCHAR(12) REFERENCES Schedule(CourseID)," +
                                    "CourseTitle VARCHAR(128) REFERENCES Schedule(Title)," +
                                    "Department VARCHAR(32) REFERENCES Schedule(Department)," +
                                    "PRIMARY KEY(CourseID)" +
                                ");";

    String DDLCreateStudents =  "CREATE TABLE Students(" +
                                    "EmpID INT PRIMARY KEY," +
                                    "FirstName VARCHAR(64)," +
                                    "LastName VARCHAR(64)," +
                                    "Email VARCHAR(128) UNIQUE," +
                                    "Gender Char CHECK(Gender = \"M\" OR Gender = \"F\" OR Gender = \"U\")" +
                                ");";
                    
    String DDLCreateClasses =   "CREATE TABLE Classes(" +
                                    "CourseID VARCHAR(12) REFERENCES Schedule(CourseID)," +
                                    "SectionNO INT REFERENCES Schedule(SectionNo)," +
                                    "EmpID INT REFERENCES Students(EmpID)," +
                                    "Year INT REFERENCES Schedule(Year)," +
                                    "Semester VARCHAR(6) REFERENCES Schedule(Semester)," +
                                    "Grade CHAR CHECK(Grade = \"A\" OR Grade = \"B\" OR Grade = \"C\" OR Grade = \"D\" OR Grade = \"F\" OR Grade = \"W\")," +
                                    "PRIMARY KEY(CourseID, EmpID, SectionNo)" +
                                ");";

    String DDLCreateAggregate = "CREATE TABLE AggregateGrades(" +
                                    "Grade CHAR PRIMARY KEY," +
                                    "NumberOfStudents INT" +
                                ");";           
                                
    String DDLCountGrades = "INSERT INTO AggregateGrades\n" +
                            "SELECT Grade, COUNT(Grade) FROM Classes\n" +
                            "GROUP BY Grade;";
    
    //general static methods

    static String DDLLoad(String FileName, String TableToInsertTo) {
        return  "LOAD DATA INFILE 'C:\\\\ProgramData\\\\MySQL\\\\MySQL Server 8.0\\\\Uploads\\\\" + FileName + ".txt'\n" +
                "INTO TABLE " + TableToInsertTo + "\n" +
                "FIELDS TERMINATED BY '\\t'\n" + 
                "LINES TERMINATED BY '\\n'\n" +
                "IGNORE 1 LINES;";
    }
    
    static String DDLInsertCourses(String TableName) {
        return  "INSERT INTO " + TableName + "\n" +
                "SELECT CourseID, Title, Department FROM Schedule;";
    }

    static String DDLUpdateGrade(String NewGrade, String StudentID) {
        return  "UPDATE Classes\n" +
                "SET Grade = '" + NewGrade  + "'\n" +
                "WHERE EmpID = '" + StudentID + "';";
    }

    static String DDLUpdateInstructor(String NewInstructor, String CourseID, String SectionNo) {
        return  "UPDATE Schedule\n" +
                "SET Instructor = '" + NewInstructor + "'\n" + 
                "WHERE CourseID = '" + CourseID + "' AND SectionNo = '" + SectionNo + "';";
    }

    static String DDLDropDatabase(String DataBaseName) {
        return "DROP DATABASE IF EXISTS " + DataBaseName + ";";
    }

    static String DDLCreateDatabase(String DataBaseName) {
        return "CREATE DATABASE " + DataBaseName + ";";
    }

    static String DDLUseDatabase(String DataBaseName) {
        return "USE " + DataBaseName + ";";
    }
}