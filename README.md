# Grade-Counter
A large application that can retrieve course information, organize into a MySQL DB, count the grade frequency, and display it using a piechart GUI.

WHAT IT DOES:

The program is a way to bring in many different concepts of programming together. By using a GUI library provided through JavaFX, we can create a pie chart that shows the percentage of students who received an A, B, C, etc. from a list of courses.
We build the pie chart from scratch, combining many primative and simple shapes. All of the information found within the text files are organized into MySQL tables to make data easier to retrieve. This data is then compiled into hashmaps then 
sorted, where the sorted information is based on their frequency. Finally, the hashmap is used as a base for the pie chart, creating slices with matchins sizes proportional to how frequent the grade received was
(If most students received 'A's, the 'A' slice would be the biggest). 

INSTRUCTIONS:

  1.  Make sure you download the Classes, Schedule, and Students text files. If you want you can use your own, just make sure they are in the same format and seperated by proper tabs or spaces.
  2
      a.  Copy these files into your MySQL Server uploads folder (my directory's location was: C:\ProgramData\MySQL Server 8.0\Uploads)
      b.  If your directory listed in step 2a is not the same as my directory, find the function "DDLLoad()" under "src/main/java/org/openjfx/DatabaseManagement/DatabaseInterface.java".
          Change the first string in the return statement to YOUR directory. If it is the same as mine, ignore and move on to step 3.
  3.  Find the file Controller2.java under the directory "src/main/java/org/openjfx/" and update the students database on line 26 with YOUR MySQL URL, username, and password.
  4.  Start your MySQL server and run the file!

FEEDBACK:
I made this back in 2022 while I was still learning Java. There are many issues with the code that make it more difficult to scale up, which is something I would fix if I recreated this app. 
If there is any feedback for this application, please reach out to me and let me know! I'm still learning and any bit of feedback helps a lot. 

