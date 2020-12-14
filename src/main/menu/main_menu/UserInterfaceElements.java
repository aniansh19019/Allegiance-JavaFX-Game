package main.menu.main_menu;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import widget.MenuButton;

public class UserInterfaceElements
{
	private static double scale =0.6;
	public UserInterfaceElements() {
		
	}
	public  ImageView getBack() {
		Image image7 = new Image("file:res/img/ui_elements/back_blue.png");
		Image image_7 =new Image("file:res/img/ui_elements/back_yellow.png");
		Image imageclicked7=new Image("file:res/img/ui_elements/back_orange.png");
        ImageView view7=new ImageView(image7);
        view7.setOnMousePressed(e->{
        	view7.setImage(imageclicked7);
        });
		view7.setOnMouseEntered(e->view7.setImage(image_7));
        view7.setOnMouseExited(e->view7.setImage(image7));
        view7.setFitWidth(40);
        view7.setFitHeight(40);
        return view7;
	}

	public  ImageView getSinglePlayer() {
		Image image8 = new Image("file:res/img/ui_elements/single_player_blue.png");
		Image image_8 =new Image("file:res/img/ui_elements/single_player_yellow.png");
		Image imageclicked8=new Image("file:res/img/ui_elements/single_player_orange.png");
        ImageView SinglePlayer=new ImageView(image8);
        SinglePlayer.setFitHeight(35);
        SinglePlayer.setFitWidth(130);
        SinglePlayer.setOnMousePressed(e->{
        	SinglePlayer.setImage(imageclicked8);
        	});
		SinglePlayer.setOnMouseEntered(e->SinglePlayer.setImage(image_8));
        SinglePlayer.setOnMouseExited(e->SinglePlayer.setImage(image8));
		return SinglePlayer;
	}

	public ImageView getStartGame() {
		Image image1 = new Image("file:res/img/ui_elements/start_game_blue.png");
		Image image_1 =new Image("file:res/img/ui_elements/start_game_yellow.png");
		Image imageclicked1=new Image("file:res/img/ui_elements/start_game_orange.png");
		ImageView StartGame=new ImageView(image1);
		StartGame.setOnMousePressed(e-> {
			StartGame.setImage(imageclicked1);
		});
		StartGame.setOnMouseEntered(e->StartGame.setImage(image_1));
		StartGame.setOnMouseExited(e->StartGame.setImage(image1));
        return StartGame;
	}
	public ImageView getChooseShip() {
	    Image image2 = new Image("file:res/img/ui_elements/choose_ship_blue.png");
		Image image_2 =new Image("file:res/img/ui_elements/choose_ship_yellow.png");
		Image imageclicked2=new Image("file:res/img/ui_elements/choose_ship_orange.png");
		ImageView view2=new ImageView(image2);
		view2.setOnMousePressed(e->{
			view2.setImage(imageclicked2);
		});
		view2.setOnMouseEntered(e->view2.setImage(image_2));
        view2.setOnMouseExited(e->view2.setImage(image2));
        return view2;
	}
	public ImageView getChoose() {
		Image image8 = new Image("file:res/img/ui_elements/choose.png");
        ImageView Choose=new ImageView(image8);
		return Choose;
	}
	public ImageView getSound() {
		Image image8 = new Image("file:res/img/ui_elements/sound_setting.png");
        ImageView Sound=new ImageView(image8);
		return Sound;
	}
	public Node getHelpInfo() {
		Image image8 = new Image("file:res/img/ui_elements/instructions_box.png");
        ImageView HelpInfo=new ImageView(image8);
        return HelpInfo;
	}
	public ImageView getShip1() {
		Image image2 = new Image("file:res/img/ui_elements/ship_1_normal.png");
		Image image_2 =new Image("file:res/img/ui_elements/ship_1_hover.png");
		Image imageclicked2=new Image("file:res/img/ui_elements/ship_1_click.png");
		ImageView ship1=new ImageView(image2);
		ship1.setOnMousePressed(e->{
			ship1.setImage(imageclicked2);
		});
		ship1.setOnMouseEntered(e->ship1.setImage(image_2));
		ship1.setOnMouseExited(e->ship1.setImage(image2));
        return ship1;
	}
	public ImageView getShip2() {
		Image image2 = new Image("file:res/img/ui_elements/ship_2_normal.png");
		Image image_2 =new Image("file:res/img/ui_elements/ship_2_hover.png");
		Image imageclicked2=new Image("file:res/img/ui_elements/ship_2_click.png");
		ImageView ship2=new ImageView(image2);
		ship2.setOnMousePressed(e->{
			ship2.setImage(imageclicked2);
		});
		ship2.setOnMouseEntered(e->ship2.setImage(image_2));
		ship2.setOnMouseExited(e->ship2.setImage(image2));
        return ship2;
	}
	public ImageView getShip3() {
		Image image2 = new Image("file:res/img/ui_elements/ship_3_normal.png");
		Image image_2 =new Image("file:res/img/ui_elements/ship_3_hover.png");
		Image imageclicked2=new Image("file:res/img/ui_elements/ship_3_click.png");
		ImageView ship3=new ImageView(image2);
		ship3.setOnMousePressed(e->{
			ship3.setImage(imageclicked2);
		});
		ship3.setOnMouseEntered(e->ship3.setImage(image_2));
		ship3.setOnMouseExited(e->ship3.setImage(image2));
        return ship3;
	}
	public ImageView getShip4() {
		Image image2 = new Image("file:res/img/ui_elements/ship_4_normal.png");
		Image image_2 =new Image("file:res/img/ui_elements/ship_4_hover.png");
		Image imageclicked2=new Image("file:res/img/ui_elements/ship_4_click.png");
		ImageView ship4=new ImageView(image2);
		ship4.setOnMousePressed(e->{

			ship4.setImage(imageclicked2);
		});
		ship4.setOnMouseEntered(e->ship4.setImage(image_2));
		ship4.setOnMouseExited(e->ship4.setImage(image2));
        return ship4;
	}
	public Image getShip1Normal() {
		Image Ship1_normal = new Image("file:res/img/ui_elements/ship_1_normal.png");
		return Ship1_normal;
	}
	public Image getShip1Hover() {
		Image Ship1_hover= new Image("file:res/img/ui_elements/ship_1_hover.png");
		return Ship1_hover;
	}
	public Image getShip1Clicked() {
		Image Ship1_click = new Image("file:res/img/ui_elements/ship_1_click.png");
		return Ship1_click;
	}
	public Image getShip2Normal() {
		Image Ship1_normal = new Image("file:res/img/ui_elements/ship_2_normal.png");
		return Ship1_normal;
	}
	public Image getShip2Hover() {
		Image Ship1_hover= new Image("file:res/img/ui_elements/ship_2_hover.png");
		return Ship1_hover;
	}
	public Image getShip2Clicked() {
		Image Ship1_click = new Image("file:res/img/ui_elements/ship_2_click.png");
		return Ship1_click;
	}
	public Image getShip3Normal() {
		Image Ship1_normal = new Image("file:res/img/ui_elements/ship_3_normal.png");
		return Ship1_normal;
	}
	public Image getShip3Hover() {
		Image Ship1_hover= new Image("file:res/img/ui_elements/ship_3_hover.png");
		return Ship1_hover;
	}
	public Image getShip3Clicked() {
		Image Ship1_click = new Image("file:res/img/ui_elements/ship_3_click.png");
		return Ship1_click;
	}
	public Image getShip4Normal() {
		Image Ship1_normal = new Image("file:res/img/ui_elements/ship_4_normal.png");
		return Ship1_normal;
	}
	public Image getShip4Hover() {
		Image Ship1_hover= new Image("file:res/img/ui_elements/ship_4_hover.png");
		return Ship1_hover;
	}
	public Image getShip4Clicked() {
		Image Ship1_click = new Image("file:res/img/ui_elements/ship_4_click.png");
		return Ship1_click;
	}
	public Image getUncheckedBox() {
		Image box = new Image("file:res/img/ui_elements/checkbox_unchecked_yellow.png");
		return box;
	}
	public Image getCheckedBox() {
		Image box = new Image("file:res/img/ui_elements/checkbox_checked_yellow.png");
		return box;
	}
	public ImageView getChoose_Ship() {
		Image image8 = new Image("file:res/img/ui_elements/choose_ship.png");
        ImageView Choose=new ImageView(image8);
		return Choose;
	}
	public Node getInstruction() {
		Image image8 = new Image("file:res/img/ui_elements/instructions_label.png");
        ImageView Instruction=new ImageView(image8);
		return Instruction;
		
	}
}

