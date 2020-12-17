package main.menu.main_menu;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import main.GlobalConfig;

import java.io.File;

public class SplashScreen
{
    static private final GlobalConfig config;
    private final Scene scene;
    private final MenuManager menuManager;

    static
    {
        config = new GlobalConfig();
    }

    public SplashScreen(MenuManager menu)
    {
        menuManager = menu;

        //init borderpane
        StackPane pane = new StackPane();
        scene = new Scene(pane, config.getSCREEN_WIDTH(), config.getSCREEN_HEIGHT());
        //set cursor
//        Image cursorImage = new Image("file:res/img/ui_elements/cursor_arrow.png");
//        ImageCursor cursor = new ImageCursor(cursorImage);
//        this.scene.setCursor(cursor);


        //init video
        File videoFile = new File("res/video/splash_screen_trimmed.mp4");
        Media media = new Media(videoFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
//        mediaView.setX(0);
//        mediaView.setY(0);



        //set center
        pane.getChildren().add(mediaView);
//        System.out.println("Scene Set!");

        //space to skip
        scene.setOnKeyPressed(e->{
            if(e.getCode().isWhitespaceKey())
            {
                mediaPlayer.stop();
                menuManager.getWindow().setScene(menuManager.getRoot());//set scene to menu
                MenuManager.getMenuBackgroundMusic().play();
            }
        });

        mediaPlayer.setOnEndOfMedia(new StartMenuAfterSplashScreen());

    }

    public Scene getScene()
    {
//        System.out.println("Scene returned!");
        return scene;
    }


    class StartMenuAfterSplashScreen implements Runnable
    {
        @Override
        public void run()
        {
            menuManager.getWindow().setScene(menuManager.getRoot());
            MenuManager.getMenuBackgroundMusic().play();
        }
    }


}
