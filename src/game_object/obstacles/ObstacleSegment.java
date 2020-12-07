package game_object.obstacles;

import javafx.scene.canvas.GraphicsContext;
import util.Sprite;

public class ObstacleSegment extends Sprite
{
    public ObstacleSegment()
    {
        super();
        setLocalShowBounds(false);
        setRenderEffects(false);
    }

//    @Override
//    public void render(GraphicsContext context)
//    {
//
//        super.render(context);
//        this.getBoundary().render(context);
//    }
}

