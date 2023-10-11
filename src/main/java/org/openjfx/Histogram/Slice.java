package org.openjfx.Histogram;

import org.openjfx.Shapes.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import java.lang.Math;

public class Slice extends MyArc {

    //the character of this slice object
    Character sliceChar;
    Integer frequency;

    //constructors
    public Slice(double x, double y, double radius, Integer frequency, MyColor color) {
        super(x, y, radius, color);

        SetFrequency(frequency);
    }

    public Slice(Character ch, double x, double y, double radius, double startingAngle, double arcExtent, Integer frequency, MyColor color) {
        super(x, y, radius, startingAngle, arcExtent, color);

        SetChar(ch);
        SetFrequency(frequency);
    }

    //set methods
    private void SetChar(Character ch) {
        this.sliceChar = ch;
    }

    //get methods
    public Character GetChar() {
        return this.sliceChar;
    }

    public Double GetSliceProbability() {
        return (GetArcExtent() / (2 * Math.PI)) * (Math.PI / 180);
    }

    public void SetFrequency(Integer value) {
        this.frequency = value;
    }

    public Integer GetFrequency() {
        return this.frequency;
    }

    //increase the arcExtent by a double 
    public void AddAngle(double increase) {
        SetArcExtent(GetArcExtent() + increase);
    }

    public void AddFrequency(int increase) {
        SetFrequency(GetFrequency() + increase);
    }

    //returns a string of the character and its probability
    public String GetText() {
        String textStr;
        double scale = Math.pow(10, 4);

        if (GetChar() == null) {
            textStr = "Other characters: " + Math.round(GetSliceProbability() * scale) / scale + " [" + GetFrequency() + "]";
        } else {
            textStr = GetChar().toString() + ": " + (Math.round(GetSliceProbability() * scale) / scale) + " [" + GetFrequency() + "]";
        }

        return textStr;
    }

    //returns a point object for the location of the text 
    public MyPoint GetSlicePoint() {
        MyPoint point = new MyPoint(GetCircle().GetRefPoint().GetX(), GetCircle().GetRefPoint().GetY());

        //check if its the sum slice
        if (GetChar() == null) {
            if (GetStartingAngle() <= 90 || GetStartingAngle() >= 270) {
                point.ShiftPoint(GetRadius() * Math.cos(Math.toRadians(GetStartingAngle())), -(GetRadius() * Math.sin(Math.toRadians(GetStartingAngle()))));
            } else if (GetStartingAngle() > 90 || GetStartingAngle() < 270){
                point.ShiftPoint(GetRadius() * Math.cos(Math.toRadians(GetStartingAngle())) - 135, -(GetRadius() * Math.sin(Math.toRadians(GetStartingAngle()))));
            }
        //all other characters
        } else {
            if (GetStartingAngle() <= 90 || GetStartingAngle() >= 270) {
                point.ShiftPoint(GetRadius() * Math.cos(Math.toRadians(GetStartingAngle())), -(GetRadius() * Math.sin(Math.toRadians(GetStartingAngle()))));
            } else if (GetStartingAngle() > 90 || GetStartingAngle() < 270){
                point.ShiftPoint(GetRadius() * Math.cos(Math.toRadians(GetStartingAngle())) - 50, -(GetRadius() * Math.sin(Math.toRadians(GetStartingAngle()))));
            }
        }
            
        return point;
    }

    //draw the slice and its text
    @Override
    public void Draw(GraphicsContext GC) {
        GC.setFill(GetColor().GetJavaFXColor());
        GC.fillArc(GetRefPoint().GetX() - GetRadius(), GetRefPoint().GetY() - GetRadius(), GetRadius() * 2, GetRadius() * 2, GetStartingAngle(), GetArcExtent(), ArcType.ROUND);   

        GC.setFill(MyColor.BLACK.GetJavaFXColor());
        GC.setStroke(MyColor.BLACK.GetJavaFXColor());
        GC.setLineWidth(1);
        GC.strokeText(GetText(), GetSlicePoint().GetX(), GetSlicePoint().GetY());
    }

    //to string method
    @Override
    public String toString() {
        return "My slice properties:" + "\n" +
                "Slice Character: " + GetChar() + "\n" +
                "Slice Probability: " + GetSliceProbability();
    }
}

