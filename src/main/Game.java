package main;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.menu.main_menu.MenuManager;
import main.menu.main_menu.SplashScreen;




public class Game extends Application
{

    private GlobalConfig config;


    @Override
    public void init() throws Exception
    {
        super.init();
        config = new GlobalConfig();
    }

    @Override
    public void start(Stage stage)
    {
        // init menu
        //    private transient main.SinglePlayerGame game;
        MenuManager menu = new MenuManager(stage);

        //init splash screen

        SplashScreen splashScreen = new SplashScreen(menu);
        //stage setup

        stage.setHeight(config.getSCREEN_HEIGHT());
        stage.setWidth(config.getSCREEN_WIDTH());
        stage.setResizable(false);
        stage.setTitle("Allegiance - A Unique take on Color Switch!");
//        stage.setX(200);
//        stage.setY(200);
        stage.initStyle(StageStyle.UTILITY);

        //go to menu
        stage.setScene(splashScreen.getScene());




        stage.show();
    }

    @Override
    public void stop() throws Exception
    {
        super.stop();
    }
}
