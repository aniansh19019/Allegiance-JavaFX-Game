package main.menus.main_menu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Box {

	static boolean answer;
	public static boolean displayexit(String title, String message) {
		Stage window=new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(300);
		Label label1=new Label();
		label1.setText(message);
		//Create buttons
		Button Yesbutton=new Button("Yes");
		Button Nobutton=new Button("No");
		Yesbutton.setOnAction(e->{
			answer=true;
			window.close();
		});
		Nobutton.setOnAction(e->{
			answer=false;
			window.close();
		});
		VBox layout=new VBox(10);
		layout.getChildren().addAll(label1,Yesbutton,Nobutton);
		layout.setAlignment(Pos.CENTER);
		Scene scene =new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		return answer;
		
		
		
	}

}
