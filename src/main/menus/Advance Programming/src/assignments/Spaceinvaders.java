package assignments;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

@SuppressWarnings("static-access")
public class Spaceinvaders extends Application  {
	
	Stage window;
	Scene MainMenu, newGame, loadGame,Leaderboard,settings,help,SingleIntermediate,TwoIntermediate,
	SingleGameplay,TwoGameplay,SingleSaved,TwoSaved,Control,splashscreen,pauseMenu,SavedIntermediate,ChooseShip;
	StackPane pane;
	int ship_num=1;
	public StackPane Pane() {
		pane=new StackPane();
		pane.setBackground(BackgroundgifClass.getBackground());
		return pane;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
		

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		window=stage;
		window.setScene(getSplashScreen());	
		window.setTitle("Allegiance");
		window.show();
		
		
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
	    TwoIntermediate.getStylesheets().add("sample.css");
	    return TwoIntermediate;
	}
	public Scene getSettings() {
		ImageView back=ui_elements.getBack();
		back.setOnMouseReleased(e->{
			window.setScene(getMainMenu());
		});
		ImageView Sound_=ui_elements.getSound();
		ImageView SoundOnBox=new ImageView(ui_elements.getCheckedBox());		
		ImageView SoundOffBox=new ImageView(ui_elements.getUncheckedBox());
		SoundOffBox.setOnMouseClicked(e->{
			Sound.check().play();
			SoundOffBox.setImage(ui_elements.getCheckedBox());
			SoundOnBox.setImage(ui_elements.getUncheckedBox());
			Sound.music().stop();
			
		});
		SoundOnBox.setOnMouseClicked(e->{
			Sound.check().play();
			SoundOnBox.setImage(ui_elements.getCheckedBox());
			SoundOffBox.setImage(ui_elements.getUncheckedBox());
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
	    settings.getStylesheets().add("sample.css");
		return settings;
	}
	public Scene getSingleGameplay() {		
		ImageView view1=ui_elements.getPause();
		view1.setOnMouseReleased(e->{
			window.setScene(getPause());
		});
	    VBox layout7=new VBox(10);
	    layout7.getChildren().add(view1);
	    FirstApp game = new FirstApp();
	    try {
			SingleGameplay=game.startGame(view1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    SingleGameplay.getStylesheets().add("sample.css");
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
		label1.setFont(Font.loadFont("file:res/AlphaCentauri500.ttf", 52));
		label1.setTextFill(Color.web("#d5d0d2"));
		ImageView New_game=ui_elements.getNewGame();
		New_game.setOnMouseReleased(e->{
			window.setScene(getControls());
		});
		//Button2
        ImageView Load_game =ui_elements.getLoadGame();
        Load_game.setOnMouseReleased(e->{
        	window.setScene(getLoadGame());
        });
		//Button3
        ImageView view3=ui_elements.getLeaderBoard();		
        view3.setOnMouseReleased(e -> {
			window.setScene(Leaderboard);			
		});
		//Button4
		ImageView Setting_s=ui_elements.getSettings();
		Setting_s.setOnMouseReleased(e -> 
		{
			window.setScene(getSettings());
		});
		//Button5
		ImageView Help=ui_elements.getHelp();
		Help.setOnMouseReleased(e -> 
		{	
			window.setScene(getHelp());
			});
		//Button6
        ImageView Exit=ui_elements.getExit();
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
		MainMenu.getStylesheets().add("sample.css");
		return MainMenu;
	}
	public Scene getLoadGame() {
		BorderPane pane=new BorderPane();
        ImageView Back=ui_elements.getBack();
		Back.setOnMouseReleased(e->{
			window.setScene(getMainMenu());
		});
        ImageView SinglePlayer=ui_elements.getSinglePlayer();
		SinglePlayer.setOnMouseReleased(e->{
			window.setScene(SingleSaved);
		});
        ImageView TwoPlayer=ui_elements.getTwoPlayer();
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
		loadGame.getStylesheets().add("sample.css");
		return loadGame;
	}
	public Scene getNewGame() {
		BorderPane pane=new BorderPane();
        ImageView Back=ui_elements.getBack();
		Back.setOnMouseReleased(e->{
			window.setScene(getMainMenu());
		});
        ImageView SinglePlayer=ui_elements.getSinglePlayer();
		SinglePlayer.setOnMouseReleased(e->{
			window.setScene(getControls());
		});
        ImageView TwoPlayer=ui_elements.getTwoPlayer();
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
		newGame.getStylesheets().add("sample.css");
		return newGame;
	}
	public Scene getControls() {
		ImageView view=ui_elements.getHelpInfo();
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
		ImageView Back=ui_elements.getBack();
		Back.setOnMouseReleased(e->{
			window.setScene(getNewGame());
		});
		VBox layout5=new VBox(10);
		ImageView view1=ui_elements.getStartGame();
		view1.setOnMouseReleased(e->{
			window.setScene(SingleGameplay);
		});
		ImageView view2=ui_elements.getChooseShip();
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
		label.setFont(Font.loadFont("file:res/AlphaCentauri500.ttf", 52));
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
        ImageView Back=ui_elements.getBack();
		Back.setOnMouseReleased(e->{
			window.setScene(getMainMenu());
		});
        ImageView SinglePlayer=ui_elements.getSinglePlayer();
		SinglePlayer.setOnMouseReleased(e->{
			window.setScene(SingleSaved);
		});
        ImageView TwoPlayer=ui_elements.getTwoPlayer();
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
		SavedIntermediate.getStylesheets().add("sample.css");
		return SavedIntermediate;
	}
	public Scene getHelp() {
		BorderPane pane=new BorderPane();
		ImageView Back=ui_elements.getBack();
		pane.setLeft(Back);
		Back.setOnMouseReleased(e->window.setScene(getMainMenu()));
		VBox box=new VBox(30);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(ui_elements.getInstruction(),ui_elements.getHelpInfo());
		pane.setCenter(box);
		pane.setRight(new Label("           "));
		pane.setBackground(BackgroundgifClass.getBackground());
		help=new Scene(pane,500,700);		
		return help;
	}
	public Scene getChooseShip() {
		ImageView Choose_Ship=ui_elements.getChoose_Ship();
		HBox hbox1=new HBox(10);
		hbox1.setAlignment(Pos.CENTER);
		ImageView ship1=new ImageView(ui_elements.getShip1Clicked());
		ImageView ship2=new ImageView(ui_elements.getShip2Normal());
		ImageView ship3=new ImageView(ui_elements.getShip3Normal());
		ImageView ship4=new ImageView(ui_elements.getShip4Normal());
		ship1.setOnMousePressed(e->{
			Sound.click().play();
		});
		ship1.setOnMouseEntered(e->ship1.setImage(ui_elements.getShip1Hover()));
		ship1.setOnMouseExited(e->ship1.setImage(ui_elements.getShip1Normal()));
		ship1.setOnMouseReleased(e->{
			ship_num=1;
			System.out.println(ship_num);
			ship1.setImage(ui_elements.getShip1Clicked());
			ship2.setImage(ui_elements.getShip2Normal());
			ship3.setImage(ui_elements.getShip3Normal());
			ship4.setImage(ui_elements.getShip4Normal());
		});
		
		ship2.setOnMousePressed(e->{
			Sound.click().play();			
		});
		ship2.setOnMouseEntered(e->ship2.setImage(ui_elements.getShip2Hover()));
		ship2.setOnMouseExited(e->ship2.setImage(ui_elements.getShip2Normal()));
		ship2.setOnMouseReleased(e->{
			ship_num=2;
			System.out.println(ship_num);
			ship2.setImage(ui_elements.getShip2Clicked());
			ship1.setImage(ui_elements.getShip1Normal());			
			ship3.setImage(ui_elements.getShip3Normal());
			ship4.setImage(ui_elements.getShip4Normal());
		});
		hbox1.getChildren().addAll(ship1,ship2);
		HBox hbox2=new HBox(10);
		hbox2.setAlignment(Pos.CENTER);
		ship3.setOnMousePressed(e->{
			Sound.click().play();
		});
		
		ship3.setOnMouseEntered(e->ship3.setImage(ui_elements.getShip3Hover()));
		ship3.setOnMouseExited(e->ship3.setImage(ui_elements.getShip3Normal()));
		ship3.setOnMouseReleased(e->{
			ship_num=3;
			System.out.println(ship_num);
			ship3.setImage(ui_elements.getShip3Clicked());
			ship1.setImage(ui_elements.getShip1Normal());
			ship2.setImage(ui_elements.getShip2Normal());
			ship4.setImage(ui_elements.getShip4Normal());			
		});
		ship4.setOnMousePressed(e->{
			Sound.click().play();
		});
		ship4.setOnMouseEntered(e->ship4.setImage(ui_elements.getShip4Hover()));
		ship4.setOnMouseExited(e->ship4.setImage(ui_elements.getShip4Normal()));
		ship4.setOnMouseReleased(e->{
			ship_num=4;
			System.out.println(ship_num);
			ship4.setImage(ui_elements.getShip4Clicked());
			ship1.setImage(ui_elements.getShip1Normal());
			ship2.setImage(ui_elements.getShip2Normal());
			ship3.setImage(ui_elements.getShip3Normal());
			
		});		
		hbox2.getChildren().addAll(ship3,ship4);
		ImageView view1=ui_elements.getStartGame();
		view1.setOnMouseReleased(e->{
			window.setScene(SingleGameplay);
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


}