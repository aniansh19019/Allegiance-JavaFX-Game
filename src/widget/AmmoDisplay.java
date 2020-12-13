package widget;

import game_object.bullets.Bullet;
import javafx.scene.canvas.GraphicsContext;
import main.GlobalConfig;
import util.Drawable;

import java.io.Serializable;
import java.util.ArrayList;

public class AmmoDisplay implements Drawable, Serializable
{
    private ArrayList<Bullet> ammo;
    static GlobalConfig config;
    static
    {
        config = new GlobalConfig();
    }

    public AmmoDisplay(ArrayList<Bullet> ammo)
    {
        this.ammo = ammo;
    }


    @Override
    public void render(GraphicsContext context)
    {
        double xVal = 20;

        for(int i=0; i< ammo.size(); i++)
        {
            ammo.get(i).setPosition(xVal, config.getSCREEN_HEIGHT() - ammo.get(i).getBoundary().getH() - 10);
            ammo.get(i).render(context);
            xVal+=30;
        }
    }
}
