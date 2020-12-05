package game_object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import util.Sprite;

public abstract class PowerUp extends Sprite
{
    private double angle;
    private double angleDelta = 2;

    public PowerUp(double y)
    {
        angle=0;
        setLocalShowBounds(false);
        setPosition(config.getSCREEN_WIDTH()/2, y);
    }

    @Override
    public boolean isInFrame()
    {
        if(getPosition().getY()>config.getSCREEN_HEIGHT())
        {
            return false;
        }
        return true;
    }

    @Override
    public void update()
    {
        super.update();
        angle = (angle+angleDelta)%360;
    }


    @Override
    public void render(GraphicsContext context)
    {
        Rotate r = new Rotate(angle, getPosition().getX(), getPosition().getY());
        context.save();
        context.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        super.render(context);
        context.restore();

        if(config.getSHOW_COLLISION_BOUNDS())
        {
            getBoundary().render(context);
        }
    }
}


