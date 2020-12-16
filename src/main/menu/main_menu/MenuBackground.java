package main.menu.main_menu;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class MenuBackground
{
	public static Background getBackground() {
		Image image=new Image("file:res/img/background/animated_background.gif");
		BackgroundImage background=new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background xx=new Background(background);
		return xx;
	}
	

}
