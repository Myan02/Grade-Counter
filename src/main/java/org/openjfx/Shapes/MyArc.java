package org.openjfx.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import java.lang.Math;

public class MyArc extends MyShape {

    //variables
    MyCircle circle;

    double radius, startingAngle, arcExtent;

    public MyArc(double x, double y, double radius, MyColor color) {
        super(x, y, color);

        circle = new MyCircle(x, y, radius, MyColor.TRANSPARENT);

        SetRadius(radius);
    }

    public MyArc(double x, double y, double radius, double startingAngle, double arcExtent , MyColor color) {
        super(x, y, color);

        circle = new MyCircle(x, y, radius, MyColor.TRANSPARENT);

        SetRadius(radius);

        SetStartingAngle(startingAngle);
        SetArcExtent(arcExtent);
    }

    //set methods
    public void SetRadius(double radius) {
        this.radius = radius;
    }

    //degrees
    public void SetStartingAngle(double startingAngle) {
        this.startingAngle = startingAngle;
    }

    //degrees
    public void SetArcExtent(double arcExtent) {
        this.arcExtent = arcExtent;
    }

    //get methods
    public double GetRadius() {
        return radius;
    }

    public double GetStartingAngle() {
        return startingAngle;
    }

    public double GetArcExtent() {
        return arcExtent;
    }

    public MyCircle GetCircle() {
        return circle;
    }

    public double GetArcLength() {
        return ((GetArcExtent() - GetStartingAngle()) / 360.0) * Math.PI * (2 * GetRadius());
    }

    @Override
    public double GetPerimeter() {
        return GetArcLength() + (2 * GetRadius());
    }

    @Override
    public double GetArea() {
        return ((GetArcExtent() - GetStartingAngle()) / 360.0) * Math.PI * Math.pow(radius, 2.0);
    }

    @Override
    public MyRectangle GetMyBoundingRectangle() {
        return GetCircle().GetMyBoundingRectangle();
    }

    @Override
    public boolean PointInMyShape(MyPoint comparisonPoint) {

        double vLeg = GetRefPoint().GetY() - comparisonPoint.GetY();
        double hLeg = comparisonPoint.GetX() - GetRefPoint().GetX();

        double pAngle = Math.toDegrees(Math.atan2(vLeg, hLeg));

        if (pAngle < 0) {
            pAngle = 360.0 + (Math.toDegrees(Math.atan2(vLeg, hLeg)));
        }

        double distanceToPoint = Math.sqrt((Math.pow(vLeg, 2)) + (Math.pow(hLeg, 2)));

        return (pAngle <= (GetStartingAngle() + GetArcExtent())) && (pAngle >= GetStartingAngle()) && (distanceToPoint <= GetRadius());
    }

    @Override
    public void Draw(GraphicsContext GC) {
        GC.setFill(GetColor().GetJavaFXColor());
        GC.fillArc(GetRefPoint().GetX() - GetRadius(), GetRefPoint().GetY() - GetRadius(), GetRadius() * 2, GetRadius() * 2, GetStartingAngle(), GetArcExtent(), ArcType.ROUND);

        GC.setStroke(MyColor.BLACK.GetJavaFXColor());
        GC.setLineWidth(2);
        GC.strokeArc(GetRefPoint().GetX() - GetRadius(), GetRefPoint().GetY() - GetRadius(), GetRadius() * 2, GetRadius() * 2, GetStartingAngle(), GetArcExtent(), ArcType.ROUND);
    }

    @Override
    public void DrawBoundingRectangle(GraphicsContext GC) {
        GC.setStroke(MyColor.BLACK.GetJavaFXColor());
        GC.setLineWidth(2);
        GC.strokeRect(GetMyBoundingRectangle().GetRefPoint().GetX(), GetMyBoundingRectangle().GetRefPoint().GetY(), GetMyBoundingRectangle().GetWidth(), GetMyBoundingRectangle().GetHeight());
    }

    @Override
    public String toString() {
        return "My Arc's properties: \n" +
        "Center: (" + GetRefPoint().GetX() + ", " + GetRefPoint().GetY() + ") \n" +
        "Radius: " + GetRadius() + "\n" +
        "Width: " + GetCircle().GetWidth() + "\n" + 
        "Height: " + GetCircle().GetHeight() + "\n" +
        "Starting angle: " + GetStartingAngle() + "\n" +
        "arc angle: " + GetArcExtent() + "\n" +
        "Perimeter: " + GetPerimeter() + "\n" +
        "Area: " + GetArea() + "\n";
    }
}

