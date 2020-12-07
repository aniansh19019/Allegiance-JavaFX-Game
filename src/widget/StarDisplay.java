package widget;

import game_object.powerups.StarPowerUp;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import main.GlobalConfig;
import util.Drawable;

public class StarDisplay implements Drawable
{
    private int stars;
    private static GlobalConfig config;
    private StarPowerUp starRenderer;

    static
    {
        config = new GlobalConfig();
    }

    public StarDisplay()
    {
        stars = 0;
        starRenderer = new StarPowerUp(config.getSCREEN_HEIGHT() - 40);
        starRenderer.setActive(false);
        starRenderer.getPosition().setX(config.getSCREEN_WIDTH() - 40);
    }

    public void update(int stars)
    {
        this.stars=stars;
    }

    @Override
    public void render(GraphicsContext context)
    {
        starRenderer.render(context);
        context.setFont(Font.loadFont(config.getPRIMARY_FONT(), 25));
        context.setFill(Paint.valueOf(config.getREGULAR_FONT_COLOR()));
        context.setTextAlign(TextAlignment.RIGHT);
        context.fillText(""+(int)stars, config.getSCREEN_WIDTH() - 70, config.getSCREEN_HEIGHT() - 30 );
    }
}
