package org.openjfx.Histogram;

import org.openjfx.Shapes.*;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HistogramAlphaBet {

    //create hashmaps to hold the character and each frequency and probability
    Map<Character, Integer> frequency = new HashMap<Character, Integer>();
    Map<Character, Double> probability = new HashMap<Character, Double>();

    //create hashmaps to hold sorted information on the frequency and probability
    Map<Character, Integer> sortedFrequency;
    Map<Character, Double> sortedProbability;

    //private scanner and text variables
    private static Scanner inputFile;
    private String text = "";

    //make a histogram from any map (sql tables)
    public void MakehistogramFromMap(Map<Character, Integer> map) {
        Sort(map);
    }

    //make a histogram from a text file
    public void MakehistogramFromFile(String fileName) {
        OpenFile(fileName);
        ReadFile();
        CloseFile();
    }

    //opens the file from a folder, throws exception if the file is incorrect
    private void OpenFile(String fileName) {

        try {
            File file = new File("C:\\Users\\babur\\Desktop\\text\\" + fileName);
            inputFile = new Scanner(file);

        } catch(IOException ioException) {
            System.err.println("No File Selected");
        }
    }

    //reads the contents of the file and procedes with the histogram
    private void ReadFile() {
            
        try {
            //fill string "text" with the entire text file
            while (inputFile.hasNext()) {
                text += inputFile.nextLine().replaceAll("[^a-zA-Z]", "").toLowerCase();
            }

            //print the number of alphanumeric characters
            System.out.println("Number of characters: " + text.length() + "\n");

            //find the frequency of each character
            for (int i = 0; i < text.length(); i++) {
                Character myChar = text.charAt(i);
                incrementFrequency(frequency, myChar);
            }

            Sort(frequency);

        } catch(NoSuchElementException elementException) {
            System.err.println("Wrong input, please try again...");

        } catch(IllegalStateException illegalStateException) {
            System.out.println("Error with file, please try again...");
        }
    }

    //takes a character: if it exists, increments the key, if it does not, put it into the map and set the key to 0, then increments
    private<K> void incrementFrequency(Map<K, Integer> map, K key) {
        map.putIfAbsent(key, 0);
        map.put(key, map.get(key) + 1);
    }

    //sorts a map in decreasing order and finds the probabilities
    private void Sort(Map<Character, Integer> map) {

        //sort the frequency in decreasing order
        sortedFrequency =    map
                            .entrySet()
                            .stream()
                            .sorted((i1, i2) -> i1.getKey().compareTo(i2.getKey()))
                            .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        
        //find the sum of all of the frequencies (must equal the number of alphanumeric characters)
        long cumulativeFrequency = sortedFrequency.values().stream().reduce(0, Integer::sum);

        //print each character and frequency, then print the sum of frequencies
        System.out.print("\nCharacters and their Frequencies:\n");
        sortedFrequency.forEach((K, V) -> System.out.println(K + ": " + V));
        System.out.println("\nCumulative Frequency: " + cumulativeFrequency + "\n");

        //find the probability of each character, put into map
        double InverseCumulativeFrequency = 1.0 / cumulativeFrequency;
        for (Character key : sortedFrequency.keySet()) {
            probability.put(key, (double) sortedFrequency.get(key) * InverseCumulativeFrequency);
        }

        //sort the probability in decreasing order
        sortedProbability =  probability
                            .entrySet()
                            .stream()
                            .sorted((i1, i2) -> i1.getKey().compareTo(i2.getKey()))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
                                                    
        //print the probabilies 
        System.out.print("Characters and their Probabilities:\n");
        sortedProbability.forEach((K, V) -> System.out.println(K + ": " + V));
        System.out.println("");
        System.out.print("Sum of Probabilities: " + probability.values().stream().reduce(0.0, Double::sum));
    }

    //close the file
    private void CloseFile() {
        if (inputFile != null){
            inputFile.close();
        }
    }

    //returns a hashmap of the descending frequency
    public Map<Character, Integer> GetSortedFrequency() {
        return sortedFrequency;
    }

    //returns a hashmap of the descending probability
    public Map<Character, Double> GetSortedProbability() {
        return sortedProbability;
    }

    //returns the probability of any character ch
    public double GetProbabilityOfChar(Character ch) {
        return GetSortedProbability().get(ch) * (2 * Math.PI) * (180.0 / Math.PI);
    }

    public Integer GetFrequencyOfChar(Character ch) {
        return GetSortedFrequency().get(ch);
    }


    //a class to make piecharts out of histograms
    public class MyPieChart {

        //variables
        private int N;  //number of slices to show
        private int M;  //total number of slices
        Map<Character, Slice> myChart = new LinkedHashMap<Character, Slice>();

        //makes the piechart's outline 
        MyCircle pieCircle;
        
        double startingAngle;
        Slice otherSlice;   //last sum slice of the pieChart 

        //constructor: creates a pie and initializes how many character slices the user wants to see
        public MyPieChart(int N, double x, double y, double radius) {
            pieCircle = new MyCircle(x, y, radius, MyColor.BLACK);
            SetTotalSlices();

            try {
                SetNumberOfSlices(N);
            } catch (Exception e) {
                
            }

            SetStartingAngle(0.0);
            MakeSliceMap();
        }

        //set methods
        private void SetStartingAngle(double startingAngle) {
            this.startingAngle = startingAngle;
        }

        //throws exception if N is not between 1 and 26
        public void SetNumberOfSlices(int N) throws Exception {
            if (N < 1 || N > M) throw new Exception("Please choose another Value...");
            else this.N = N;
        }

        public void SetTotalSlices() {
            this.M = GetSortedProbability().size();
        }

        //get methods
        private double GetStartingAngle() {
            return startingAngle;
        }

        public MyCircle GetPieCircle() {
            return pieCircle;
        }

        //populate the hashmap myChart with each character and the corresponding slice object
        public void MakeSliceMap() {
            for (Character key : GetSortedProbability().keySet()) {
                myChart.putIfAbsent(key, new Slice(key, GetPieCircle().GetRefPoint().GetX(), GetPieCircle().GetRefPoint().GetY(),GetPieCircle().GetRadius(), GetStartingAngle(), GetProbabilityOfChar(key), GetFrequencyOfChar(key), MyColor.GetRandomColor()));
                SetStartingAngle(GetStartingAngle() + GetProbabilityOfChar(key));
            }
        }
        
        //draw method 
        public void Draw(GraphicsContext GC) {
            int i = 0;
            for (Character key : myChart.keySet()) {
                if (i < N) {
                    myChart.get(key).Draw(GC);
                    i++;
                } else {
                    if (i == N) {
                        otherSlice = new Slice(GetPieCircle().GetRefPoint().GetX(), GetPieCircle().GetRefPoint().GetY(), GetPieCircle().GetRadius(), 0, MyColor.GetRandomColor());
                        otherSlice.SetStartingAngle(myChart.get(key).GetStartingAngle());
                    }

                    otherSlice.AddAngle(myChart.get(key).GetArcExtent());
                    otherSlice.AddFrequency(myChart.get(key).GetFrequency());
                    i++;
                }
            }
            //draws the last sum slice if you picked a value for N less than the number of total characters available
            if (N < M) {
                otherSlice.Draw(GC);
            }
        }
    }
}

