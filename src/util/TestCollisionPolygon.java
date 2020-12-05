package util;

import javafx.geometry.Point3D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

import java.util.List;

public class TestCollisionPolygon implements Drawable
{
    private Polygon poly;
    private double x, y, w, h;

    public TestCollisionPolygon(double x, double y, double w, double h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.poly = new Polygon();
        this.poly.getPoints().addAll(new Double[]{
                x, y,
                x+w, y,
                x+w, y+h,
                x, y+h
        });
    }


    public Polygon rotate(double angle)
    {
        Rotate r = new Rotate(angle,x,y); // rotate transform with the specified angle

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

    public boolean didCollide(TestCollisionPolygon other)
    {
        //if any of the lines intersect with each other, the polygons have collided
        //TODO include the case when one rectangle contains the other

        List<Double> myList = this.poly.getPoints();
        List<Double> otherList = other.poly.getPoints();

        LineSegment[] myLines = new LineSegment[myList.size()/2];
        LineSegment[] otherLines = new LineSegment[otherList.size()/2];

        for(int i=0; i< myLines.length; i++) // make mylines
        {
            double ax = myList.get(2*i);
            double ay = myList.get((2*i)+1);
            double bx = myList.get( 2*( (i+1)%myLines.length ));
            double by = myList.get( 2*((i+1)%myLines.length)+1);
            myLines[i] = new LineSegment(ax, ay, bx, by);
            System.out.println("Mine: "+myLines[i]);
        }


        for(int i=0; i< otherLines.length; i++) // make otherLines
        {
            double ax = otherList.get(2*i);
            double ay = otherList.get((2*i)+1);
            double bx = otherList.get( 2*( (i+1)%(otherLines.length/2) ));
            double by = otherList.get( 2*((i+1)%(otherLines.length/2))+1);
            otherLines[i] = new LineSegment(ax, ay, bx, by);
            System.out.println("Other: " + otherLines[i]);
        }

        for(int i=0; i< myLines.length; i++) // check if any lines intersect
        {
            for(int j = 0; j< otherLines.length; j++)
            {
                if(myLines[i].intersect(otherLines[j]))
                {
                    System.out.println(myLines[i]+" with " + otherLines[j]);
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
        Object[] allPoints = poly.getPoints().toArray();


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

        context.fillPolygon(pointsX, pointsY, pointsX.length);


    }


}
