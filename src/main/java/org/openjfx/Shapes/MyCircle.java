package org.openjfx.Shapes;

import java.lang.Math;

public class MyCircle extends MyOval {

    //variables
    private double radius;

    public MyCircle(double x, double y) {
        super(x, y, 50, 50, MyColor.BLACK);

        SetRadius(50.0);
        SetMajorAndMinor();
    }

    public MyCircle(double x, double y, MyColor color) {
        super(x, y, 50, 50, color);

        SetRadius(50.0);
        SetMajorAndMinor();
    }

    public MyCircle(double radius) {
        super(0, 0, radius, radius, MyColor.BLACK);

        SetRadius(radius);
        SetMajorAndMinor();
    }

    public MyCircle(double radius, MyColor color) {
        super(0, 0, radius, radius, color);

        SetRadius(radius);
        SetMajorAndMinor();
    }

    public MyCircle(double x, double y, double radius, MyColor color) {
        super(x, y, radius, radius, color);

        SetRadius(radius);
        SetMajorAndMinor();
    }

    //copy constructor
    public MyCircle(MyCircle circle) {
        super(circle.GetRefPoint().GetX(), circle.GetRefPoint().GetY(), circle.GetRadius(), circle.GetRadius(), circle.GetColor());

        SetRadius(circle.radius);
        SetMajorAndMinor();
    }

    //setter method
    public void SetRadius(double radius) {
        this.radius = radius;
    }

    //getter method
    public double GetRadius() {
        return radius;
    }

    //return circumference
    @Override
    public double GetPerimeter() {
        return Math.round(2 * Math.PI * GetRadius());
    }

    //Return area
    @Override
    public double GetArea() {
        return Math.round(Math.PI * Math.pow(GetRadius(), 2.0));
    }

    //toString method
    @Override
    public String toString() {
        return "My circle's properties: \n" +
        "Center: (" + GetRefPoint().GetX() + ", " + GetRefPoint().GetY() + ") \n" +
        "Radius: " + GetRadius() + "\n" +
        "Width: " + GetWidth() + "\n" + 
        "Height: " + GetHeight() + "\n" +
        "Perimeter: " + GetPerimeter() + "\n" +
        "Area: " + GetArea() + "\n";
    }
}

