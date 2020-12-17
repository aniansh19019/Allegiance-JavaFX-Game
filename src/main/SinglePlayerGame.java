package main;

import animations.AnimatedEffect;
import animations.TimeOverlayEffect;
import animations.YellowExplosionEffect;
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
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.menu.GameOverMenu;
import main.menu.SaveSuccessMenu;
import widget.MenuButton;
import main.menu.PauseMenu;
import main.menu.main_menu.MenuManager;
import util.*;
import widget.AmmoDisplay;
import widget.StarDisplay;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;



public class SinglePlayerGame implements Serializable
{
    private static final double pauseButtonScale = 0.17;
    //number of entities that exist at a time in the game
    private static final int NUM_SCREENS = 3;
    //powerup probabilities
    private static final double BULLET_POWERUP_PROB = 0.1;
    private static final double TIME_BULLET_POWERUP_PROB = 0.1;
    private static final double PROTECTION_POWERUP_PROB = 0.1;
    private static final double TIME_POWERUP_PROB = 0.1;
    //obstacle probabilities
    private static final double ROTATING_ARMS_OBSTACLE_SINGLE_PROB = 0.1;
    private static final double ROTATING_ARMS_OBSTACLE_DOUBLE_PROB = 0.1;
    private static final double ROTATING_SATELLITES_OBSTACLE_SINGLE_PROB = 0.1;
    private static final double ROTATING_SATELLITES_OBSTACLE_DOUBLE_PROB = 0.1;
    private static final double SLIDING_BULBS_OBSTACLE_DOUBLE_PROB = 0.1;
    private static final double NESTED_ROTATING_SATELLITE_OBSTACLE_DOUBLE_PROB = 0.1;
    // misc
    private static final int HARD_THRESHOLD = 10;


    //init sounds
    private static final AudioClip timePowerupOnSound;
    private static final AudioClip timePowerupOffSound;
    private static final AudioClip backGroundMusic;
    private static final AudioClip pauseSound;
    private static final AudioClip resumeSound;
    private static final AudioClip gameOverSound;



    private transient Scene scene;
    private transient Stage mainStage; //reference to the main stage
    private static final GlobalConfig config;
    private GameAnimationTimer gameLoop;
    private transient Canvas gameCanvas;
    private transient StackPane root;
    private transient GraphicsContext context;
    private final PlayerShip ship;
    private ArrayList<String> inputList;
    private Obstacle[] obstacles; // to store all obstacles
    private PowerUp[] powerUps; // to store all powerups/collectibles
    private StarPowerUp[] stars; // to store all the stars
    private Sprite bottomCollisionDetector; // to denote the bottom of the frame
    private static Random rand;
    private int starCount;
    private transient AmmoDisplay ammoDisplay;
    private transient StarDisplay starDisplay;
    private int level;
    private transient PauseMenu pauseMenu;
    private transient MenuButton pauseButton;
    private transient SaveSuccessMenu saveSuccessMenu;
    private transient GameOverMenu gameOverMenu;
    private int starsRequired;
    private boolean isDestroyed;
    private String name;
    private transient SinglePlayerGameWrapper wrapper;

    private transient MenuManager menuManager;
//    private AnimatedEffect effects;


    public void startTimer()
    {
        gameLoop.start();
        if(GlobalConfig.isSoundOn())
        backGroundMusic.play();
    }

    public void stopTimer()
    {
        gameLoop.stop();
        backGroundMusic.stop();
    }


    public PlayerShip getShip()
    {
        return ship;
    }

    public int getStarCount()
    {
        return starCount;
    }

    public void setStarCount(int starCount)
    {
        this.starCount = starCount;
    }

    public MenuManager getMainMenu()
    {
        return menuManager;
    }

    public void setMainMenu(MenuManager menuManager)
    {
        this.menuManager = menuManager;
        this.mainStage = menuManager.getWindow();
    }

    public void setMainStage(Stage mainStage)
    {
        this.mainStage = mainStage;
    }

    public void setWrapper(SinglePlayerGameWrapper wrapper)
    {
        this.wrapper = wrapper;
    }
    //user info

    static {
        //init config
        config = new GlobalConfig();
        //init rand
        rand = new Random();
        //init sounds
        timePowerupOnSound = new AudioClip("file:res/sound/time_powerup_on.mp3");
        timePowerupOffSound = new AudioClip("file:res/sound/time_power_up_off.mp3");
//        backGroundMusic = new AudioClip("file:res/sound/Automation.mp3");
        backGroundMusic = new AudioClip("file:res/sound/Automation.mp3");
        backGroundMusic.setCycleCount(AudioClip.INDEFINITE);

        resumeSound = new AudioClip("file:res/sound/resume_sound.mp3");
        pauseSound = new AudioClip("file:res/sound/pause_sound.mp3");
        gameOverSound = new AudioClip("file:res/sound/game_over_sound.mp3");
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public SinglePlayerGame(MenuManager menu, SinglePlayerGameWrapper wrapper)
    {
        //init utils
        this.wrapper=wrapper;

        //init menu
        menuManager = menu;
        //init player name
        this.name=  menu.getPlayerName();
        //init score to 0
        starCount =0;
        //init vars
        isDestroyed=false;
        inputList = new ArrayList<String>();
        level=1;
        starsRequired=2;

        //init stage
        this.mainStage = menu.getWindow();
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

        ship = new PlayerShip(GameColor.values()[rand.nextInt(4)], menuManager.getShipNum());

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
        this.root.setCursor(cursor);
        backGroundMusic.play();

        //init pause menu

        pauseButton = new MenuButton(e -> pauseGame());
        pauseButton.setNormalImageString("file:res/img/ui_elements/pause_blue.png");
        pauseButton.setHoverImageString("file:res/img/ui_elements/pause_yellow.png");
        pauseButton.setClickImageString("file:res/img/ui_elements/pause_orange.png");
        pauseButton.loadImages();
        pauseButton.getButton().setScaleX(pauseButtonScale);
        pauseButton.getButton().setScaleY(pauseButtonScale);


        //add pause button

        this.root.getChildren().add(pauseButton.getButton());
        StackPane.setAlignment(pauseButton.getButton(), Pos.TOP_LEFT);
        pauseButton.getButton().setTranslateX(-110);
        pauseButton.getButton().setTranslateY(-100);






        gameLoop.start();


    }

    private void initStars()
    {

        //randomly with low chance spawn other powerups
        stars = new StarPowerUp[NUM_SCREENS];

        for(int i = 0; i< NUM_SCREENS; i++)
        {
            stars[i] = new StarPowerUp(-(i*config.getSCREEN_HEIGHT() + 0.5*config.getSCREEN_HEIGHT()));
        }
    }

    private PowerUp getRandomPowerUp(double y)
    {
        ArrayList<PowerUp> candidates = new ArrayList<PowerUp>();

        if(toss(PROTECTION_POWERUP_PROB))
        {
            candidates.add(new ProtectionPowerUp(y));
        }
        if(toss(TIME_POWERUP_PROB))
        {
            candidates.add(new TimePowerUp(y));
        }
        if(toss(BULLET_POWERUP_PROB))
        {
            candidates.add(new BulletPowerUp(y));
        }
        if(toss(TIME_BULLET_POWERUP_PROB))
        {
            candidates.add(new TimeBulletPowerUp(y));
        }
        if(candidates.isEmpty())
        {
            candidates.add(new ColorSwitcher(y));
        }

        PowerUp selected = candidates.get(rand.nextInt(candidates.size())); // return a random powerup from the selected powerups


        return selected;
    }

    private void initPowerUps()
    {
        //randomly with low chance spawn other powerups
        powerUps = new PowerUp[NUM_SCREENS];

        for(int i = 0; i< NUM_SCREENS; i++)
        {
            powerUps[i] = getRandomPowerUp(-(i*config.getSCREEN_HEIGHT() + 0.25*config.getSCREEN_HEIGHT()));
        }
    }


    private ObstaclePosition getRandomPosition(boolean includeCenter)
    {
        if(!includeCenter)
        {
            if (rand.nextInt() % 2 == 0)
            {
                return ObstaclePosition.LEFT;
            }
            return ObstaclePosition.RIGHT;
        }
        else
        {
            int toss = rand.nextInt(3);
            if(toss==0)
            {
                return ObstaclePosition.LEFT;
            }
            else if(toss == 1)
            {
                return ObstaclePosition.RIGHT;
            }
            return ObstaclePosition.CENTER;
        }
    }

    private Obstacle getRandomObstacle(double y)
    {
        ArrayList<Obstacle> candidates = new ArrayList<Obstacle>();
        boolean isHard=false;
        if(level>HARD_THRESHOLD)
        {
            isHard=true;
        }

        if(toss(ROTATING_ARMS_OBSTACLE_SINGLE_PROB))
        {
            if(isHard && toss(0.5))
            {
                candidates.add(new HardRotatingArmsObstacle(y, getRandomPosition(false), level/2));
            }
            else
            {
                candidates.add(new RotatingArmsObstacle(y, getRandomPosition(false), level));
            }
        }
        if(toss(ROTATING_ARMS_OBSTACLE_DOUBLE_PROB))
        {
            if(isHard && toss(0.5))
            {
                candidates.add(new HardDoubleRotatingArmsObstacle(y, level/4));
            }
            else
            {
                candidates.add(new DoubleRotatingArmsObstacle(y, level));
            }
        }
        if(toss(ROTATING_SATELLITES_OBSTACLE_SINGLE_PROB))
        {
            candidates.add(new RotatingSatellitesObstacle(y, getRandomPosition(true),level));
        }
        if(toss(ROTATING_SATELLITES_OBSTACLE_DOUBLE_PROB))
        {
            if(isHard && toss(0.5))
            {
                candidates.add(new HardDoubleRotatingSatellitesObstacle(y, level/4));
            }
            else
            {
                candidates.add(new DoubleRotatingSatellitesObstacle(y, level));
            }
        }
        if(toss(NESTED_ROTATING_SATELLITE_OBSTACLE_DOUBLE_PROB))
        {
            candidates.add(new NestedRotatingSatellitesObstacle(y, level/4));
        }
        if(toss(SLIDING_BULBS_OBSTACLE_DOUBLE_PROB))
        {
            candidates.add(new SlidingBulbsObstacle(y,level));
        }
        if(candidates.isEmpty())
        {
            candidates.add(new RotatingSatellitesObstacle(y, ObstaclePosition.CENTER, level));
        }

        return candidates.get(rand.nextInt(candidates.size())); // return one of the above
    }

    private void initObstacles()
    {
        obstacles = new Obstacle[NUM_SCREENS];

        for(int i = 0; i< NUM_SCREENS; i++)
        {
            double yVal = -i*config.getSCREEN_HEIGHT();
            obstacles[i] = getRandomObstacle(yVal);
        }
    }

    private void pauseGame()
    {

        backGroundMusic.stop();
        pauseSound.play();

        Image pauseOverlay = new Image("file:res/img/overlays/pause_overlay.png");
        context.drawImage(pauseOverlay, 0, 0);

        gameLoop.stop();
        pauseMenu = new PauseMenu(e -> resumeGame(), e->restartGame(), e->saveGame(), e->quitGame());
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

        backGroundMusic.stop();

        gameOverSound.play();
        Image pauseOverlay = new Image("file:res/img/overlays/pause_overlay.png");
        context.drawImage(pauseOverlay, 0, 0);

        gameLoop.stop();

        gameOverMenu = new GameOverMenu(e->revivePlayer(), e->restartGame(), e->quitGame(),starsRequired,starCount);

        root.getChildren().add(gameOverMenu.getLayer());
        root.getChildren().remove(pauseButton.getButton());


    }

    private void revivePlayer()
    {


        //update stars
        starCount-=starsRequired;
        starsRequired*=2;

        root.getChildren().remove(gameOverMenu.getLayer());
        root.getChildren().add(pauseButton.getButton());
        ship.revive();

        //destroy obstacles
        for(int i=0; i<NUM_SCREENS; i++)
        {
            if(obstacles[i].isInFrame())
            {
                obstacles[i].destroy();
            }
        }
        backGroundMusic.play();


        gameLoop.start();

    }

    private void resumeGame()
    {
        //play sound
        resumeSound.play();
        root.getChildren().remove(pauseMenu.getLayer());
        root.getChildren().add(pauseButton.getButton());
        gameLoop.start();
        backGroundMusic.play();
    }


    private void confirmGameSave()
    {
        root.getChildren().remove(saveSuccessMenu.getLayer());
        root.getChildren().add(pauseMenu.getLayer());
    }

    public void saveGame() //catch exceptions
    {
        //try stuff
        try
        {
            //set file name to current system time in milliseconds
            String fileName = "saves/"+System.currentTimeMillis() + ".sav";

            //open file and create new file if file doesn't exist
            File outFile = new File(fileName);
            outFile.createNewFile();

            //save effect
            FileOutputStream outStream = new FileOutputStream(outFile, false);
            ObjectOutputStream out = new ObjectOutputStream(outStream);
            out.writeObject(this);
            out.close();
            outStream.close();

            System.out.println("Saved Game Successfully!");

            //display confirm menu

            root.getChildren().remove(pauseMenu.getLayer()); // remove pause menu
            saveSuccessMenu = new SaveSuccessMenu(e->confirmGameSave());
            root.getChildren().add(saveSuccessMenu.getLayer());

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    public void restartGame() //unlink current refernce and set new object
    {
        SinglePlayerGame game = new SinglePlayerGame(menuManager, wrapper);
        wrapper.setGame(game);
        mainStage.setScene(game.getScene());
    }

    private void addToLeaderBoard()
    {
        File leaderBoardFile = new File("leaderboard/leaderboard.csv");
        String line = "\n" + name + ", " + starCount + ", " + ship.getScore();

        try
        {
            FileWriter out = new FileWriter(leaderBoardFile, true);
            out.write(line);
            out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void quitGame() // got to menu
    {

        //add record to leaderboards
        addToLeaderBoard();

        ship.mute();
        //proceed to the menu
        backGroundMusic.stop();
        MenuManager.getMenuBackgroundMusic().play();
        mainStage.setScene(menuManager.getRoot());
        menuManager.enterMainMenu();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();

        //init stage
//        this.mainStage = maineStage;

        //init canvas
        this.gameCanvas = new Canvas(config.getSCREEN_WIDTH(), config.getSCREEN_HEIGHT());
        //init context
        this.context = gameCanvas.getGraphicsContext2D();
        //init root node
        this.root = new StackPane();
        root.getChildren().add(gameCanvas);

        //init scene
        scene = new Scene(root);

        //init ammoDisplay

        ammoDisplay = new AmmoDisplay(ship.getBulletsAmmo());

        //init starDisplay

        starDisplay = new StarDisplay();

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


        //init pause menu

        pauseButton = new MenuButton(e -> pauseGame());
        pauseButton.setNormalImageString("file:res/img/ui_elements/pause_blue.png");
        pauseButton.setHoverImageString("file:res/img/ui_elements/pause_yellow.png");
        pauseButton.setClickImageString("file:res/img/ui_elements/pause_orange.png");
        pauseButton.loadImages();
        pauseButton.getButton().setScaleX(pauseButtonScale);
        pauseButton.getButton().setScaleY(pauseButtonScale);

        //add pause button

        this.root.getChildren().add(pauseButton.getButton());
        StackPane.setAlignment(pauseButton.getButton(), Pos.TOP_LEFT);
        pauseButton.getButton().setTranslateX(-110);
        pauseButton.getButton().setTranslateY(-100);


        this.root.setCursor(cursor);


    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException
    {


        out.defaultWriteObject();
    }


    private class GameAnimationTimer extends AnimationTimer implements Serializable
    {
        private boolean shiftingWindow;
        private final double lineOfControl;
        private CollisionRectangle screenRectangle;
        private long firedTime=0;
        private final long fireDelay = 1000000000;
        private ArrayList<AnimatedEffect> effects;
        private boolean isSlow;
        private final long slowTimeLimit = 1000;
        private long slowTimeCounter;
        private int waitShipDestroy = 60;
        private int destroyTimeCounter;
        private final String backGroundImageString = "file:res/img/background/background.png";
        private transient Image backgroundFill;
        private boolean isNewGame=true;
        private Sprite launchPad;

        public ArrayList<AnimatedEffect> getEffects()
        {
            return effects;
        }

        public GameAnimationTimer()
        {
            super();
            effects = new ArrayList<AnimatedEffect>();
            shiftingWindow=false;
            lineOfControl = config.getSCREEN_HEIGHT()*0.4;
            screenRectangle = new CollisionRectangle(0,0,config.getSCREEN_WIDTH(), config.getSCREEN_HEIGHT());
            slowTimeCounter = 0;
            destroyTimeCounter=0;
            backgroundFill = new Image(backGroundImageString);
            launchPad = new Sprite();
            launchPad.setImage(new Image("file:res/img/ships/launch_pad.png.png"));
            launchPad.setImageScaleFactor(0.8);
            launchPad.setPosition(config.getSCREEN_WIDTH()/2, 0.9*config.getSCREEN_HEIGHT());
        }

        public void cleanSlate()
        {
            context.drawImage(backgroundFill,0,0);
        }

        @Override
        public void handle(long l)
        {
            if(shiftingWindow && ship.isActive())  //shift everything
            {
                if(ship.getPosition().getY()<lineOfControl && ship.getVelocity().getY()<=0) // bring everything down in the frame
                {
                    //shift ship and bullets
                    ship.shiftPosition(ship.getVelocity());
                    //shift launchpad
                    launchPad.shiftPosition(ship.getVelocity());

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

            //draw stuff
            cleanSlate(); // clean slate
            //draw
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
            //draw launchpad
            launchPad.render(context);

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


//            double yVal = -(NUM_SCREENS -1)*config.getSCREEN_HEIGHT();
            double yVal = obstacles[NUM_SCREENS-2].getPosition().getY()-config.getSCREEN_HEIGHT();
            obstacles[NUM_SCREENS-1] = getRandomObstacle(yVal);
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
            }
            //create new obstacle
            //coordinate for new powerup
            double yVal = powerUps[NUM_SCREENS-2].getPosition().getY()-config.getSCREEN_HEIGHT();

            PowerUp selected;
            boolean overlap;

            //check for obstacle overlap
            do
            {
                overlap=false;
                selected = getRandomPowerUp(yVal); // return a random powerup from the selected powerups
                for(int i=0; i<NUM_SCREENS; i++)
                {
                    if(obstacles[i].didCollide(selected)!=GameColor.NONE)
                    {
                        overlap=true;
                        yVal-=0.3*config.getSCREEN_HEIGHT();
                    }
                }
            }while(overlap);

            powerUps[NUM_SCREENS-1] = selected;


        }

        private void drawShip(long l)
        {
            if(inputList.contains("SPACE") && isNewGame)
            {
                isNewGame=false;
                effects.add(new YellowExplosionEffect(launchPad.getPosition()));
            }

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

            if(ship.didCollide(launchPad))
            {
                isNewGame = true;
                ship.shiftPosition(new Vector(0,10));
            }

            if(!isNewGame)
            ship.update();
            ship.render(context);
        }

        private void shiftWindow()
        {
            shiftingWindow =true;
        }

        private void checkCollisions()
        {

            //check for destroyed
            if(isDestroyed)
            {
                destroyTimeCounter++;
                if(destroyTimeCounter>waitShipDestroy)
                {
                    gameOver();
                    destroyTimeCounter = 0;
                    isDestroyed=false;
                }
            }

            for(int i=0; i<NUM_SCREENS; i++) // powerups and obstacles and stars
            {

                //check collision with lower boundary
                if(ship.didCollide(bottomCollisionDetector))
                {
                    ship.destroy();
                    isDestroyed=true;
                }

                //check with other objects
                if(obstacles[i].didCollisionWithShip(ship))
                {
                    //game over
                    ship.destroy();
                    isDestroyed=true;
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

                        //adding overlay
                        if(!isSlow)
                            effects.add(new TimeOverlayEffect(ship.getPosition()));

                        isSlow=true;
                        slowTimeCounter=0;
                        //playing sound
                        timePowerupOnSound.play();

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
                }
            }
        }

        private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
        {
            in.defaultReadObject();
            backgroundFill = new Image(backGroundImageString);
        }
    }
}
