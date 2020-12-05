package main;

public class GlobalConfig
{
    final private boolean SHOW_COLLISION_BOUNDS = true;
    final private int SCREEN_WIDTH = 500;
    final private int SCREEN_HEIGHT = 700;

    final private String PRIMARY_FONT = "file:res/font/kenvector_future_thin.ttf";

    final private String REGULAR_FONT_COLOR = "CCCCCC";
    final private String UI_PRIMARY_COLOR = "FFFFFF";
    final private String UI_ACCENT_COLOR = "999999";
    final private String BACKGROUND_COLOR = "123456";

    public String getPRIMARY_FONT()
    {
        return PRIMARY_FONT;
    }

    public boolean getSHOW_COLLISION_BOUNDS()
    {
        return SHOW_COLLISION_BOUNDS;
    }

    public int getSCREEN_WIDTH()
    {
        return SCREEN_WIDTH;
    }

    public int getSCREEN_HEIGHT()
    {
        return SCREEN_HEIGHT;
    }

    public String getREGULAR_FONT_COLOR()
    {
        return REGULAR_FONT_COLOR;
    }

    public String getUI_PRIMARY_COLOR()
    {
        return UI_PRIMARY_COLOR;
    }

    public String getUI_ACCENT_COLOR()
    {
        return UI_ACCENT_COLOR;
    }

    public String getBACKGROUND_COLOR()
    {
        return BACKGROUND_COLOR;
    }
}
