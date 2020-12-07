package animations;

import javafx.scene.image.Image;
import util.Vector;

public class ShipProtectEffect extends AnimatedEffect
{
    @Override
    public void loadFrames()
    {
        String fileBasePath = "file:res/img/effects/ship_protect_effect/ship_protect_effect_";
        for(int i = 1; i<=15; i++)
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

    public ShipProtectEffect(Vector position)
    {
        loadFrames();
        setImageScaleFactor(1.3);
        setPosition(position);
        setLoopFrames(true);
        setAnimationSpeed(0.6);
    }
}
