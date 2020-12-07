package animations;

import javafx.scene.image.Image;
import util.Vector;

public class ShipExplosionEffect extends AnimatedEffect
{

    public ShipExplosionEffect(Vector position)
    {
        loadFrames();
        setImageScaleFactor(2);
        setPosition(position);
        setLoopFrames(false);
        setAnimationSpeed(0.6);
    }

    @Override
    public void loadFrames()
    {
        String fileBasePath = "file:res/img/effects/ship_explosion_effect/ship_explode_effect_";
        for(int i = 1; i<=64; i++)
        {
            String filePath = fileBasePath;
            if(i<10)
            {
                filePath+="0";
            }
            filePath= filePath + i + ".png";

            //load image

            getFrames().add(new Image(filePath));
        }
        setImage(getFrames().get(0));
    }
}
