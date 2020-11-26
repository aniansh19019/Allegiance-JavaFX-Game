package main;

import game_object.PlayerShip;
import game_object.RotatingArmsObstacle;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import util.CollisionRectangle;
import util.Sprite;

import java.io.Serializable;
import java.util.ArrayList;

//TODO pause button

public class SinglePlayerGame implements Serializable
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

    //user info



    public SinglePlayerGame(Stage maineStage)
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
        //init ship
        ship = new PlayerShip();
        ship.setPosition(config.getSCREEN_WIDTH()/2, 0.5* config.getSCREEN_HEIGHT());
        ship.setNormalImage("file:res/img/ships/ship_1_pink.png");
        ship.setThrustImage("file:res/img/ships/ship_1_pink_fire.png");

        ship.setImageScaleFactor(0.6);

        //init test sprite
        testSprite = new Sprite();
        testSprite.setImage("file:res/img/obstacle.png");
        testSprite.setImageScaleFactor(0.4);
        testSprite.setPosition(config.getSCREEN_WIDTH()/2, config.getSCREEN_HEIGHT()/2);

        //init test obstacle
        testObstacle = new RotatingArmsObstacle();
        testObstacle.setPosition(0,0);

        //init KeyEvents

        scene.setOnKeyPressed((KeyEvent event) ->
        {
            String keyName = event.getCode().toString();
            if(!inputList.contains(keyName))
                inputList.add(keyName);
        });
        scene.setOnKeyReleased((KeyEvent event) ->
        {
            String keyName = event.getCode().toString();
            inputList.remove(keyName);
        });


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

    class GameAnimationTimer extends AnimationTimer
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
            context.setFill(Color.web(config.getBACKGROUND_COLOR()));
            context.fillRect(0, 0, config.getSCREEN_WIDTH(), 3*config.getSCREEN_HEIGHT());//replace with background
            if(shiftingWindow)  //shift everything
            {
                if(ship.getPosition().getY()<lineOfControl) // bring everything down in the frame
                {
                    ship.shiftPosition(ship.getVelocity());
                    testSprite.shiftPosition(ship.getVelocity());
                    testObstacle.shiftPosition(ship.getVelocity());

                }
                else // turn off shifting
                {
                    shiftingWindow=false;
                }
            }
             //draw stuff
            drawShip();
            drawObstacles();
            //drawTestSprite(l);
        }

        private void drawObstacles()
        {
            testObstacle.update();
            testObstacle.render(context);
        }

        private void drawScore()
        {

        }

        private void drawPowerUps()
        {

        }

        private void drawBullets()
        {

        }

        private void drawTestSprite(long l)
        {
            if(testSprite.getPosition().getY()> config.getSCREEN_HEIGHT()*1.2)
            {
                testSprite.setPosition(config.getSCREEN_WIDTH()/2, -0.2*config.getSCREEN_HEIGHT());
            }
            Rotate r = new Rotate(l/10000000, testSprite.getPosition().getX(), testSprite.getPosition().getY());
            context.save();
            context.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
            testSprite.update();
            testSprite.render(context);
            context.restore();
        }

        private void drawShip()
        {
            if(inputList.contains("SPACE"))
            {
                ship.boost();
            }
            else
            {
                ship.unBoost();
                //TODO stop unnecessary calls
            }

            if(inputList.contains("X"))
            {
                ship.fire();
            }

            if(ship.getPosition().getY()<lineOfControl)
            {
                shiftWindow();
            }


            ship.update();
            ship.render(context);
        }
        private void shiftWindow()
        {
            shiftingWindow =true;
        }
    }
}
