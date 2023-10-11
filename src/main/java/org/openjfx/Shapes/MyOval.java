package org.openjfx.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.lang.Math;

public class MyOval extends MyShape {

    //variables
    private double width, height, majorAxis, minorAxis;
    
    public MyOval() {
        //sets the x, y, and color of the oval (default)
        super();

        SetWidth(100.0);
        SetHeight(100.0);

        SetMajorAndMinor();
    }

    public MyOval(double width, double height) {
        super();

        SetWidth(width);
        SetHeight(height);

        SetMajorAndMinor();
    }

    public MyOval(double x, double y, double width, double height, MyColor color) {
        super(x, y, color);

        SetWidth(width);
        SetHeight(height);

        SetMajorAndMinor();
    }

    //set methods
    public void SetWidth(double width) {
        this.width = width;
    }

    public void SetHeight(double height) {
        this.height = height;  
    }

    //sets the major and minor axis depending on the orientation of the oval
    public void SetMajorAndMinor() {

        if (GetWidth() < GetHeight()) {
            this.majorAxis = GetHeight() * 2;
            this.minorAxis = GetWidth() * 2;
        } else if (GetWidth() >= GetHeight()) {
            this.majorAxis = GetWidth() * 2;
            this.minorAxis = GetHeight() * 2;
        } 
    }
    
    //get methods
    public double GetWidth() {
        return this.width; 
    }

    public final double GetHeight() {
        return this.height; 
    }

    public final double GetMajorAxis() {
        return this.majorAxis;
    }

    public final double GetMinorAxis() {
        return this.minorAxis;
    }

    //A from the equation of an ellipse
    public final double GetA() {
        return GetMajorAxis() / 2;
    }

    //B from the equation of an ellipse
    public final double GetB() {
        return GetMinorAxis() / 2;
    }

    //returns the perimeter of the oval
    @Override
    public double GetPerimeter() {

        double h = Math.pow((GetA() - GetB()), 2.0) / Math.pow((GetA() + GetB()), 2.0);

        return (Math.PI * (GetA() + GetB())) * (1.0 + ((3.0 * h) / (10.0 + (Math.sqrt(4.0 - (3.0 * h))))));
    }

    //returns the area of the oval
    @Override
    public double GetArea() {
        return Math.round(GetA() * GetB() * Math.PI);
    }

    //returns a rectangle outlining the bounding area of the oval
    @Override
    public MyRectangle GetMyBoundingRectangle() {
        return new MyRectangle(GetRefPoint().GetX() - GetWidth(), GetRefPoint().GetY() - GetHeight(), GetWidth() * 2, GetHeight() * 2, MyColor.TRANSPARENT);
    }

    //returns true if a random point "comparisonPoint" is in, or on, the oval object
    @Override
    public boolean PointInMyShape(MyPoint comparisonPoint) {

        double p;

        if (GetWidth() > GetHeight()) {
            p = ((((double) Math.pow((comparisonPoint.GetX() - GetRefPoint().GetX()), 2.0) / (double) Math.pow(GetA(), 2.0)) + ((double)Math.pow((comparisonPoint.GetY() - GetRefPoint().GetY()), 2.0) / (double) Math.pow(GetB(), 2.0))));
        } else {
            p = ((((double) Math.pow((comparisonPoint.GetX() - GetRefPoint().GetX()), 2.0) / (double) Math.pow(GetB(), 2.0)) + ((double)Math.pow((comparisonPoint.GetY() - GetRefPoint().GetY()), 2.0) / (double) Math.pow(GetA(), 2.0))));
        }
            
        if (p <= 1) {
            return true;
        } else {
            return false;
        }
    }

    //draws the oval
    @Override
    public void Draw(GraphicsContext GC) {
        GC.setFill(GetColor().GetJavaFXColor());
        GC.fillOval(GetRefPoint().GetX() - GetWidth(), GetRefPoint().GetY() - GetHeight(), GetWidth() * 2, GetHeight() * 2);
        GC.setFill(Color.BLACK);
        GC.setLineWidth(2);
        GC.strokeOval(GetRefPoint().GetX() - GetWidth(), GetRefPoint().GetY() - GetHeight(), GetWidth() * 2, GetHeight() * 2);
    }

    //draws the bounding rectangle
    @Override
    public void DrawBoundingRectangle(GraphicsContext GC) {
        GC.setFill(Color.BLACK);
        GC.setLineWidth(2);
        GC.strokeRect(GetMyBoundingRectangle().GetRefPoint().GetX(), GetMyBoundingRectangle().GetRefPoint().GetY(), GetMyBoundingRectangle().GetWidth(), GetMyBoundingRectangle().GetHeight());
    }

    //string representation of an oval
    @Override
    public String toString() {
        return "My oval's properties: \n" +
                "Reference Point (center): (" + GetRefPoint().GetX() + ", " + GetRefPoint().GetY() + ") \n" +
                "Width: " + GetWidth() + "\n" + 
                "Height: " + GetHeight() + "\n" +
                "Major Axis: " + GetMajorAxis() + "\n" +
                "Minor Axis: " + GetMinorAxis() + "\n" +
                "Perimeter: " + GetPerimeter() + "\n" +
                "Area: " + GetArea() + "\n" + 
                "A and B:" + GetA() + ", " + GetB() + "\n";
    }
}

