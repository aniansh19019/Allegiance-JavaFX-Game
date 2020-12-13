package main.menus.main_menu;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.SinglePlayerGameWrapper;

@SuppressWarnings("static-access")
public class MainMenu
{
	Stage window;
	Scene MainMenu, newGame, loadGame,Leaderboard,settings,help,SingleIntermediate,TwoIntermediate,
	SingleGameplay,TwoGameplay,SingleSaved,TwoSaved,Control,splashscreen,pauseMenu,SavedIntermediate,ChooseShip;
	StackPane pane;

	private boolean soundOn;
	private int shipNum =1;

	public int getShipNum()
	{
		return shipNum;
	}

	public void setShipNum(int shipNum)
	{
		this.shipNum = shipNum;
	}

	public StackPane Pane() {
		pane=new StackPane();
		pane.setBackground(BackgroundgifClass.getBackground());
		return pane;
	}


	private SinglePlayerGameWrapper gameWrapper;



	public MainMenu(Stage window)
	{
		this.window = window;
		soundOn=true;

	}

	public Scene getTwoIntermediate() {
		VBox layout6=new VBox(10);
	    Button startGame2=new Button("Start Game");
	    Button chooseShip2=new Button("Choose Ship");
//	    Button gravity2=new Button("Gravity");
	    startGame2.setOnAction(e->{
	    	window.setScene(TwoGameplay);
	    });
	    layout6.setAlignment(Pos.CENTER);
	    layout6.getChildren().addAll(startGame2,chooseShip2);
	    TwoIntermediate=new Scene(layout6,500,700);
	    TwoIntermediate.getStylesheets().add("file:res/css/sample.css");
	    return TwoIntermediate;
	}
	public Scene getSettings() {
		ImageView back= UserInterfaceElements.getBack();
		back.setOnMouseReleased(e->{
			window.setScene(getMainMenu());
		});
		ImageView Sound_= UserInterfaceElements.getSound();
		ImageView SoundOnBox=new ImageView(UserInterfaceElements.getCheckedBox());
		ImageView SoundOffBox=new ImageView(UserInterfaceElements.getUncheckedBox());
		SoundOffBox.setOnMouseClicked(e->{
			Sound.check().play();
			SoundOffBox.setImage(UserInterfaceElements.getCheckedBox());
			SoundOnBox.setImage(UserInterfaceElements.getUncheckedBox());
			Sound.music().stop();
			
		});
		SoundOnBox.setOnMouseClicked(e->{
			Sound.check().play();
			SoundOnBox.setImage(UserInterfaceElements.getCheckedBox());
			SoundOffBox.setImage(UserInterfaceElements.getUncheckedBox());
			Sound.music().play();
			
		});
		CheckBox SoundOn=new CheckBox();
		SoundOn.setSelected(true);
		CheckBox SoundOff=new CheckBox();
		SoundOff.setOnMouseClicked(e->{
			Sound.check().play();
			SoundOn.setSelected(false);
			Sound.music().stop();
			
		});
		SoundOn.setOnMouseClicked(e->{
			Sound.check().play();
			SoundOff.setSelected(false);
			Sound.music().play();
		});		
		Label label6=new Label("Sound On");
		Label label7=new Label("Sound Off");
		label6.getStyleClass().add("label-main");
		label7.getStyleClass().add("label-main");		
	    BorderPane pane=new BorderPane();
	    pane.setLeft(back);
	    HBox hbox1=new HBox(10);
	    hbox1.getChildren().addAll(SoundOnBox,label6);
	    HBox hbox2=new HBox(10);
	    hbox2.getChildren().addAll(SoundOffBox,label7);
	    VBox vbox=new VBox(10);
	    vbox.getChildren().addAll(Sound_,hbox1,hbox2);
	    pane.setCenter(vbox);
	    vbox.setAlignment(Pos.CENTER);
	    pane.setBackground(BackgroundgifClass.getBackground());
	    settings=new Scene(pane,500,700);
	    settings.getStylesheets().add("file:res/css/sample.css");
		return settings;
	}
	public Scene getSingleGameplay() {		
		ImageView view1= UserInterfaceElements.getPause();
		view1.setOnMouseReleased(e->{
			window.setScene(getPause());
		});
	    VBox layout7=new VBox(10);
	    layout7.getChildren().add(view1);

	    try {
			SingleGameplay=gameWrapper.getGame().getScene();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    SingleGameplay.getStylesheets().add("file:res/css/sample.css");
		return SingleGameplay;
	}
	public Scene getPause() {
		VBox box=new VBox(10); 			 
		Button resume=new Button("Resume");
		Button Restart =new Button("Restart");
		Button exit =new Button("Quit");
		resume.setOnAction(f->{
		//Gameplay
			window.setScene(SingleGameplay);
		});
		Restart.setOnAction(h->{
			window.setScene(SingleIntermediate);
//			PauseWindow.close();
		});
		exit.setOnAction(g->{
//			PauseWindow.close();
			closeProgram();
		});
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(resume,Restart,exit);
		box.setBackground(BackgroundgifClass.getBackground());
		pauseMenu=new Scene(box,500,700);
		return pauseMenu;
		
	}
	public Scene getMainMenu() {
//		Image image=new Image("file:res/download.gif");
//		ImageView view=new ImageView(image);
		Label label1=new Label("Allegiance");
		label1.setFont(Font.loadFont("file:res/font/AlphaCentauri500.ttf", 52));
		label1.setTextFill(Color.web("#d5d0d2"));
		ImageView New_game= UserInterfaceElements.getNewGame();
		New_game.setOnMouseReleased(e->{
			window.setScene(getControls());
		});
		//Button2
        ImageView Load_game = UserInterfaceElements.getLoadGame();
        Load_game.setOnMouseReleased(e->{
        	window.setScene(getLoadGame());
        });
		//Button3
        ImageView view3= UserInterfaceElements.getLeaderBoard();
        view3.setOnMouseReleased(e -> {
			window.setScene(Leaderboard);			
		});
		//Button4
		ImageView Setting_s= UserInterfaceElements.getSettings();
		Setting_s.setOnMouseReleased(e -> 
		{
			window.setScene(getSettings());
		});
		//Button5
		ImageView Help= UserInterfaceElements.getHelp();
		Help.setOnMouseReleased(e -> 
		{	
			window.setScene(getHelp());
			});
		//Button6
        ImageView Exit= UserInterfaceElements.getExit();
        Exit.setOnMouseReleased(e -> {
        	closeProgram();
        });      
		window.setOnCloseRequest(e->{
			e.consume();
			closeProgram();
		});		
		// Layout1
		BorderPane paneMain = new BorderPane();
		VBox layout1=new VBox(10);
		paneMain.setCenter(layout1);
		layout1.getChildren().addAll(New_game,Load_game,view3,Setting_s,Help,Exit);
		layout1.setAlignment(Pos.CENTER);
		paneMain.setTop(label1);
		paneMain.setAlignment(label1, Pos.TOP_CENTER);
		label1.setTranslateY(75);
		paneMain.setBackground(BackgroundgifClass.getBackground());
		MainMenu =new Scene(paneMain ,500,700);
		MainMenu.getStylesheets().add("file:res/css/sample.css");
		return MainMenu;
	}
	public Scene getLoadGame() {
		BorderPane pane=new BorderPane();
        ImageView Back= UserInterfaceElements.getBack();
		Back.setOnMouseReleased(e->{
			window.setScene(getMainMenu());
		});
        ImageView SinglePlayer= UserInterfaceElements.getSinglePlayer();
		SinglePlayer.setOnMouseReleased(e->{
			window.setScene(SingleSaved);
		});
        ImageView TwoPlayer= UserInterfaceElements.getTwoPlayer();
		TwoPlayer.setOnMouseReleased(e->{
			window.setScene(TwoSaved);
		});
		Label label2=new Label("Choose");
		label2.getStyleClass().add("label-main");
		pane.setLeft(Back);
		HBox hbox=new HBox(10);
		hbox.getChildren().addAll(SinglePlayer,TwoPlayer);
		hbox.setAlignment(Pos.CENTER);
		VBox vbox=new VBox(10);
		vbox.getChildren().addAll(label2,hbox);
		vbox.setAlignment(Pos.CENTER);
		pane.setCenter(vbox);
		pane.setBackground(BackgroundgifClass.getBackground());
		loadGame=new Scene(pane,500,700);
		loadGame.getStylesheets().add("file:res/css/sample.css");
		return loadGame;
	}
	public Scene getNewGame() {
		BorderPane pane=new BorderPane();
        ImageView Back= UserInterfaceElements.getBack();
		Back.setOnMouseReleased(e->{
			window.setScene(getMainMenu());
		});
        ImageView SinglePlayer= UserInterfaceElements.getSinglePlayer();
		SinglePlayer.setOnMouseReleased(e->{
			window.setScene(getControls());
		});
        ImageView TwoPlayer= UserInterfaceElements.getTwoPlayer();
        TwoPlayer.setOnMouseReleased(e->{
			window.setScene(TwoIntermediate);
		});
		Label label2=new Label("Choose");
		label2.getStyleClass().add("label-main");
		pane.setLeft(Back);
		HBox hbox=new HBox(10);
		hbox.getChildren().addAll(SinglePlayer,TwoPlayer);
		hbox.setAlignment(Pos.CENTER);
		VBox vbox=new VBox(10);
		vbox.getChildren().addAll(label2,hbox);
		vbox.setAlignment(Pos.CENTER);
		pane.setCenter(vbox);
		pane.setBackground(BackgroundgifClass.getBackground());
		newGame=new Scene(pane,500,700);
		newGame.getStylesheets().add("file:res/css/sample.css");
		return newGame;
	}
	public Scene getControls() {
		ImageView view= UserInterfaceElements.getHelpInfo();
		StackPane pane=new StackPane();
		pane.getChildren().add(view);
		pane.setAlignment(Pos.CENTER);
		PauseTransition wait=new PauseTransition(Duration.seconds(1));
		wait.setOnFinished(e-> window.setScene(getChooseShip()));
		wait.play();
		pane.setBackground(BackgroundgifClass.getBackground());
		Control=new Scene(pane,500,700);
		Control.getStylesheets().add("Sample.css");
		return Control;
	 }
	public Scene getSingleIntermediate() {
		ImageView Back= UserInterfaceElements.getBack();
		Back.setOnMouseReleased(e->{
			window.setScene(getNewGame());
		});
		VBox layout5=new VBox(10);
		ImageView view1= UserInterfaceElements.getStartGame();
		view1.setOnMouseReleased(e->{
			window.setScene(SingleGameplay);
		});
		ImageView view2= UserInterfaceElements.getChooseShip();
		view2.setOnMouseReleased(e->{
			window.setScene(getChooseShip());
		});
		BorderPane  pane=new BorderPane();
		pane.setLeft(Back);
//	    Button gravity1=new Button("Gravity");	   
	    layout5.setAlignment(Pos.CENTER);
	    layout5.getChildren().addAll(view1,view2);
	    pane.setBackground(BackgroundgifClass.getBackground());
	    pane.setCenter(layout5);
	    SingleIntermediate=new Scene(pane,500,700);
		return SingleIntermediate;
	}
	public Scene getSplashScreen() {
		Label label=new Label();
		label.setText("Loading..");
		label.setFont(Font.loadFont("file:res/font/AlphaCentauri500.ttf", 52));
		label.setTextFill(Color.web("#d5d0d2"));
		StackPane pane=new StackPane();
		pane.getChildren().add(label);
		pane.setBackground(BackgroundgifClass.getBackground());
		FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
	    fadeIn.setFromValue(0.8);
	    fadeIn.setToValue(1);
	    fadeIn.setCycleCount(1);
	    //Finish splash with fade out effect
	    FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
	    fadeOut.setFromValue(1);
	    fadeOut.setToValue(0.6);
	    fadeOut.setCycleCount(1);
	    fadeIn.play();
	    //After fade in, start fade out
	    fadeIn.setOnFinished((e) -> {
	        fadeOut.play();
	    });
		fadeOut.setOnFinished(e-> window.setScene(getMainMenu()));
		splashscreen=new Scene(pane, 500, 700);
		Sound.music().play();
		return splashscreen;
	}
	public Scene getSavedIntermediate() {
		BorderPane pane=new BorderPane();
        ImageView Back= UserInterfaceElements.getBack();
		Back.setOnMouseReleased(e->{
			window.setScene(getMainMenu());
		});
        ImageView SinglePlayer= UserInterfaceElements.getSinglePlayer();
		SinglePlayer.setOnMouseReleased(e->{
			window.setScene(SingleSaved);
		});
        ImageView TwoPlayer= UserInterfaceElements.getTwoPlayer();
        TwoPlayer.setOnMouseReleased(e->{
			window.setScene(TwoSaved);
		});
		Label label2=new Label("Choose");
		label2.getStyleClass().add("label-main");
		pane.setLeft(Back);
		HBox hbox=new HBox(10);
		hbox.getChildren().addAll(SinglePlayer,TwoPlayer);
		hbox.setAlignment(Pos.CENTER);
		VBox vbox=new VBox(10);
		vbox.getChildren().addAll(label2,hbox);
		vbox.setAlignment(Pos.CENTER);
		pane.setCenter(vbox);
		pane.setBackground(BackgroundgifClass.getBackground());
		SavedIntermediate=new Scene(pane,500,700);
		SavedIntermediate.getStylesheets().add("file:res/css/sample.css");
		return SavedIntermediate;
	}
	public Scene getHelp() {
		BorderPane pane=new BorderPane();
		ImageView Back= UserInterfaceElements.getBack();
		pane.setLeft(Back);
		Back.setOnMouseReleased(e->window.setScene(getMainMenu()));
		VBox box=new VBox(30);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(UserInterfaceElements.getInstruction(), UserInterfaceElements.getHelpInfo());
		pane.setCenter(box);
		pane.setRight(new Label("           "));
		pane.setBackground(BackgroundgifClass.getBackground());
		help=new Scene(pane,500,700);		
		return help;
	}
	public Scene getChooseShip() {
		ImageView Choose_Ship= UserInterfaceElements.getChoose_Ship();
		HBox hbox1=new HBox(10);
		hbox1.setAlignment(Pos.CENTER);
		ImageView ship1=new ImageView(UserInterfaceElements.getShip1Clicked());
		ImageView ship2=new ImageView(UserInterfaceElements.getShip2Normal());
		ImageView ship3=new ImageView(UserInterfaceElements.getShip3Normal());
		ImageView ship4=new ImageView(UserInterfaceElements.getShip4Normal());
		ship1.setOnMousePressed(e->{
			Sound.click().play();
		});
		ship1.setOnMouseEntered(e->ship1.setImage(UserInterfaceElements.getShip1Hover()));
		ship1.setOnMouseExited(e->ship1.setImage(UserInterfaceElements.getShip1Normal()));
		ship1.setOnMouseReleased(e->{
			shipNum =1;
			System.out.println(shipNum);
			ship1.setImage(UserInterfaceElements.getShip1Clicked());
			ship2.setImage(UserInterfaceElements.getShip2Normal());
			ship3.setImage(UserInterfaceElements.getShip3Normal());
			ship4.setImage(UserInterfaceElements.getShip4Normal());
		});
		
		ship2.setOnMousePressed(e->{
			Sound.click().play();			
		});
		ship2.setOnMouseEntered(e->ship2.setImage(UserInterfaceElements.getShip2Hover()));
		ship2.setOnMouseExited(e->ship2.setImage(UserInterfaceElements.getShip2Normal()));
		ship2.setOnMouseReleased(e->{
			shipNum =2;
			System.out.println(shipNum);
			ship2.setImage(UserInterfaceElements.getShip2Clicked());
			ship1.setImage(UserInterfaceElements.getShip1Normal());
			ship3.setImage(UserInterfaceElements.getShip3Normal());
			ship4.setImage(UserInterfaceElements.getShip4Normal());
		});
		hbox1.getChildren().addAll(ship1,ship2);
		HBox hbox2=new HBox(10);
		hbox2.setAlignment(Pos.CENTER);
		ship3.setOnMousePressed(e->{
			Sound.click().play();
		});
		
		ship3.setOnMouseEntered(e->ship3.setImage(UserInterfaceElements.getShip3Hover()));
		ship3.setOnMouseExited(e->ship3.setImage(UserInterfaceElements.getShip3Normal()));
		ship3.setOnMouseReleased(e->{
			shipNum =3;
			System.out.println(shipNum);
			ship3.setImage(UserInterfaceElements.getShip3Clicked());
			ship1.setImage(UserInterfaceElements.getShip1Normal());
			ship2.setImage(UserInterfaceElements.getShip2Normal());
			ship4.setImage(UserInterfaceElements.getShip4Normal());
		});
		ship4.setOnMousePressed(e->{
			Sound.click().play();
		});
		ship4.setOnMouseEntered(e->ship4.setImage(UserInterfaceElements.getShip4Hover()));
		ship4.setOnMouseExited(e->ship4.setImage(UserInterfaceElements.getShip4Normal()));
		ship4.setOnMouseReleased(e->{
			shipNum =4;
			System.out.println(shipNum);
			ship4.setImage(UserInterfaceElements.getShip4Clicked());
			ship1.setImage(UserInterfaceElements.getShip1Normal());
			ship2.setImage(UserInterfaceElements.getShip2Normal());
			ship3.setImage(UserInterfaceElements.getShip3Normal());
			
		});		
		hbox2.getChildren().addAll(ship3,ship4);
		ImageView view1= UserInterfaceElements.getStartGame();


		Sound.music().stop();


		view1.setOnMouseReleased(e->{
			gameWrapper = new SinglePlayerGameWrapper(this);
			window.setScene(gameWrapper.getGame().getScene());
		});


		VBox vbox=new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(Choose_Ship,hbox1,hbox2,view1);
		vbox.setBackground(BackgroundgifClass.getBackground());
		ChooseShip=new Scene(vbox,500,700);		
		return ChooseShip;
	}
	public void closeProgram() {
		boolean answer=Box.displayexit("Exit", "Are you sure, you want to Exit?");
		if(answer) {
			window.close();
		}
	}

	public Stage getWindow()
	{
		return window;
	}
}

class StartGameEventHandler implements EventHandler<ActionEvent>
{
	private MainMenu menu;
	public StartGameEventHandler(MainMenu menu)
	{
		this.menu = menu;
	}

	@Override
	public void handle(ActionEvent actionEvent)
	{
            SinglePlayerGameWrapper wrappedGame = new SinglePlayerGameWrapper(menu);
            menu.window.setScene(wrappedGame.getGame().getScene());
	}
}