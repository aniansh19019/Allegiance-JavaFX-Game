package util;

import javafx.scene.image.Image;

public class ExplosionEffect extends AnimatedEffect
{

    public ExplosionEffect(Vector position)
    {
        loadFrames();
        setImageScaleFactor(1.5);
        setPosition(position);
        setLoopFrames(false);
        setAnimationSpeed(0.3);
    }

    @Override
    public void loadFrames()
    {
        String fileBasePath = "file:res/img/effects/Explosion4/00";
        for(int i = 1; i<=25; i++)
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
