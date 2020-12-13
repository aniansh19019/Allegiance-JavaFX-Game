package assignments;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite
{
    private double screenWidth;
    private double screenHeight;
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private Image image;
    private double imageScaleFactor;
    private RectangleCollisionPolygon boundary;

    public double getScreenWidth()
    {
        return screenWidth;
    }

    public void setScreenWidth(double screenWidth)
    {
        this.screenWidth = screenWidth;
    }

    public double getScreenHeight()
    {
        return screenHeight;
    }

    public void setScreenHeight(double screenHeight)
    {
        this.screenHeight = screenHeight;
    }

    public Vector getPosition()
    {
        return position;
    }

    public Vector getScaledPosition()
    {
        Vector retval =  new Vector(position);
        retval.scale(imageScaleFactor);
        return retval;
    }

    public void setPosition(Vector position)
    {
        this.position = position;
    }

    public Vector getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Vector velocity)
    {
        this.velocity = velocity;
    }

    public Vector getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(Vector acceleration)
    {
        this.acceleration = acceleration;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
        boundary.setH(image.getHeight()*imageScaleFactor);
        boundary.setW(image.getWidth()*imageScaleFactor);
        // TODO no boundary change for effects
    }

    public double getImageScaleFactor()
    {
        return imageScaleFactor;
    }

    public void setBoundary(RectangleCollisionPolygon boundary)
    {
        this.boundary = boundary;
    }

    public Sprite()
    {
        position =  new Vector(0,0);
        velocity = new Vector(0,0);
        acceleration = new Vector(0,0);
        boundary = new RectangleCollisionPolygon(0,0,0,0);
        imageScaleFactor = 1;
    }

    public RectangleCollisionPolygon getBoundary()
    {
        boundary.setPos(position.getX(), position.getY());
        return boundary;
    }
    public void setImage(String file)
    {
        image = new Image(file);
        boundary.setW(image.getWidth());
        boundary.setH(image.getHeight());
    }

    public void update()
    {
        velocity.add(acceleration);
        position.add(velocity);
    }

    public void setPosition(double x, double y)
    {
        position.set(x,y);
    }

    public void setVelocity(double x, double y)
    {
        velocity.set(x, y);
    }

    public void setAcceleration(double x, double y)
    {
        acceleration.set(x,y);
    }


    public void setImageScaleFactor(double imageScaleFactor)
    {
        this.imageScaleFactor = imageScaleFactor;
        boundary.setW(image.getWidth()*imageScaleFactor);
        boundary.setH(image.getHeight()*imageScaleFactor);
        // TODO image update scale
    }

    public boolean didCollide(Sprite other)
    {
        return this.boundary.didCollide(other.boundary);
    }

    public void render(GraphicsContext context)
    {
        context.drawImage(image, position.getX() - boundary.getW()/2, position.getY(), image.getWidth()*imageScaleFactor, image.getHeight()*imageScaleFactor);
//        System.out.println(position);
    }
}

