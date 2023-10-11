package org.openjfx.Shapes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;

public interface MyShapeInterface {

   //returns the bounding rectangle of a shape as a MyRectangle object
   public abstract MyRectangle GetMyBoundingRectangle();

   //draws the bounding rectangle
   public abstract void DrawBoundingRectangle(GraphicsContext GC);

   //returns true if a point is in a shape, otherwise false
   public abstract boolean PointInMyShape(MyPoint comparisonPoint);

   //compares two objects and returns true if same object with same dimensions
   public static boolean SimilarObject(MyShape s1, MyShape s2) {

       if (s1.getClass().equals(s2.getClass())) {

           //comparing rectangles
           if (s1.getClass().getName().equalsIgnoreCase("org.openjfx.MyRectangle")) {

               MyRectangle r1 = (MyRectangle) s1;
               MyRectangle r2 = (MyRectangle) s2;

               if ((r1.GetWidth() == r2.GetWidth()) && (r1.GetHeight() == r2.GetHeight())) {

                   return true;
               } else {

                   return false;
               }

           //comparing ovals
           } else if (s1.getClass().getName().equalsIgnoreCase("org.openjfx.MyOval")) {

               MyOval o1 = (MyOval) s1;
               MyOval o2 = (MyOval) s2;

               if ((o1.GetMajorAxis() == o2.GetMajorAxis()) && (o1.GetMinorAxis() == o2.GetMinorAxis())) {

                   return true;
               } else {
                   
                   return false;
               }

           //comparing circles
           } else if(s1.getClass().getName().equalsIgnoreCase("org.openjfx.MyCircle")) {
               
               MyCircle c1 = (MyCircle) s1;
               MyCircle c2 = (MyCircle) s2;

               if (c1.GetRadius() == c2.GetRadius()) {

                   return true;
               } else {

                   return false;
               }

           //comparing arc
           } else if(s1.getClass().getName().equalsIgnoreCase("org.openjfx.MyArc")) {
               MyArc a1 = (MyArc) s1;
               MyArc a2 = (MyArc) s2;

               if (a1.GetRadius() == a2.GetRadius() && a1.GetStartingAngle() == a2.GetStartingAngle() && a1.GetArcExtent() == a2.GetArcExtent()) {
                   
                   return true;
               } else {
                   return false;
               }
           } else {

               return false;
           }
       } else {

           return false;
       }
   }

   //returns a rectangle of intersection between two rectangles; if they dont overlap, return null
   public static MyRectangle IntersectMyRectangles(MyRectangle r1, MyRectangle r2) {
       
       MyRectangle intersection = new MyRectangle();

       //get the main x, y, width, height values for rect 1
       int x1 = (int) r1.GetRefPoint().GetX();
       int y1 = (int) r1.GetRefPoint().GetY();
       int w1 = (int) r1.GetWidth();
       int h1 = (int) r1.GetHeight();

       //get the main x, y, width, height values for rect 2
       int x2 = (int) r2.GetRefPoint().GetX();
       int y2 = (int) r2.GetRefPoint().GetY();
       int w2 = (int) r2.GetWidth();
       int h2 = (int) r2.GetHeight();

       //check if the ract is overlapping within the y variable
       if (((y2 + h1) < y2) || (y1 > (y2 + h2))) {
           return null;
       }

       //check if the rect is overlapping within the x variable
       if (((x1 + h1) < x2) || (x1 > (x2 + h2)))  {
           return null;
       }
       
       //find the max and mins between each pair
       int xMax = Math.max(x1, x2);
       int yMax = Math.max(y1, y2);
       int xMin = Math.min(x1 + w1, x2 + w2);
       int yMin = Math.min(y1 + h1, y2 + h2);

       intersection.SetRefPoint(xMax, yMax);
       intersection.SetWidth(Math.abs(xMin - xMax));
       intersection.SetHeight(Math.abs(yMin - yMax));

       return intersection;
   }

   //returns an array of points where every point is in the intersection of two shapes; if thet dont overlap, return null
   public static MyPoint[] IntersectMyShapes(MyShape s1, MyShape s2) {

       MyRectangle intersection = IntersectMyRectangles(s1.GetMyBoundingRectangle(), s2.GetMyBoundingRectangle());
       MyPoint tempPoint = new MyPoint(intersection.GetRefPoint().GetX(), intersection.GetRefPoint().GetY());

       MyPoint[] points = new MyPoint[(int) (intersection.GetArea() + intersection.GetPerimeter())];

       int counter = 0;    //keeps track of how many points there are in the array that aren't null
       
       while ((tempPoint.GetX() <= intersection.GetRefPoint().GetX() + intersection.GetWidth()) && (tempPoint.GetY() <= intersection.GetRefPoint().GetY() + intersection.GetHeight())) {

           if (s1.PointInMyShape(tempPoint) && s2.PointInMyShape(tempPoint)) {

               points[counter] = new MyPoint(tempPoint.GetX(), tempPoint.GetY());
               counter++;
           }

           if (tempPoint.GetX() == (intersection.GetRefPoint().GetX() + intersection.GetWidth())) {

               tempPoint.ShiftPoint(-intersection.GetWidth(), 1);
           } else {

               tempPoint.ShiftXValue(1);
           }
       }

       return points;
   }

   //default draw method; returns a canvas with the intersection of two shapes
   default Canvas DrawIntersectMyShapes(MyShape s1, MyShape s2, double width, double height) {

       Canvas canvas = new Canvas(width, height);
       GraphicsContext GC = canvas.getGraphicsContext2D();
       
       s1.Draw(GC);
       s2.Draw(GC);

       MyPoint[] intersection = IntersectMyShapes(s1, s2);

       for (int i = 0; i < intersection.length; i++) {
           if (intersection[i] != null) {
               intersection[i].SetColor(MyColor.GetRandomColor());
               intersection[i].Draw(GC);
           }
       }

       return canvas;
   }
}

