package main;

import game_object.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import util.*;
import widget.AmmoDisplay;
import widget.StarDisplay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

//TODO off center effects fix
//TODO save game
//TODO pause button
// TODO store all references in Game SinglePLayerGame Menu
//TODO detect placement overlaps
//TODO normalise ship widths

//TODO collision checking lags because index 0 is being checked, check all !

public class SinglePlayerGame implements Serializable
{
    private static final int NUM_SCREENS = 3;
    private static final double BULLET_POWERUP_PROB = 0.2;
    private static final double TIME_BULLET_POWERUP_PROB = 0.2;
    private static final double PROTECTION_POWERUP_PROB = 0.2;
    private static final double TIME_POWERUP_PROB = 0.2;


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
    private PowerUp[] powerUps; // to store all powerups/collectibles
    private StarPowerUp[] stars; // to store all the stars
    private Sprite bottomCollisionDetector; // to denote the bottom of the frame
    private Random rand;
    private int starCount;
    private double score;
    private AmmoDisplay ammoDisplay;
    private StarDisplay starDisplay;
//    private AnimatedEffect effects;

    //user info



    public SinglePlayerGame(Stage maineStage)
    {
        //init utils
        rand = new Random();
        //init score to 0
        starCount =0;
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
        ship = new PlayerShip(GameColor.values()[rand.nextInt(4)], 2);

        //init ammoDisplay

        ammoDisplay = new AmmoDisplay(ship.getBulletsAmmo());

        //init starDisplay

        starDisplay = new StarDisplay();


        //init obstacles
        initObstacles();

        //init powerups
        initPowerUps();

        //init stars
        initStars();

        //init screen bottom collision rectangle
        bottomCollisionDetector = new Sprite();
        bottomCollisionDetector.setPosition(0, config.getSCREEN_HEIGHT()-10);
        bottomCollisionDetector.setBoundary(new CollisionRectangle(0, config.getSCREEN_HEIGHT()-10, config.getSCREEN_WIDTH()*2, 10));
        bottomCollisionDetector.setVisible(false);
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

    private void initStars()
    {
        //TODO change this to be random
        //randomly with low chance spawn other powerups
        stars = new StarPowerUp[NUM_SCREENS];

        for(int i = 0; i< NUM_SCREENS; i++)
        {
            stars[i] = new StarPowerUp(-(i*config.getSCREEN_HEIGHT() + 0.5*config.getSCREEN_HEIGHT()));
        }
    }

    private void initPowerUps()
    {
        //TODO change this to be random
        //randomly with low chance spawn other powerups
        powerUps = new PowerUp[NUM_SCREENS];

        for(int i = 0; i< NUM_SCREENS; i++)
        {
            powerUps[i] = new BulletPowerUp(-(i*config.getSCREEN_HEIGHT() + 0.25*config.getSCREEN_HEIGHT()));
        }
    }

    private void initObstacles()
    {
        //TODO change this to be random
        obstacles = new Obstacle[NUM_SCREENS];

        for(int i = 0; i< NUM_SCREENS; i++)
        {
            double yVal = -i*config.getSCREEN_HEIGHT();

            if(i%2==0)
            {
                obstacles[i] = new RotatingSatellitesObstacle(yVal);
            }
            else
            {
                obstacles[i] = new RotatingSatellitesObstacle(yVal);
            }
        }


    }

    private void pauseGame()
    {
        gameLoop.stop();

        //darken screen

        //display menu

        //resume maybe
    }

    private boolean toss(double probability)
    {
        int numToss = (int) (1/probability);

        if(rand.nextInt(numToss) == 0) // probability chance
        {
            return true;
        }
        return false;
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
        private ArrayList<AnimatedEffect> effects;
        private boolean isSlow;
        private final long slowTimeLimit = 1000;
        private long slowTimeCounter;


        public GameAnimationTimer()
        {
            super();
            effects = new ArrayList<AnimatedEffect>();
            shiftingWindow=false;
            lineOfControl = config.getSCREEN_HEIGHT()*0.4;
            windowShiftDelta = 1.5;
            screenRectangle = new CollisionRectangle(0,0,config.getSCREEN_WIDTH(), config.getSCREEN_HEIGHT());
            slowTimeCounter = 0;
        }

        public void cleanSlate()
        {
            context.setFill(Color.web(config.getBACKGROUND_COLOR()));
            context.fillRect(0, 0, config.getSCREEN_WIDTH(), 3*config.getSCREEN_HEIGHT());//replace with background
        }

        @Override
        public void handle(long l)
        {
            //erase stuff


            if(shiftingWindow && ship.isActive())  //shift everything
            {
                if(ship.getPosition().getY()<lineOfControl && ship.getVelocity().getY()<=0) // bring everything down in the frame
                {
                    //shift ship and bullets
                    ship.shiftPosition(ship.getVelocity());

                    //shift obstacles and powerups and stars
                    for(int i = 0; i< NUM_SCREENS; i++)
                    {
                        obstacles[i].shiftPosition(ship.getVelocity()); // shift obstacles
                        powerUps[i].shiftPosition(ship.getVelocity()); // shift powerups
                        stars[i].shiftPosition(ship.getVelocity()); // shift stars
                    }

                    //shift effects
                    if(!effects.isEmpty())
                    {
                        for(int i=0 ;i < effects.size(); i++)
                        {
                            effects.get(i).shiftPosition(ship.getVelocity());
                        }
                    }

                }
                else // turn off shifting
                {
                    shiftingWindow=false;
                }
            }
             //draw stuff



            if(isSlow) //draw stuff
            {
                slowTimeCounter++;
                if (slowTimeCounter >= slowTimeLimit)
                {
                    isSlow = false;
                    slowTimeCounter = 0;
                }
            }

                cleanSlate();
                drawShip(l);

                drawObstacles(l);

                drawStars(l);

                drawPowerUps(l);

                drawScore();
                drawMisc(l);



            //check for collisions
            checkCollisions();

        }

        private void drawMisc(long l)
        {
            if(config.getSHOW_COLLISION_BOUNDS())
            {
                bottomCollisionDetector.render(context);
            }
            if(!effects.isEmpty()) // for any additional game level effects
            {
                Iterator itr = effects.iterator();

                while (itr.hasNext())
                {
                    AnimatedEffect currentEffect = (AnimatedEffect) itr.next();
                    if(currentEffect.isFinished())
                    {
                        itr.remove();
                    }
                    else
                    {
                        if(isSlow)
                        {
                            if(slowTimeCounter%2==0)
                            {
                                currentEffect.update();
                            }
                        }
                        else
                        {
                            currentEffect.update();
                        }
                        currentEffect.render(context);
                    }
                }
            }


        }
//TODO move update inside render
        private void drawObstacles(long l)
        {
            for(int i = 0; i< NUM_SCREENS; i++)
            {
                if(isSlow)
                {
                    if(slowTimeCounter%2==0)
                    {
                        obstacles[i].update();
                    }
                }
                else
                {
                    obstacles[i].update();
                }

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

            double yVal = -(NUM_SCREENS -1)*config.getSCREEN_HEIGHT();


//            obstacles[NUM_SCREENS -1] = new RotatingArmsObstacle();

            if(obstacleCount%2==0)
            {
                obstacles[NUM_SCREENS -1] = new RotatingArmsObstacle(50, yVal);
            }
            else
            {
                obstacles[NUM_SCREENS -1] = new RotatingArmsObstacle(config.getSCREEN_WIDTH()-50, yVal);
            }
//            obstacles[NUM_OBSTACLES-1].setPosition(20,-(NUM_OBSTACLES-1)*config.getSCREEN_HEIGHT());
            obstacleCount++;
        }

        private void drawScore()
        {
            context.setFont(Font.loadFont(config.getPRIMARY_FONT(), 20));
            context.setFill(Paint.valueOf(config.getREGULAR_FONT_COLOR()));
            context.setTextAlign(TextAlignment.RIGHT);
            context.fillText("Score: "+ (int) score, config.getSCREEN_WIDTH() - 10, 30 );

            //render ammo display

            ammoDisplay.render(context);

            //render star display
            starDisplay.update(starCount);
            starDisplay.render(context);
        }

        private void drawStars(long l)
        {
            for(int i = 0; i< NUM_SCREENS; i++)
            {
                if(isSlow)
                {
                    if(slowTimeCounter%2==0)
                    {
                        stars[i].update();
                    }
                }
                else
                {
                    stars[i].update();
                }
                stars[i].render(context);
            }

            //check if obstacle 0 is in frame

            if(!stars[0].isInFrame())
            {
                spawnNewStar();
                System.out.println("Spawning new Star!");
            }
        }

        private void spawnNewStar()
        {
            //shift everything in array
            for(int i = 0; i< NUM_SCREENS -1; i++)
            {
                stars[i] = stars[i+1];
//                System.out.println(powerups[i]);
            }
            //create new obstacle
            //TODO randomise this


            stars[NUM_SCREENS -1] = new StarPowerUp(-((NUM_SCREENS-1)*config.getSCREEN_HEIGHT() + 0*config.getSCREEN_HEIGHT()));
        }

        private void drawPowerUps(long l)
        {
            for(int i = 0; i< NUM_SCREENS; i++)
            {
                if(isSlow)
                {
                    if(slowTimeCounter%2==0)
                    {
                        powerUps[i].update();
                    }
                }
                else
                {
                    powerUps[i].update();
                }
                powerUps[i].render(context);
            }

            //check if obstacle 0 is in frame

            if(!powerUps[0].isInFrame())
            {
                spawnNewPowerUp();
                System.out.println("Spawning new Powerup!");
            }
        }

        private void spawnNewPowerUp()
        {
            //shift everything in array
            for(int i = 0; i< NUM_SCREENS -1; i++)
            {
                powerUps[i] = powerUps[i+1];
//                System.out.println(powerups[i]);
            }
            //create new obstacle
            //TODO randomise this

            //coordinate for new powerup
            double yVal = -( (NUM_SCREENS-1)*config.getSCREEN_HEIGHT() + 0*config.getSCREEN_HEIGHT());


            if(toss(BULLET_POWERUP_PROB))
            {
                powerUps[NUM_SCREENS-1] = new BulletPowerUp(yVal);
            }
            else if(toss(TIME_BULLET_POWERUP_PROB))
            {
                powerUps[NUM_SCREENS-1] = new TimeBulletPowerUp(yVal);
            }
            else if(toss(TIME_POWERUP_PROB))
            {
                powerUps[NUM_SCREENS-1] = new TimePowerUp(yVal);
            }
            else if(toss(PROTECTION_POWERUP_PROB))
            {
                powerUps[NUM_SCREENS-1] = new ProtectionPowerUp(yVal);
            }
            else
            {
                powerUps[NUM_SCREENS-1] = new ColorSwitcher(yVal);
            }
        }




        private void drawShip(long l)
        {

            if(inputList.contains("SPACE") && !ship.isBoosted())// if not already boosted, call boost
            {
                if(ship.isActive())
                ship.boost();

            }
            else if(!inputList.contains("SPACE") && ship.isBoosted())
            {
                if(ship.isActive())
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
            for(int i=0; i<NUM_SCREENS; i++) // powerups and obstacles and stars
            {

                //check collision with lower boundary
                if(ship.didCollide(bottomCollisionDetector))
                {
                    System.out.println("Game Over");
                    ship.destroy();
                }

                //check with other objects
                if(obstacles[i].didCollisionWithShip(ship))
                {
                    //game over
                    //TODO implement this
                    System.out.println("Game Over");
                    ship.destroy();
                }

                if(ship.didCollide(powerUps[i]))
                {
                    PowerUp current = powerUps[i];

                    if(current.getClass() == ColorSwitcher.class)
                    {
                        ship.switchColor();
                    }
                    else if(current.getClass() == BulletPowerUp.class)
                    {
                        ship.addAmmo(new GrenadeBullet(ship));
                    }
                    else if(current.getClass() == TimeBulletPowerUp.class)
                    {
                        ship.addAmmo(new TimeBullet(ship));
                    }
                    else if(current.getClass() == ProtectionPowerUp.class)
                    {
                        ship.protectionOn();
                    }
                    else if(current.getClass() == TimePowerUp.class)
                    {
                        //TODO implement this
                        isSlow=true;
                        //TODO add screen overlay animation
                    }
                    score+=50;
                    current.destroy();// destroy powerup from canvas

                }

                if(ship.didCollide(stars[i]))
                {
                    starCount++;
                    score+=50;
                    stars[i].destroy();
//                    ship.addCollectEffect();
                    //TODO change thi®s
                }
            }
        }
    }
}
