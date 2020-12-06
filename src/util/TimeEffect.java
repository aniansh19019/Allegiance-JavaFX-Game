package util;

import javafx.scene.image.Image;

public class TimeEffect extends AnimatedEffect
{
    @Override
    public void loadFrames()
    {
        String fileBasePath = "file:res/img/effects/time_effect/time_effect_";
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

    public TimeEffect(Vector position)
    {
        loadFrames();
        setImageScaleFactor(1);
        setPosition(position);
        setLoopFrames(false);
        setAnimationSpeed(0.3);
    }
}
