package main;

import game_object.PlayerShip;
import game_object.obstacles.RotatingArmsObstacle;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import util.CollisionRectangle;
import util.Sprite;
import util.TestCollisionPolygon;

import java.util.ArrayList;

//TODO pause button
// TODO this is for testing right now

public class TestStuff
{
    private Scene scene;
    private Stage mainStage; //reference to the main stage
    private GlobalConfig config;
    private GameAnimationTimer gameLoop;
    private Canvas gameCanvas;
    private StackPane root;
    private GraphicsContext context;
    private PlayerShip ship;
    private ArrayList<String> inputList;
    private Sprite testSprite;
    private RotatingArmsObstacle testObstacle;
    private TestCollisionPolygon polyOne;
    private TestCollisionPolygon polyTwo;

    //user info



    public TestStuff(Stage maineStage)
    {
        //init vars
        inputList = new ArrayList<String>();
        //init config
        this.config = new GlobalConfig();
        //init stage
        this.mainStage = maineStage;
        //init gameLoop
        this.gameLoop = new GameAnimationTimer();
        //init canvas
        this.gameCanvas = new Canvas(config.getSCREEN_WIDTH(), config.getSCREEN_HEIGHT());
        //init context
        this.context = gameCanvas.getGraphicsContext2D();
        //init root node
        this.root = new StackPane();
        root.getChildren().add(gameCanvas);
        //init scene
        scene = new Scene(root);
        //init test
        //////////////////////////////////////////////////////////////////////////////////////////
        polyOne = new TestCollisionPolygon(200,200,50,50);
        polyTwo = new TestCollisionPolygon(290, 200, 50, 50);

        polyOne.rotate(45);
        polyTwo.rotate(60);

        polyOne.render(context);
        polyTwo.render(context);

        System.out.println(polyOne.didCollide(polyTwo));



        gameLoop.start();


    }

    private void pauseGame()
    {

    }

    public Scene getScene()
    {
        return scene;
    }

    private void gameOver()
    {
//        context.

    }

    private class GameAnimationTimer extends AnimationTimer
    {
        private boolean shiftingWindow;
        private final double windowShiftDelta;
        private final double lineOfControl;
        private CollisionRectangle screenRectangle;

        public GameAnimationTimer()
        {
            super();
            shiftingWindow=false;
            lineOfControl = config.getSCREEN_HEIGHT()*0.4;
            windowShiftDelta = 1.5;
            screenRectangle = new CollisionRectangle(0,0,config.getSCREEN_WIDTH(), config.getSCREEN_HEIGHT());
        }

        @Override
        public void handle(long l)
        {
//            context.setFill(Color.web(config.getBACKGROUND_COLOR()));
//            context.fillRect(0, 0, config.getSCREEN_WIDTH(), 3*config.getSCREEN_HEIGHT());//replace with background
//            polyOne.render(context);
//            polyTwo.render(context);

        }

    }
}
