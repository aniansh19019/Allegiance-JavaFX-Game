package util;

import javafx.scene.image.Image;

public class ReviveEffect extends AnimatedEffect
{
    @Override
    public void loadFrames()
    {
        String fileBasePath = "file:res/img/effects/revive_animation/Slice_";
        for(int i = 1; i<=20; i++)
        {
            String filePath = fileBasePath;
            filePath= filePath + i + ".png";

            //load image

            getFrames().add(new Image(filePath));
        }
        setImage(getFrames().get(0));
    }

    public ReviveEffect(Vector position)
    {
        loadFrames();
        setImageScaleFactor(1.3);
        setPosition(position);
        setLoopFrames(false);
        setAnimationSpeed(0.6);
    }
}
