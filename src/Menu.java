import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import widget.MenuItem;

import javax.swing.*;


public class Menu
{
    private Stage mainStage;
    private Scene scene;
    private GlobalConfig config;
    private SinglePlayerGame game;

    public Menu(Stage mainStage)
    {
        this.mainStage = mainStage;
        //init config
        config = new GlobalConfig();


        //init sample menu
        BorderPane pane = new BorderPane();

        Button button = new Button("Play Game!");
        pane.setCenter(button);
        scene = new Scene(pane);
        button.setOnAction(new StartGameEventHandler());
        //menu over, game started
    }

    public Scene getScene()
    {
        return scene;
    }

    class StartGameEventHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent actionEvent)
        {
            game = new SinglePlayerGame(mainStage);
            mainStage.setScene(game.getScene());
        }
    }
}
