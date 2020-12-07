package animations;

import javafx.scene.image.Image;
import util.Vector;

public class YellowExplosionEffect extends AnimatedEffect
{
    @Override
    public void loadFrames()
    {
        String fileBasePath = "file:res/img/effects/YellowExplosion/explosion_yellow_";
        for(int i = 29; i<=84; i++)
        {
            String filePath = fileBasePath;
            filePath= filePath + i + ".png";

            //load image

            getFrames().add(new Image(filePath));
        }
        setImage(getFrames().get(0));
    }

    public YellowExplosionEffect(Vector position)
    {
        loadFrames();
        setImageScaleFactor(1.3);
        setPosition(position);
        setLoopFrames(false);
        setAnimationSpeed(1);
    }
}
