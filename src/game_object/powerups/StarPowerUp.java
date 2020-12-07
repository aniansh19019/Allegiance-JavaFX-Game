package game_object.powerups;

import animations.YellowExplosionEffect;

public class StarPowerUp extends PowerUp
{

    public StarPowerUp(double y)
    {
        super(y);
        setImage("file:res/img/powerups/star.png");
        setImageScaleFactor(0.3);
    }

    @Override
    public void destroy()
    {
        super.destroy();
        addEffect(new YellowExplosionEffect(getPosition()));
    }
}
