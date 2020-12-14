package main;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.menu.main_menu.MainMenu;

import java.io.Serializable;
import java.util.Hashtable;

//TODO serializable transient and stuff

public class Game extends Application implements Serializable
{
//    private transient main.SinglePlayerGame game;
    private transient MainMenu menu;

    private GlobalConfig config;

    private boolean soundOn;

    private KeyCode PlayerOneMoveKey;
    private KeyCode PlayerOneFireKey;
    private KeyCode PlayerOnePowerUpKey;

    private KeyCode PlayerTwoMoveKey;
    private KeyCode PlayerTwoFireKey;
    private KeyCode PlayerTwoPowerUpKey;

    private Hashtable<String, Integer> leaderBoards;

    @Override
    public void init() throws Exception
    {
        super.init();
        config = new GlobalConfig();


    }

    @Override
    public void start(Stage stage) throws Exception
    {
        // init menu
        menu = new MainMenu(stage);
        //stage setup

        stage.setHeight(config.getSCREEN_HEIGHT());
        stage.setWidth(config.getSCREEN_WIDTH());
        stage.setResizable(false);
        stage.setTitle("Allegiance - A Unique take on Color Switch!");
        stage.setX(200);
        stage.setY(200);
        stage.initStyle(StageStyle.UTILITY);


        //test stuff

//        TestStuff stuff = new TestStuff(stage);
//        stage.setScene(stuff.getScene());


        //go to menu

        stage.setScene(menu.getMainMenu());

        // for now go to game



        stage.show();
    }

    @Override
    public void stop() throws Exception
    {
        super.stop();
    }
}
