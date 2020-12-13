package animations;

import javafx.scene.image.Image;
import util.Vector;

public class TimeOverlayEffect extends AnimatedEffect
{
    @Override
    public void loadFrames()
    {
        String fileBasePath = "file:res/img/overlays/time_overlay/time_overlay_";
        for(int i = 1; i<=20; i++)
        {
            String filePath = fileBasePath;
            filePath= filePath + i + ".png";

//            System.out.println(filePath);

            //load image

            getFrames().add(new Image(filePath));
        }
        setImage(getFrames().get(0));
    }

    public TimeOverlayEffect(Vector position)
    {
        loadFrames();
        setImageScaleFactor(1);
        setPosition(config.getSCREEN_WIDTH()/2,config.getSCREEN_HEIGHT()/2);
        setLoopFrames(true);
        setAnimationSpeed(0.5);
    }

    @Override
    public void shiftPosition(Vector delta)
    {
        //nop
    }
}
