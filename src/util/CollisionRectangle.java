package util;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

import java.util.List;

public class CollisionRectangle implements Drawable
{
    private double x;
    private double y;
    private double w;
    private double h;

    private double angle;
    private double pivotX;
    private double pivotY;

    private Polygon poly;

    public double getAngle()
    {
        return angle;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    public double getPivotX()
    {
        return pivotX;
    }

    public void setPivotX(double pivotX)
    {
        this.pivotX = pivotX;
    }

    public double getPivotY()
    {
        return pivotY;
    }

    public void setPivotY(double pivotY)
    {
        this.pivotY = pivotY;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
        updatePoly();
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
        updatePoly();
    }


    //! only to be used while the initial scaling! Not afterwards!
    public double getW()
    {
        return w;
    }

    public void setW(double w)
    {
        this.w = w;
        updatePoly();
    }

    public double getH()
    {
        return h;
    }

    public void setH(double h)
    {
        this.h = h;
        updatePoly();
    }

    public void setPos(double x, double y)
    {
        this.x = x;
        this.y = y;
        updatePoly();
    }

    public void updatePoly()
    {
        this.poly = new Polygon();
        this.poly.getPoints().addAll(new Double[]{
                x, y,
                x+w, y,
                x+w, y+h,
                x, y+h
        });
    }

    public CollisionRectangle(double x, double y, double w, double h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        updatePoly();
        this.angle=0;
        this.pivotX=x;
        this.pivotY=y;
    }


    public Polygon rotate()
    {
        Rotate r = new Rotate(angle,pivotX,pivotY); // rotate transform with the specified angle

        double[] points = new double[poly.getPoints().size()]; // priitive points
        Double[] newPoints = new Double[poly.getPoints().size()]; // wrapper points

        for(int i=0; i<poly.getPoints().size();i++)
        {
            points[i] = poly.getPoints().get(i);
        }

        r.transform2DPoints(points, 0, points, 0, poly.getPoints().size()/2); // apply transform

        for(int i=0; i<poly.getPoints().size();i++)
        {
            newPoints[i] = points[i];
        }

        Polygon retval = new Polygon();

        retval.getPoints().addAll(newPoints); // add new points
        return retval;
    }


    public boolean didCollide(CollisionRectangle other)
    {
        //if any of the lines intersect with each other, the polygons have collided
        //TODO include the case when one rectangle contains the other

        List<Double> myList = this.rotate().getPoints();
        List<Double> otherList = other.rotate().getPoints();

        LineSegment[] myLines = new LineSegment[myList.size()/2];
        LineSegment[] otherLines = new LineSegment[otherList.size()/2];

        for(int i=0; i< myLines.length; i++) // make mylines
        {
            double ax = myList.get(2*i);
            double ay = myList.get((2*i)+1);
            double bx = myList.get( 2*( (i+1)%myLines.length ));
            double by = myList.get( 2*((i+1)%myLines.length)+1);
            myLines[i] = new LineSegment(ax, ay, bx, by);
//            System.out.println("Mine: "+myLines[i]);
        }


        for(int i=0; i< otherLines.length; i++) // make otherLines
        {
            double ax = otherList.get(2*i);
            double ay = otherList.get((2*i)+1);
            double bx = otherList.get( 2*( (i+1)%(otherLines.length/2) ));
            double by = otherList.get( 2*((i+1)%(otherLines.length/2))+1);
            otherLines[i] = new LineSegment(ax, ay, bx, by);
//            System.out.println("Other: " + otherLines[i]);
        }

        for(int i=0; i< myLines.length; i++) // check if any lines intersect
        {
            for(int j = 0; j< otherLines.length; j++)
            {
                if(myLines[i].intersect(otherLines[j]))
                {
//                    System.out.println(myLines[i]+" with " + otherLines[j]);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void render(GraphicsContext context)
    {
        double[] pointsX = new double[poly.getPoints().size()/2];
        double[] pointsY = new double[poly.getPoints().size()/2];
        Object[] allPoints = rotate().getPoints().toArray();


        for(int i=0; i<poly.getPoints().size(); i++)
        {
            if(i%2==0)
            {
                pointsX[i/2] = (double)allPoints[i];
            }
            else
            {
                pointsY[i/2] = (double)allPoints[i];
            }
        }
        context.setStroke(Paint.valueOf("ffffff"));
        context.strokePolygon(pointsX, pointsY, pointsX.length);


    }

//    public boolean didCollide(CollisionRectangle other)
//    {
//        boolean didNotCollide =
//                this.x + this.w < other.x ||
//                        other.x + other.w <this.x ||
//                        this.y + this.h < other.y ||
//                        other.y + other.h < this.y;
//        return !didNotCollide;
//    }


}
