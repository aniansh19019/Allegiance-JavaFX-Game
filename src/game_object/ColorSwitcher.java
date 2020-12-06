package game_object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import util.ColorSwitchEffect;
import util.YellowExplosionEffect;

public class ColorSwitcher extends PowerUp
{


    public ColorSwitcher(double y)
    {
        super(y);
        setImage("file:res/img/powerups/color_switcher.png");
        setImageScaleFactor(0.3);
        //TODO better graphics for this
    }

    @Override
    public void destroy()
    {
        super.destroy();
        addEffect(new ColorSwitchEffect(getPosition()));
    }





}
