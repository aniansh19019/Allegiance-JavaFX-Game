package game_object.powerups;

import animations.ColorSwitchEffect;
import game_object.powerups.PowerUp;

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
