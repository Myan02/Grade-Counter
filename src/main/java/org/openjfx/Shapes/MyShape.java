package org.openjfx.Shapes;

import javafx.scene.canvas.GraphicsContext;

public abstract class MyShape implements MyShapeInterface {

    //variables
    private MyPoint refPoint;
    private MyColor color;

    //constructors
    public MyShape() {
        refPoint = new MyPoint();
        SetColor(MyColor.BLACK);
    }

    public MyShape(double x, double y, MyColor color) {
        refPoint = new MyPoint(x, y, color);
        SetColor(color);
    }

    //set methods
    public void SetRefPoint(double x, double y) {
        this.refPoint.SetX(x);
        this.refPoint.SetY(y);
    }

    public void SetColor(MyColor color) {
        this.color = color;
        this.refPoint.SetColor(color);
    }

    //get methods
    public MyPoint GetRefPoint() {
        return refPoint;
    }

    public MyColor GetColor() {
        return color;
    }
    
    //signature method to return the perimeter of a shape
    public abstract double GetPerimeter();

    //signature method to return the area of a shape
    public abstract double GetArea();

    //signature method to draw a shape; requires a graphics context
    public abstract void Draw(GraphicsContext GC);

    //to string method
    @Override
    public String toString() {
        return "My Abstract Shape";
    }
}

