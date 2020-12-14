package main.menu.main_menu;

import javafx.scene.media.AudioClip;

public class Sound {
	public static AudioClip music() {
		AudioClip clip=new AudioClip("file:res/sound/Game_Menu.mp3");
		clip.setCycleCount(AudioClip.INDEFINITE);
		return clip;	
		
	}
	public static AudioClip check() {
		AudioClip clip=new AudioClip("file:res/sound/click.mp3");
		return clip;
	}
	public static AudioClip click() {
		AudioClip clip=new AudioClip("file:res/sound/click.mp3");
		return clip;
	}

	public static AudioClip hover() {
		AudioClip clip=new AudioClip("file:res/sound/hover.mp3");
		return clip;
	}
}
