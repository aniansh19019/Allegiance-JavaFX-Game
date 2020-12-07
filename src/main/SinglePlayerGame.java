package main;

import animations.AnimatedEffect;
import animations.TimeOverlayEffect;
import game_object.*;
import game_object.bullets.GrenadeBullet;
import game_object.bullets.TimeBullet;
import game_object.obstacles.*;
import game_object.powerups.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
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

//TODO game lagging, optimise!!


//TODO sounds: timeslow, music, game end, revive,
//TODO juice up the game!
//TODO add overlays

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
    private static final double BULLET_POWERUP_PROB = 0.1;
    private static final double TIME_BULLET_POWERUP_PROB = 0.2;
    private static final double PROTECTION_POWERUP_PROB = 0.8;
    private static final double TIME_POWERUP_PROB = 0.4;

    //init sounds
    private static AudioClip timePowerupOnSound;
    private static AudioClip timePowerupOffSound;
    private static AudioClip backGroundMusic;


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
    private AmmoDisplay ammoDisplay;
    private StarDisplay starDisplay;
    private int level;
    private PauseMenu pauseMenu;
    private MenuButton pauseButton;
//    private AnimatedEffect effects;

    //user info

    static {
        timePowerupOnSound = new AudioClip("file:res/sound/time_powerup_on.mp3");
        timePowerupOffSound = new AudioClip("file:res/sound/time_power_up_off.mp3");
        backGroundMusic = new AudioClip("file:res/sound/Automation.mp3");
        backGroundMusic.setCycleCount(AudioClip.INDEFINITE);
    }





    public SinglePlayerGame(Stage maineStage)
    {
        //init utils
        rand = new Random();
        //init score to 0
        starCount =0;
        //init vars
        inputList = new ArrayList<String>();
        level=1;
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
        // init cursor
        Image cursorImage = new Image("file:res/img/ui_elements/cursor_arrow.png");
        ImageCursor cursor = new ImageCursor(cursorImage);
        backGroundMusic.play();

        //init pause menu

        pauseButton = new MenuButton(e -> pauseGame());
        pauseButton.setNormalImageString("file:res/img/ui_elements/pause_blue.png");
        pauseButton.setHoverImageString("file:res/img/ui_elements/pause_yellow.png");
        pauseButton.setClickImageString("file:res/img/ui_elements/pause_orange.png");
        pauseButton.loadImages();
        pauseButton.getButton().setScaleX(0.13);
        pauseButton.getButton().setScaleY(0.13);

        //add pause button

        this.root.getChildren().add(pauseButton.getButton());
        this.root.setAlignment(pauseButton.getButton(), Pos.TOP_LEFT);
        pauseButton.getButton().setTranslateX(-120);
        pauseButton.getButton().setTranslateY(-110);


        this.root.setCursor(cursor);



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

    private PowerUp getRandomPowerUp()
    {
        return null;
        //TODO implement
    }

    private void initPowerUps()
    {
        //TODO change this to be random
        //randomly with low chance spawn other powerups
        powerUps = new PowerUp[NUM_SCREENS];

        for(int i = 0; i< NUM_SCREENS; i++)
        {
            powerUps[i] = new ColorSwitcher(-(i*config.getSCREEN_HEIGHT() + 0.25*config.getSCREEN_HEIGHT()));
        }
    }

    private Obstacle getRandomObstacle()
    {
        //TODO IMPLEMENT
        return null;
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
                obstacles[i] = new RotatingSatellitesObstacle(yVal, ObstaclePosition.CENTER, level);
            }
            else
            {
                obstacles[i] = new SlidingBulbsObstacle(yVal, level);
            }
        }


    }

    private void pauseGame()
    {
        //TODO draw overlay and play sound
        Image pauseOverlay = new Image("file:res/img/overlays/pause_overlay.png");
        context.drawImage(pauseOverlay, 0, 0);

        gameLoop.stop();
        pauseMenu = new PauseMenu(e -> resumeGame(), null, null, null);
        root.getChildren().add(pauseMenu.getLayer());
        root.getChildren().remove(pauseButton.getButton());

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
        System.out.println("Game Over");
    }

    private void resumeGame()
    {
        //play sound
        root.getChildren().remove(pauseMenu.getLayer());
        root.getChildren().add(pauseButton.getButton());
        gameLoop.start();
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
                    //turn off overlay
                    for(int i=0; i< effects.size(); i++)
                    {
                        if(effects.get(i).getClass() == TimeOverlayEffect.class)
                        {
                            effects.get(i).setLoopFrames(false);
                        }
                    }
                    //play sound
                    timePowerupOffSound.play();
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
                obstacles[NUM_SCREENS -1] = new RotatingArmsObstacle(yVal,ObstaclePosition.LEFT, level);
            }
            else
            {
                obstacles[NUM_SCREENS -1] = new RotatingArmsObstacle(yVal,ObstaclePosition.RIGHT,level);
            }
//            obstacles[NUM_OBSTACLES-1].setPosition(20,-(NUM_OBSTACLES-1)*config.getSCREEN_HEIGHT());
            obstacleCount++;
            //increment level
            level++;
        }

        private void drawScore()
        {
            context.setFont(Font.loadFont(config.getPRIMARY_FONT(), 20));
            context.setFill(Paint.valueOf(config.getREGULAR_FONT_COLOR()));
            context.setTextAlign(TextAlignment.RIGHT);
            context.fillText("Score: "+ ship.getScore(), config.getSCREEN_WIDTH() - 10, 30 );

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


            stars[NUM_SCREENS -1] = new StarPowerUp(-((NUM_SCREENS-1)*config.getSCREEN_HEIGHT()));
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
            double yVal = -( (NUM_SCREENS-1)*config.getSCREEN_HEIGHT() );


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
                    ship.destroy();
                    gameOver();
                }

                //check with other objects
                if(obstacles[i].didCollisionWithShip(ship))
                {
                    //game over
                    //TODO implement this
                    ship.destroy();
                    gameOver();
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

                        //adding overlay
                        if(!isSlow)
                            effects.add(new TimeOverlayEffect(ship.getPosition()));

                        isSlow=true;
                        slowTimeCounter=0;
                        //playing sound
                        timePowerupOnSound.play();

                        //TODO add screen overlay animation
                    }
                    ship.addScore(50);
                    current.destroy();// destroy powerup from canvas

                }

                if(ship.didCollide(stars[i]))
                {
                    starCount++;
                    ship.addScore(50);
                    stars[i].destroy();
//                    ship.addCollectEffect();
                    //TODO change thi®s
                }
            }
        }
    }
}
