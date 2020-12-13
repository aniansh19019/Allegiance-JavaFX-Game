package main.menus.main_menu;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserInterfaceElements
{
	public UserInterfaceElements() {
		
	}
	public static ImageView getBack() {
		Image image7 = new Image("file:res/img/ui_elements/back_blue.png");
		Image image_7 =new Image("file:res/img/ui_elements/back_yellow.png");
		Image imageclicked7=new Image("file:res/img/ui_elements/back_orange.png");
        ImageView view7=new ImageView(image7);
        view7.setOnMousePressed(e->{
        	Sound.click().play();
        	view7.setImage(imageclicked7);
        });
		view7.setOnMouseEntered(e->view7.setImage(image_7));
        view7.setOnMouseExited(e->view7.setImage(image7));
        view7.setFitWidth(40);
        view7.setFitHeight(40);
        return view7;
	}
	public static ImageView getPause() {
		Image image1 = new Image("file:res/img/ui_elements/pause_blue.png");
		Image image_1 =new Image("file:res/img/ui_elements/pause_yellow.png");
		Image imageclicked1=new Image("file:res/img/ui_elements/pause_orange.png");
		ImageView pause=new ImageView(image1);
		pause.setOnMousePressed(e->{
			Sound.click().play();
			pause.setImage(imageclicked1);
		});
		pause.setOnMouseEntered(e->pause.setImage(image_1));
		pause.setOnMouseExited(e->pause.setImage(image1));
		return pause;
	}
	public static ImageView getNewGame() {
		Image image1 = new Image("file:res/img/ui_elements/new_game_blue.png");
		Image image_1 =new Image("file:res/img/ui_elements/new_game_yellow.png");
		Image imageclicked1=new Image("file:res/img/ui_elements/new_game_orange.png");
		ImageView NewGame=new ImageView(image1);
		NewGame.setOnMousePressed(e->{
			Sound.click().play();
			NewGame.setImage(imageclicked1);
		});
		NewGame.setOnMouseEntered(e->NewGame.setImage(image_1));
		NewGame.setOnMouseExited(e->NewGame.setImage(image1));
        return NewGame;
	}
	public static ImageView getLoadGame() {
		Image image1 = new Image("file:res/img/ui_elements/load_game_blue.png");
		Image image_1 =new Image("file:res/img/ui_elements/load_game_yellow.png");
		Image imageclicked1=new Image("file:res/img/ui_elements/load_game_orange.png");
        ImageView LoadGame =new ImageView(image1);
        LoadGame.setOnMousePressed(e->{
        	Sound.click().play();
        	LoadGame.setImage(imageclicked1);
        });
        LoadGame.setOnMouseEntered(e->LoadGame.setImage(image_1));
        LoadGame.setOnMouseExited(e->LoadGame.setImage(image1));
        return LoadGame;
	}
	public static ImageView getLeaderBoard() {
		Image image3 = new Image("file:res/img/ui_elements/leaderboards_blue.png");
		Image image_3 =new Image("file:res/img/ui_elements/leaderboards_yellow.png");
		Image imageclicked3=new Image("file:res/img/ui_elements/leaderboards_orange.png");
        ImageView LeaderBoard=new ImageView(image3);
        LeaderBoard.setOnMousePressed(e->{
        	Sound.click().play();
        	LeaderBoard.setImage(imageclicked3);
        });
        LeaderBoard.setOnMouseEntered(e->LeaderBoard.setImage(image_3));
        LeaderBoard.setOnMouseExited(e->LeaderBoard.setImage(image3));
		return LeaderBoard;
	}
	public static ImageView getSettings() {
		Image image4 = new Image("file:res/img/ui_elements/settings_blue.png");
		Image image_4 =new Image("file:res/img/ui_elements/settings_yellow.png");
		Image imageclicked4=new Image("file:res/img/ui_elements/settings_orange.png");
		ImageView Settings=new ImageView(image4);
		Settings.setOnMousePressed(e->{
			Sound.click().play();
			Settings.setImage(imageclicked4);
		});
		Settings.setOnMouseEntered(e->Settings.setImage(image_4));
		Settings.setOnMouseExited(e->Settings.setImage(image4));
		return Settings;
	}
	public static ImageView getHelp() {
		Image image5 = new Image("file:res/img/ui_elements/help_blue.png");
		Image image_5 =new Image("file:res/img/ui_elements/help_yellow.png");
		Image imageclicked5=new Image("file:res/img/ui_elements/help_orange.png");
		ImageView help=new ImageView(image5);
		help.setOnMousePressed(e->{
			Sound.click().play();
			help.setImage(imageclicked5);
		});
		help.setOnMouseEntered(e->help.setImage(image_5));
        help.setOnMouseExited(e->help.setImage(image5));
		return help;
	}
	public static ImageView getExit() {
		Image image6 = new Image("file:res/img/ui_elements/exit_blue.png");
		Image image_6 =new Image("file:res/img/ui_elements/exit_yellow.png");
		Image imageclicked6=new Image("file:res/img/ui_elements/exit_orange.png");
        ImageView exit=new ImageView(image6);
        exit.setOnMousePressed(e->{
        	Sound.click().play();
        	exit.setImage(imageclicked6);
        });
        exit.setOnMouseEntered(e->exit.setImage(image_6));
        exit.setOnMouseExited(e->exit.setImage(image6));  
        return exit;
	}
	public static ImageView getSinglePlayer() {
		Image image8 = new Image("file:res/img/ui_elements/single_player_blue.png");
		Image image_8 =new Image("file:res/img/ui_elements/single_player_yellow.png");
		Image imageclicked8=new Image("file:res/img/ui_elements/single_player_orange.png");
        ImageView SinglePlayer=new ImageView(image8);
        SinglePlayer.setFitHeight(35);
        SinglePlayer.setFitWidth(130);
        SinglePlayer.setOnMousePressed(e->{
        	Sound.click().play();
        	SinglePlayer.setImage(imageclicked8);
        	});
		SinglePlayer.setOnMouseEntered(e->SinglePlayer.setImage(image_8));
        SinglePlayer.setOnMouseExited(e->SinglePlayer.setImage(image8));
		return SinglePlayer;
	}
	public static ImageView getTwoPlayer() {
		Image image9 = new Image("file:res/img/ui_elements/two_player_blue.png");
		Image image_9 =new Image("file:res/img/ui_elements/two_player_yellow.png");
		Image imageclicked9=new Image("file:res/img/ui_elements/two_player_orange.png");
        ImageView TwoPlayer=new ImageView(image9);
        TwoPlayer.setFitHeight(35);
        TwoPlayer.setFitWidth(130);
        TwoPlayer.setOnMousePressed(e->{
        	Sound.click().play();
        	TwoPlayer.setImage(imageclicked9);
        });
        TwoPlayer.setOnMouseEntered(e->TwoPlayer.setImage(image_9));
		TwoPlayer.setOnMouseExited(e->TwoPlayer.setImage(image9));
        return TwoPlayer;
	}
	public static ImageView getStartGame() {
		Image image1 = new Image("file:res/img/ui_elements/start_game_blue.png");
		Image image_1 =new Image("file:res/img/ui_elements/start_game_yellow.png");
		Image imageclicked1=new Image("file:res/img/ui_elements/start_game_orange.png");
		ImageView StartGame=new ImageView(image1);
		StartGame.setOnMousePressed(e-> {
			Sound.click().play();
			StartGame.setImage(imageclicked1);
		});
		StartGame.setOnMouseEntered(e->StartGame.setImage(image_1));
		StartGame.setOnMouseExited(e->StartGame.setImage(image1));
        return StartGame;
	}
	public static ImageView getChooseShip() {
	    Image image2 = new Image("file:res/img/ui_elements/choose_ship_blue.png");
		Image image_2 =new Image("file:res/img/ui_elements/choose_ship_yellow.png");
		Image imageclicked2=new Image("file:res/img/ui_elements/choose_ship_orange.png");
		ImageView view2=new ImageView(image2);
		view2.setOnMousePressed(e->{
			Sound.click().play();
			view2.setImage(imageclicked2);
		});
		view2.setOnMouseEntered(e->view2.setImage(image_2));
        view2.setOnMouseExited(e->view2.setImage(image2));
        return view2;
	}
	public static ImageView getChoose() {
		Image image8 = new Image("file:res/img/ui_elements/choose.png");
        ImageView Choose=new ImageView(image8);
		return Choose;
	}
	public static ImageView getSound() {
		Image image8 = new Image("file:res/img/ui_elements/sound_setting.png");
        ImageView Sound=new ImageView(image8);
		return Sound;
	}
	public static ImageView getHelpInfo() {
		Image image8 = new Image("file:res/img/ui_elements/instructions_box.png");
        ImageView HelpInfo=new ImageView(image8);
        return HelpInfo;
	}
	public static ImageView getShip1() {
		Image image2 = new Image("file:res/img/ui_elements/ship_1_normal.png");
		Image image_2 =new Image("file:res/img/ui_elements/ship_1_hover.png");
		Image imageclicked2=new Image("file:res/img/ui_elements/ship_1_click.png");
		ImageView ship1=new ImageView(image2);
		ship1.setOnMousePressed(e->{
			Sound.click().play();
			ship1.setImage(imageclicked2);
		});
		ship1.setOnMouseEntered(e->ship1.setImage(image_2));
		ship1.setOnMouseExited(e->ship1.setImage(image2));
        return ship1;
	}
	public static ImageView getShip2() {
		Image image2 = new Image("file:res/img/ui_elements/ship_2_normal.png");
		Image image_2 =new Image("file:res/img/ui_elements/ship_2_hover.png");
		Image imageclicked2=new Image("file:res/img/ui_elements/ship_2_click.png");
		ImageView ship2=new ImageView(image2);
		ship2.setOnMousePressed(e->{
			Sound.click().play();
			ship2.setImage(imageclicked2);
		});
		ship2.setOnMouseEntered(e->ship2.setImage(image_2));
		ship2.setOnMouseExited(e->ship2.setImage(image2));
        return ship2;
	}
	public static ImageView getShip3() {
		Image image2 = new Image("file:res/img/ui_elements/ship_3_normal.png");
		Image image_2 =new Image("file:res/img/ui_elements/ship_3_hover.png");
		Image imageclicked2=new Image("file:res/img/ui_elements/ship_3_click.png");
		ImageView ship3=new ImageView(image2);
		ship3.setOnMousePressed(e->{
			Sound.click().play();
			ship3.setImage(imageclicked2);
		});
		ship3.setOnMouseEntered(e->ship3.setImage(image_2));
		ship3.setOnMouseExited(e->ship3.setImage(image2));
        return ship3;
	}
	public static ImageView getShip4() {
		Image image2 = new Image("file:res/img/ui_elements/ship_4_normal.png");
		Image image_2 =new Image("file:res/img/ui_elements/ship_4_hover.png");
		Image imageclicked2=new Image("file:res/img/ui_elements/ship_4_click.png");
		ImageView ship4=new ImageView(image2);
		ship4.setOnMousePressed(e->{
			Sound.click().play();
			ship4.setImage(imageclicked2);
		});
		ship4.setOnMouseEntered(e->ship4.setImage(image_2));
		ship4.setOnMouseExited(e->ship4.setImage(image2));
        return ship4;
	}
	public static Image getShip1Normal() {
		Image Ship1_normal = new Image("file:res/img/ui_elements/ship_1_normal.png");
		return Ship1_normal;
	}
	public static Image getShip1Hover() {
		Image Ship1_hover= new Image("file:res/img/ui_elements/ship_1_hover.png");
		return Ship1_hover;
	}
	public static Image getShip1Clicked() {
		Image Ship1_click = new Image("file:res/img/ui_elements/ship_1_click.png");
		return Ship1_click;
	}
	public static Image getShip2Normal() {
		Image Ship1_normal = new Image("file:res/img/ui_elements/ship_2_normal.png");
		return Ship1_normal;
	}
	public static Image getShip2Hover() {
		Image Ship1_hover= new Image("file:res/img/ui_elements/ship_2_hover.png");
		return Ship1_hover;
	}
	public static Image getShip2Clicked() {
		Image Ship1_click = new Image("file:res/img/ui_elements/ship_2_click.png");
		return Ship1_click;
	}
	public static Image getShip3Normal() {
		Image Ship1_normal = new Image("file:res/img/ui_elements/ship_3_normal.png");
		return Ship1_normal;
	}
	public static Image getShip3Hover() {
		Image Ship1_hover= new Image("file:res/img/ui_elements/ship_3_hover.png");
		return Ship1_hover;
	}
	public static Image getShip3Clicked() {
		Image Ship1_click = new Image("file:res/img/ui_elements/ship_3_click.png");
		return Ship1_click;
	}
	public static Image getShip4Normal() {
		Image Ship1_normal = new Image("file:res/img/ui_elements/ship_4_normal.png");
		return Ship1_normal;
	}
	public static Image getShip4Hover() {
		Image Ship1_hover= new Image("file:res/img/ui_elements/ship_4_hover.png");
		return Ship1_hover;
	}
	public static Image getShip4Clicked() {
		Image Ship1_click = new Image("file:res/img/ui_elements/ship_4_click.png");
		return Ship1_click;
	}
	public static Image getUncheckedBox() {
		Image box = new Image("file:res/img/ui_elements/checkbox_unchecked_yellow.png");
		return box;
	}
	public static Image getCheckedBox() {
		Image box = new Image("file:res/img/ui_elements/checkbox_checked_yellow.png");
		return box;
	}
	public static ImageView getChoose_Ship() {
		Image image8 = new Image("file:res/img/ui_elements/choose_ship.png");
        ImageView Choose=new ImageView(image8);
		return Choose;
	}
	public static ImageView getInstruction() {
		Image image8 = new Image("file:res/img/ui_elements/instructions_label.png");
        ImageView Instruction=new ImageView(image8);
		return Instruction;
		
	}
}

