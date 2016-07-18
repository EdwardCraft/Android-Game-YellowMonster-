package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.mygdx.game.screens.DifficultyScreen;
import com.mygdx.game.util.Constants.Difficulty;


public class DogeMania extends Game {



	@Override
	public void create() {
		showDifficultyScreen();
	}

	public void showDifficultyScreen(){
		setScreen(new DifficultyScreen(this));
	}

	public void showDogeScreen(Difficulty difficulty){
		setScreen(new DogeGameScreen(this, difficulty));
	}
}
