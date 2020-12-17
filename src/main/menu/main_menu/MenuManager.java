package main.menu.main_menu;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.GlobalConfig;
import widget.MenuButton;

// TODO use preloading for load game

public class MenuManager
{
	private static final GlobalConfig config;
	private static final AudioClip menuBackgroundMusic;
	private final Stage window;
	private final StackPane pane;
	private final StackPane animatedBackground;
	private final Scene root;
	private int shipNum = 1;
	private static final double scale =0.65;
	public int getShipNum()
	{
		return shipNum;
	}
	public void setShipNum(int shipNum)
	{
		this.shipNum = shipNum;
	}
	private String playerName;

	public String getPlayerName()
	{
		return playerName;
	}

	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	static
	{
		config = new GlobalConfig();
		menuBackgroundMusic = new AudioClip("file:res/sound/Game_Menu.mp3");
		menuBackgroundMusic.setCycleCount(AudioClip.INDEFINITE);
	}

	public static AudioClip getMenuBackgroundMusic()
	{
		return menuBackgroundMusic;
	}

	public Scene getRoot()
	{
		return root;
	}

	public MenuManager(Stage window)
	{
		this.window = window;


		//init pane
		pane = new StackPane();
		pane.setAlignment(Pos.CENTER);

		//init background pane
		animatedBackground = new StackPane();
		animatedBackground.setBackground(MenuBackground.getBackground());


		//add to pane
		pane.getChildren().add(animatedBackground);

		//init root

		root= new Scene(pane, config.getSCREEN_WIDTH(), config.getSCREEN_HEIGHT());
		//set cursor
		Image cursorImage = new Image("file:res/img/ui_elements/cursor_arrow.png");
		ImageCursor cursor = new ImageCursor(cursorImage);
		this.root.setCursor(cursor);


		//initially in main menu
		enterMainMenu();

	}

	public Node getMainMenu() {


		//init Title
		Image gameTitleImage = new Image("file:res/img/ui_elements/game_title.png");
		ImageView gameTitle = new ImageView(gameTitleImage);
		gameTitle.setScaleX(0.85);
		gameTitle.setScaleY(0.85);
		gameTitle.setTranslateY(-40);

		//init title animation
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(2500), gameTitle);

		translateTransition.setByY(10);
		translateTransition.setCycleCount(Animation.INDEFINITE);
		translateTransition.setAutoReverse(true);
		translateTransition.play();


		Label label1=new Label("Allegiance");
		label1.setFont(Font.loadFont("file:res/font/AlphaCentauri500.ttf", 52));
		label1.setTextFill(Color.web("#d5d0d2"));
		MenuButton new_Game=new MenuButton(e->newGame());
		new_Game .setNormalImageString("file:res/img/ui_elements/new_game_blue.png");
		new_Game.setHoverImageString("file:res/img/ui_elements/new_game_yellow.png");
		new_Game.setClickImageString("file:res/img/ui_elements/new_game_orange.png");
		new_Game.loadImages();
		new_Game.getButton().setScaleX(scale);
		new_Game.getButton().setScaleY(scale);

		//Button2
		MenuButton load_Game=new MenuButton(e->loadGame());
		load_Game .setNormalImageString("file:res/img/ui_elements/load_game_blue.png");
		load_Game.setHoverImageString("file:res/img/ui_elements/load_game_yellow.png");
		load_Game.setClickImageString("file:res/img/ui_elements/load_game_orange.png");
		load_Game.loadImages();
		load_Game.getButton().setScaleX(scale);
		load_Game.getButton().setScaleY(scale);
		//Button3
		MenuButton leaderBoard=new MenuButton(e->leaderBoards());
		leaderBoard.setNormalImageString("file:res/img/ui_elements/leaderboards_blue.png");
		leaderBoard.setHoverImageString("file:res/img/ui_elements/leaderboards_yellow.png");
		leaderBoard.setClickImageString("file:res/img/ui_elements/leaderboards_orange.png");
		leaderBoard.loadImages();
		leaderBoard.getButton().setScaleX(scale);
		leaderBoard.getButton().setScaleY(scale);

		//Button4

		MenuButton settings =new MenuButton(e ->settings());
		settings.setNormalImageString("file:res/img/ui_elements/settings_blue.png");
		settings.setHoverImageString("file:res/img/ui_elements/settings_yellow.png");
		settings.setClickImageString("file:res/img/ui_elements/settings_orange.png");
		settings.loadImages();
		settings.getButton().setScaleX(scale);
		settings.getButton().setScaleY(scale);
		//Button5
		MenuButton Help=new MenuButton(e -> help());
		Help.setNormalImageString("file:res/img/ui_elements/help_blue.png");
		Help.setHoverImageString("file:res/img/ui_elements/help_yellow.png");
		Help.setClickImageString("file:res/img/ui_elements/help_orange.png");
		Help.loadImages();
		Help.getButton().setScaleX(scale);
		Help.getButton().setScaleY(scale);

		//Button6
		MenuButton Exit=new MenuButton(e -> quit());
		Exit.setNormalImageString("file:res/img/ui_elements/exit_blue.png");
		Exit.setHoverImageString("file:res/img/ui_elements/exit_yellow.png");
		Exit.setClickImageString("file:res/img/ui_elements/exit_orange.png");
		Exit.loadImages();
		Exit.getButton().setScaleX(scale);
		Exit.getButton().setScaleY(scale);

		// Layout1

		VBox layout1=new VBox(-10);
		layout1.getChildren().addAll(gameTitle,new_Game.getButton(),load_Game.getButton(),
				leaderBoard.getButton(), settings.getButton(),Help.getButton(),Exit.getButton());
		layout1.setAlignment(Pos.CENTER);
		label1.setTranslateY(75);
		return layout1;
	}


	public void help()
	{
		HelpMenu helpMenu = new HelpMenu(f->enterMainMenu());
		clearStackPane();
		pane.getChildren().add(helpMenu.getlayer());
	}

	public void enterMainMenu()
	{
		clearStackPane();
		pane.getChildren().add(getMainMenu());
	}

	public void newGame()
	{

		clearStackPane();
		ShipMenu shipMenu = new ShipMenu(this);
		pane.getChildren().add(shipMenu.getLayer());

	}

	public void settings()
	{
		SettingsMenu settingsMenu = new SettingsMenu(e->enterMainMenu(), e->{
			if(GlobalConfig.isSoundOn())
			{
				GlobalConfig.setSoundOn(false);
				menuBackgroundMusic.stop();
			}
			else
			{
				GlobalConfig.setSoundOn(true);
				menuBackgroundMusic.play();
			}
		});//toggle sound
		clearStackPane();
		pane.getChildren().add(settingsMenu.getLayer());
	}

	public void enterName()
	{
		NameInputMenu nameInputMenu = new NameInputMenu(this);
		clearStackPane();
		pane.getChildren().add(nameInputMenu.getLayer());
	}

	public void loadGame()
	{
		//to be done by aniansh
		clearStackPane();
		LoadGameMenu loadGameMenu = new LoadGameMenu(this);
		pane.getChildren().add(loadGameMenu.getLayer());
	}

	public void quit()
	{
		window.close();
	}

	public void leaderBoards()
	{
		//to be done by aniansh
		clearStackPane();
		LeaderBoardMenu leaderBoardMenu = new LeaderBoardMenu(e->enterMainMenu());
		pane.getChildren().add(leaderBoardMenu.getLayer());
	}


	private void clearStackPane()
	{
		int size = pane.getChildren().size();
		for(int i=1; i<size; i++) // remove everything except background
		{
			pane.getChildren().remove(i);
		}
	}

	public Stage getWindow()
	{
		return window;
	}
}

