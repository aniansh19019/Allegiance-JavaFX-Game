package main.menu.main_menu;

import javafx.scene.media.AudioClip;

public class Sound {
	private String musicClip;
	private String checkClip;
	private String clickClip;
	private String hoverClip;

	public Sound(){
		musicClip="file:res/sound/Game_Menu.mp3";
		checkClip="file:res/sound/click.mp3";
		clickClip="file:res/sound/click.mp3";
		hoverClip="file:res/sound/hover.mp3";
	}

	public AudioClip music() {
		AudioClip clip=new AudioClip(musicClip);
		clip.setCycleCount(AudioClip.INDEFINITE);
		return clip;	
		
	}
	public AudioClip check() {
		AudioClip clip=new AudioClip(checkClip);
		return clip;
	}
	public AudioClip click() {
		AudioClip clip=new AudioClip(clickClip);
		return clip;
	}

	public AudioClip hover() {
		AudioClip clip=new AudioClip(hoverClip);
		return clip;
	}
}
