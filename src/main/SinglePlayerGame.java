package main;

import game_object.*;
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
import util.GameColor;
import util.Sprite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

//TODO pause button
// TODO this is for testing right now
// TODO store all references in Game SinglePLayerGame Menu
public class SinglePlayerGame implements Serializable
{
    private static final int NUM_SCREENS = 3;


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
    private Obstacle[] obstacles; // to store all obstacles
    private PowerUp[] powerups; // to store all powerups/collectibles
    private Random rand;

    //user info



    public SinglePlayerGame(Stage maineStage)
    {
        //init utils
        rand = new Random();
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
        //TODO select ship from menu
        ship = new PlayerShip(GameColor.ALL, 2);

        //init obstacles
        initObstacles();

        //init powerups
        initPowerups();

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

    private void initPowerups()
    {
        //TODO change this to be random
        //randomly with low chance spawn other powerups
        powerups = new PowerUp[NUM_SCREENS];

        for(int i = 0; i< NUM_SCREENS; i++)
        {
            powerups[i] = new ColorSwitcher(-(i*config.getSCREEN_HEIGHT() + config.getSCREEN_HEIGHT()/2));
        }
    }

    private void initObstacles()
    {
        //TODO change this to be random
        obstacles = new Obstacle[NUM_SCREENS];

        for(int i = 0; i< NUM_SCREENS; i++)
        {
            obstacles[i] = new RotatingArmsObstacle();
            if(i%2==0)
            {
                obstacles[i].setPosition(30, -i*config.getSCREEN_HEIGHT());
            }
            else
            {
                obstacles[i].setPosition(config.getSCREEN_WIDTH()-30, -i*config.getSCREEN_HEIGHT());
            }
        }


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
        private int obstacleCount = NUM_SCREENS;
        private boolean shiftingWindow;
        private final double windowShiftDelta;
        private final double lineOfControl;
        private CollisionRectangle screenRectangle;
        private long firedTime=0;
        private final long fireDelay = 1000000000;

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
            //erase stuff
            context.setFill(Color.web(config.getBACKGROUND_COLOR()));
            context.fillRect(0, 0, config.getSCREEN_WIDTH(), 3*config.getSCREEN_HEIGHT());//replace with background


            if(shiftingWindow)  //shift everything
            {
                if(ship.getPosition().getY()<lineOfControl && ship.getVelocity().getY()<=0) // bring everything down in the frame
                {
                    //shift ship and bullets
                    ship.shiftPosition(ship.getVelocity());

                    //shift obstacles and powerups
                    for(int i = 0; i< NUM_SCREENS; i++)
                    {
                        obstacles[i].shiftPosition(ship.getVelocity()); // shift obstacles
                        powerups[i].shiftPosition(ship.getVelocity()); // shift powerups
                    }

                    //shift powerups

                }
                else // turn off shifting
                {
                    shiftingWindow=false;
                }
            }
             //draw stuff

            drawObstacles();
            drawPowerUps();
            drawShip(l);

            //check for collisions
            checkCollisions();

        }
//TODO move update inside render
        private void drawObstacles()
        {
            for(int i = 0; i< NUM_SCREENS; i++)
            {
                obstacles[i].update();
                obstacles[i].render(context);
            }

            //check if obstacle 0 is in frame

            if(!obstacles[0].isInFrame())
            {
                spawnNewObstacle();
                System.out.println("Spawning new Obstacle!");
            }
        }

        void spawnNewObstacle()
        {
            //shift everything in array
            for(int i = 0; i< NUM_SCREENS -1; i++)
            {
                obstacles[i] = obstacles[i+1];
//                System.out.println(obstacles[i]);
            }
            //create new obstacle
            //TODO randomise this


            obstacles[NUM_SCREENS -1] = new RotatingArmsObstacle();

            if(obstacleCount%2==0)
            {
                obstacles[NUM_SCREENS -1].setPosition(30, -(NUM_SCREENS -1)*config.getSCREEN_HEIGHT());
            }
            else
            {
                obstacles[NUM_SCREENS -1].setPosition(config.getSCREEN_WIDTH()-30, -(NUM_SCREENS -1)*config.getSCREEN_HEIGHT());
            }
//            obstacles[NUM_OBSTACLES-1].setPosition(20,-(NUM_OBSTACLES-1)*config.getSCREEN_HEIGHT());
            obstacleCount++;
        }

        private void drawScore()
        {

        }

        private void drawPowerUps()
        {
            for(int i = 0; i< NUM_SCREENS; i++)
            {
                powerups[i].update();
                powerups[i].render(context);
            }

            //check if obstacle 0 is in frame

            if(!powerups[0].isInFrame())
            {
                spawnNewPowerup();
                System.out.println("Spawning new Powerup!");
            }
        }

        private void spawnNewPowerup()
        {
            //shift everything in array
            for(int i = 0; i< NUM_SCREENS -1; i++)
            {
                powerups[i] = powerups[i+1];
//                System.out.println(powerups[i]);
            }
            //create new obstacle
            //TODO randomise this


            powerups[NUM_SCREENS -1] = new ColorSwitcher(-((NUM_SCREENS-1)*config.getSCREEN_HEIGHT() + config.getSCREEN_HEIGHT()/2));
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

        private void drawShip(long l)
        {

            if(inputList.contains("SPACE") && !ship.isBoosted())// if not already boosted, call boost
            {
                ship.boost();

            }
            else if(!inputList.contains("SPACE") && ship.isBoosted())
            {
                ship.unBoost();
            }

            if(inputList.contains("X") && l-firedTime > fireDelay)
            {
                ship.fire();
                firedTime=l;
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

        private void checkCollisions()
        {

        }
    }
}
