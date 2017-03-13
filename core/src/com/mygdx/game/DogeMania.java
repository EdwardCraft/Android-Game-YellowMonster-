package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.game.screens.DifficultyScreen;
import com.mygdx.game.util.AdHandler;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Constants.Difficulty;


public class DogeMania extends Game {

	public Preferences save;
	public AdHandler handler;

	public DogeMania(){

	}

	public DogeMania(AdHandler handler){
		this.handler = handler;
		handler.showAds(false);
	}

	@Override
	public void create() {
		save = Gdx.app.getPreferences(Constants.PREF_NAME);
		showDifficultyScreen();
	}

	public void showDifficultyScreen(){
		setScreen(new DifficultyScreen(this));
	}

	public void showDogeScreen(Difficulty difficulty){
		setScreen(new DogeGameScreen(this, difficulty));
	}


}
