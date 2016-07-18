package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.overlays.ChunHud;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Constants.Difficulty;
import com.mygdx.game.util.Enums.JumpState;

import java.io.DataOutputStream;


/**
 * Created by PEDRO on 20/01/2016.
 */
public class DogeGameScreen extends InputAdapter implements Screen {
    public static final String TAG = DataOutputStream.class.getSimpleName();

    Difficulty difficulty;
    DogeMania dogeMania;
    SpriteBatch batch;
    ExtendViewport viewport;
    Level level;
    ExtendViewport hudViewPort;
    BitmapFont font;
    ChunHud chunHud;


    public DogeGameScreen(DogeMania dogeMania, Difficulty difficulty){
        this.difficulty = difficulty;
        this.dogeMania = dogeMania;
    }

    @Override
    public void show () {
        batch = new SpriteBatch();
        hudViewPort = new  ExtendViewport(Constants.HUD_FONT_REFERENCE_SCREEN_SIZE, Constants.HUD_FONT_REFERENCE_SCREEN_SIZE);
        font = new BitmapFont(Gdx.files.internal("sprites/font-1-export.fnt"));
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        chunHud = new ChunHud(hudViewPort,font);
        viewport = new ExtendViewport(Constants.WOLRD_SIZE ,Constants.WOLRD_SIZE);
        level = new Level(viewport,difficulty);
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render (float delta) {

        level.update(delta);
        viewport.apply();
        Gdx.gl.glClearColor(
                Constants.BACKGRAOUND_COLOR.r,
                Constants.BACKGRAOUND_COLOR.g,
                Constants.BACKGRAOUND_COLOR.b,
                Constants.BACKGRAOUND_COLOR.a
        );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        level.render(batch);
        batch.end();

        hudViewPort.apply();
        batch.begin();
        batch.setProjectionMatrix(hudViewPort.getCamera().combined);
        chunHud.render(batch, level.getChun().getHits(), level.chun.getScore());
        batch.end();

        if(level.getChun().getHits() == 0){
            dogeMania.showDifficultyScreen();
        }
    }

    @Override
    public void resize (int width, int height) {

        viewport.update(width, height, true);
        hudViewPort.update(width, height, true);
        font.getData().setScale(Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_SIZE);

    }

    @Override
    public void hide () {
        batch.dispose();
        font.dispose();
        level.dispose();
        //level.getChun().dispose();
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

    @Override
    public void dispose () {
        batch.dispose();
        font.dispose();
        level.dispose();
        //level.getChun().dispose();
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        dogeMania.showDifficultyScreen();
        return true;
    }


}
