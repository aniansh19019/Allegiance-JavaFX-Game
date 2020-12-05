package util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GlobalConfig;

//TODO sprite implements an interface drawable which obstacle also implements

public class Sprite implements Drawable
{
    private boolean localShowBounds;
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private Image image;
    private boolean isVisible;
    private boolean isMovable;
    private boolean isInFrame;
    private CollisionRectangle boundary;
    private double imageScaleFactor;
    private GameColor color;
    public final static GlobalConfig config;
    private final static double screenOffset = 50;

    static
    {
        config = new GlobalConfig();
    }



    //TODO implement standard animation routines


    //getters and setters


    public boolean isLocalShowBounds()
    {
        return localShowBounds;
    }

    public void setLocalShowBounds(boolean localShowBounds)
    {
        this.localShowBounds = localShowBounds;
    }


    public boolean isInFrame()
    {
        return isInFrame;
    }

    public void setInFrame(boolean inFrame)
    {
        isInFrame = inFrame;
    }

    public boolean isVisible()
    {
        return isVisible;
    }

    public void setVisible(boolean visible)
    {
        isVisible = visible;
    }

    public boolean isMovable()
    {
        return isMovable;
    }

    public void setMovable(boolean movable)
    {
        isMovable = movable;
    }

    public Vector getPosition()
    {
        return position;
    }

    public void setPosition(Vector position)
    {
        setPosition(position.getX(), position.getY());
    }

    public Vector getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Vector velocity)
    {
        setVelocity(velocity.getX(), velocity.getY());
    }

    public Vector getAcceleration()
    {
        return acceleration;
    }

    public void setAcceleration(Vector acceleration)
    {
        setAcceleration(acceleration.getX(), acceleration.getY());
    }

    public void shiftPosition(Vector delta) // for shifting everything down
    {
        position.subtract(delta);
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image) // use for replacing a sprite's image along with the collision boundary
    {
        this.image = image;
        updateBoundary();
    }

    public void setImage(String file)
    {
        image = new Image(file);
        updateBoundary();
    }

    public void switchImage(Image image) // use to change image without changing collision boundary for rocket effects etc.
    {
        this.image = image;
    }

    public void switchImage(String file)
    {
        image = new Image(file);
        switchImage(image);
    }

    public double getImageScaleFactor()
    {
        return imageScaleFactor;
    }

    public void setBoundary(CollisionRectangle boundary)
    {
        this.boundary = boundary;
    }

    public GameColor getColor()
    {
        return color;
    }

    public void setColor(GameColor color)
    {
        this.color = color;
    }

    private void updateBoundary()
    {
        boundary.setW(image.getWidth()*imageScaleFactor);
        boundary.setH(image.getHeight()*imageScaleFactor);
    }

    public Sprite()
    {
        position =  new Vector(0,0);
        velocity = new Vector(0,0);
        acceleration = new Vector(0,0);
        boundary = new CollisionRectangle(0,0,0,0);
        imageScaleFactor = 1;
        isMovable = true;
        isVisible = true;
        isInFrame = true;
        color = GameColor.NONE;
        localShowBounds=true;
    }

    public CollisionRectangle getBoundary()
    {
        boundary.setPos(position.getX()- boundary.getW() / 2, position.getY()- boundary.getH() / 2);
        return boundary;
    }


    public void update()
    {
        if(isMovable)
        {
            velocity.add(acceleration);
            position.add(velocity);
        }
        else
        {
            acceleration.set(0,0);
            velocity.set(0,0);
        }
        if(getPosition().getY() > config.getSCREEN_HEIGHT()+screenOffset ||
                getPosition().getY() < -screenOffset ||
                getPosition().getX() > config.getSCREEN_HEIGHT()+screenOffset ||
                getPosition().getX() < -screenOffset)
        {
            //outside the frame
            isInFrame = false;
        }
        else
        {
            isInFrame = true;
        }
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
        updateBoundary();
    }

    public boolean didCollide(Sprite other)
    {
        return getBoundary().didCollide(other.getBoundary());
    }

    public void render(GraphicsContext context)
    {
        // display the center of the image at the position vector
        if (isVisible)
        {
            context.drawImage(image, position.getX() - boundary.getW() / 2, position.getY() - boundary.getH() / 2, image.getWidth() * imageScaleFactor, image.getHeight() * imageScaleFactor);
        }

        if(config.getSHOW_COLLISION_BOUNDS() && isLocalShowBounds())
        {
            this.getBoundary().render(context);
        }

    }

    @Override
    public String toString()
    {
        return "Sprite{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", acceleration=" + acceleration +
                ", isVisible=" + isVisible +
                ", isMovable=" + isMovable +
                ", color=" + color +
                '}';
    }
}
