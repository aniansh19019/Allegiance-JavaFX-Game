package assignments;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.ArrayList;

public class FirstApp
{
    private static final int GAME_STAGE_WIDTH = 500;
    private static final int GAME_STAGE_HEIGHT = 650;
    private Sprite missile = null;

    public Scene startGame(Button pauseButton) throws Exception
    {
        StackPane root = new StackPane();
        Scene gamePlayScene = new Scene(root);
        gamePlayScene.setFill(Color.web("3a243b"));
        Image spaceShipImg = new Image("file:res/spaceShip.png");
        Image spaceShipAccelImg = new Image("file:res/ShipAccel.png");


        Canvas canvas = new Canvas(GAME_STAGE_WIDTH, GAME_STAGE_HEIGHT);
        GraphicsContext context = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        context.setFill(Color.web("3a243b"));
        context.fillRect(0,0,GAME_STAGE_WIDTH, GAME_STAGE_HEIGHT);


        Image obstacleImage = new Image("file:res/obstacle.png");
        ImageView obstacle = new ImageView(obstacleImage);
        obstacle.setScaleX(0.7);
        obstacle.setScaleY(0.7);
//TODO stars
        obstacle.setY(0.1*GAME_STAGE_HEIGHT);
        root.getChildren().add(obstacle);
        RotateTransition obstacleRotate = new RotateTransition();
        obstacleRotate.setByAngle(360);
        obstacleRotate.setDuration(Duration.seconds(6));
        obstacleRotate.setNode(obstacle);
        obstacleRotate.setCycleCount(3000);
        obstacleRotate.setDelay(Duration.ZERO);
        obstacleRotate.play();

        Sprite ship = new Sprite();
        ship.setScreenHeight(GAME_STAGE_HEIGHT);
        ship.setScreenWidth(GAME_STAGE_WIDTH);
        ship.setImage(spaceShipImg);
        ship.setPosition(GAME_STAGE_WIDTH/2, 0.2*GAME_STAGE_HEIGHT);
        ship.setImageScaleFactor(0.2);
        ship.setAcceleration(0, 0.1);
        ship.render(context);
        ArrayList<String> inputList = new ArrayList<String>();
        gamePlayScene.setOnKeyPressed((KeyEvent event) ->
        {
            String keyName = event.getCode().toString();
            if(!inputList.contains(keyName))
                inputList.add(keyName);
        });
        gamePlayScene.setOnKeyReleased((KeyEvent event) ->
        {
            String keyName = event.getCode().toString();
            inputList.remove(keyName);
        });

        StackPane uiLayer = new StackPane();
        uiLayer.setMaxWidth(GAME_STAGE_WIDTH);
        uiLayer.setMaxHeight(GAME_STAGE_HEIGHT);

        uiLayer.setAlignment(Pos.TOP_RIGHT);
        StackPane pauseLayer = new StackPane();
        root.getChildren().addAll(uiLayer, pauseLayer);



        Label scoreLabel = new Label("Score: 123");
        scoreLabel.setFont(Font.loadFont("file:res/kenvector_future_thin.ttf", 22));
        scoreLabel.setTextFill(Color.web("ababab"));
        uiLayer.getChildren().add(scoreLabel);
        uiLayer.getChildren().add(pauseButton);
        uiLayer.setAlignment(pauseButton, Pos.TOP_LEFT);
        Image diamond = new Image("file:res/diamond.png");
        ImageView diamondImage = new ImageView(diamond);
        Label starCount = new Label("#3", diamondImage);
        uiLayer.getChildren().add(starCount);
        uiLayer.setAlignment(starCount, Pos.BOTTOM_RIGHT);
        starCount.setTextFill(Color.web("ababab"));
        starCount.setFont(Font.loadFont("file:res/kenvector_future_thin.ttf", 22));
        pauseButton.setTranslateX(10);
        pauseButton.setTranslateY(10);

//        pauseButton.setOnAction();







        Image missileCountImage = new Image("file:res/missile_count.png");

        RectangleCollisionPolygon screen = new RectangleCollisionPolygon(0,0,GAME_STAGE_WIDTH, GAME_STAGE_HEIGHT);


        AnimationTimer gameloop = new AnimationTimer()
        {
            @Override
            public void handle(long l)
            {
                if(inputList.contains("Z"))
                {
                    ship.setImage(spaceShipAccelImg);
                    ship.setVelocity(0, -2.5);
//                    thrusterPlayer.play();
                }
                else
                {
                    ship.setImage(spaceShipImg);
//                    thrusterPlayer.stop();
                }

                if(inputList.contains("X") && missile==null) // launch missile
                {
                    missile = new Sprite();
                    missile.setImage("file:res/spaceMissiles_001.png");
                    missile.setImageScaleFactor(0.4);
                    missile.setVelocity(0, -5);
                    missile.setPosition(ship.getPosition().getX(), ship.getPosition().getY());
                    missile.getPosition();
                }



                ship.update();

                //TODO on key typed

                context.fillRect(0,0,GAME_STAGE_WIDTH, GAME_STAGE_HEIGHT);


                if(missile!=null)
                {
                    missile.update();
                    missile.render(context);
                    if(!missile.getBoundary().didCollide(screen))
                    {
                        System.out.println("Missile Destroyed!");
                        missile=null;
                    }
                }

                if(ship.getPosition().getY() >= GAME_STAGE_HEIGHT - 100)
                {
                    ship.setVelocity(0,0);
                    ship.getPosition().set(GAME_STAGE_WIDTH/2, GAME_STAGE_HEIGHT - 100);
                }
                if(ship.getPosition().getY() <=0)
                {
                    ship.setVelocity(0,0);
                    ship.getPosition().set(GAME_STAGE_WIDTH/2, 0);
                }


                ship.render(context);
                context.drawImage(missileCountImage, 10, GAME_STAGE_HEIGHT-40,0.2*missileCountImage.getWidth(), 0.2*missileCountImage.getHeight());


            }
        };

        gameloop.start();



        return gamePlayScene;


    }

	public Scene startGame(ImageView view1) {
		// TODO Auto-generated method stub
		return null;
	}


}

