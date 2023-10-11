package org.openjfx.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyRectangle extends MyShape {

    //variables
    private double width, height;

    //constructors
    public MyRectangle() {
        //sets the x, y, and color of the rectangle 
        super();

        SetWidth(200.0);
        SetHeight(100.0);
    }

    public MyRectangle(double width, double height) {
        super();

        SetWidth(width);
        SetHeight(height);
    }

    public MyRectangle(double x, double y, double width, double height, MyColor color) {
        super(x, y, color);

        SetWidth(width);
        SetHeight(height);
    }

    //set methods
    public void SetWidth(double width) {
        this.width = width;
    }

    public void SetHeight(double height) {
        this.height = height;
    }

    //get methods
    public final double GetWidth() {
        return width;
    }

    public final double GetHeight() {
        return height;
    }

    //perimeter of rectangle
    @Override
    public double GetPerimeter() {
        return (GetHeight() * 2) + (GetWidth() * 2);
    }

    //area of rectangle
    @Override
    public double GetArea() {
        return GetHeight() * GetWidth();
    }

    @Override
    public MyRectangle GetMyBoundingRectangle() {
        return this;
    }

    @Override
    public void Draw(GraphicsContext GC) {
        GC.setFill(GetColor().GetJavaFXColor());
        GC.fillRect(GetRefPoint().GetX(), GetRefPoint().GetY(), GetWidth(), GetHeight());
        GC.setStroke(Color.BLACK);
        GC.setLineWidth(2);
        GC.strokeRect(GetRefPoint().GetX(), GetRefPoint().GetY(), GetWidth(), GetHeight());
    }

    @Override
    public void DrawBoundingRectangle(GraphicsContext GC) {
        GC.setFill(Color.BLACK);
        GC.setLineWidth(2);
        GC.strokeRect(GetMyBoundingRectangle().GetRefPoint().GetX(), GetMyBoundingRectangle().GetRefPoint().GetY(), GetMyBoundingRectangle().GetWidth(), GetMyBoundingRectangle().GetHeight());
    }

    @Override
    public boolean PointInMyShape(MyPoint comparisonPoint) {
        return (((comparisonPoint.GetX() >= GetRefPoint().GetX()) && comparisonPoint.GetX() <= (GetRefPoint().GetX() + GetWidth())) &&
                ((comparisonPoint.GetY() >= GetRefPoint().GetY()) && comparisonPoint.GetY() <= (GetRefPoint().GetY() + GetHeight())));
    }

    //string representation of the rectangle's properties
    @Override
    public String toString() {
        return "My rectangle's properties: \n" +
                "Reference Point: (" + GetRefPoint().GetX() + ", " + GetRefPoint().GetY() + ") \n" +
                "Width: " + GetWidth() + "\n" + 
                "Height: " + GetHeight() + "\n" +
                "Perimeter: " + GetPerimeter() + "\n" +
                "Area: " + GetArea() + "\n";
    }
}

