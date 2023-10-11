package org.openjfx.Shapes;

import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;

public class MyPoint {
        
    //private variables
    private double x, y;
    private MyColor color;

    //constructors
    public MyPoint() {
        SetX(0.0);
        SetY(0.0);
        SetColor(MyColor.BLACK);
    }

    public MyPoint(double x, double y) {
        SetX(x);
        SetY(y);
        SetColor(MyColor.BLACK);
    }

    public MyPoint(double x, double y, MyColor color) {
        SetX(x);
        SetY(y);
        SetColor(color);
    }

    //set Methods
    public void SetX(double x) {
        this.x = x;
    }

    public void SetY(double y) {
        this.y = y;
    }

    public void SetColor(MyColor color) {
        this.color = color;
    }

    //get methods
    public final double GetX() {
        return this.x;
    }

    public final double GetY() {
        return this.y;
    }

    public final MyColor GetColor() {
        return this.color;
    }

    //shift point
    public void ShiftPoint(double xShift, double yShift) {
        SetX(this.x += xShift);
        SetY(this.y += yShift);
    }

    public void ShiftXValue(double xShift) {
        SetX(this.x += xShift);
    }

    public void ShiftYValue(double yShift) {
        SetY(this.y += yShift);
    }

    //distance
    public final double DistanceFromOrigin() {
        return Math.round(Math.sqrt(Math.pow(GetX(), 2.0) + Math.pow(GetY(), 2.0)));
    }

    public final double DistanceFromAnotherPoint(MyPoint secondPoint) {
        return Math.round(Math.sqrt(Math.pow((secondPoint.GetX() - GetX()), 2.0) + Math.pow((secondPoint.GetY() - GetY()), 2.0)));
    }

    //angle (in degrees) with the x-axis of the line extending from this point to another point
    public final double AngleRelativeToXAxis(MyPoint secondPoint) {
        return Math.toDegrees(Math.atan2(secondPoint.GetY() - GetY(), secondPoint.GetX() - GetX()));
    }

    //draw point
    public void Draw(GraphicsContext GC) {
        GC.setFill(GetColor().GetJavaFXColor());
        GC.fillOval(GetX(), GetY(), 1, 1);
    }

    //String representation of the point; it's coordinates
    @Override
    public String toString() {
        return "Point (" + GetX() + ", " + GetY() + ")\n" ;
    }
}

