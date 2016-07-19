package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.entities.Chun;
import com.mygdx.game.entities.Coins;
import com.mygdx.game.entities.Enemies;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Enums.HIT;
import com.mygdx.game.util.Constants.Difficulty;


/**
 * Created by PEDRO on 20/01/2016.
 */
public class Level {

    Chun chun;
    Enemies enemies;
    Coins coins;
    Texture beta;
    Texture beta1;
    Texture iciclesNOanimation;
    Texture iciclesHitAnimation;
    Difficulty difficulty;

    public Level(ExtendViewport viewport,Difficulty difficulty){
        this.difficulty = difficulty;
        coins = new Coins(viewport,this);
        chun = new Chun(new Vector2(Constants.STARTING_POINT ,Constants.CHUN_EYE_HEIGHT), viewport,this);
        enemies = new Enemies();
        enemies.init(viewport, difficulty, this);
        beta = new Texture(Constants.BETA_LEVEL_1);
        beta.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        iciclesNOanimation = new Texture(Constants.ICICLES_SPRITE_1);
        iciclesNOanimation.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        iciclesHitAnimation = new Texture(Constants.ICICLES_SPRITE_2);
        iciclesHitAnimation.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void update(float delta){

        chun.update(delta);
        enemies.update(delta);
        coins.update(delta);

    }

    public void render(SpriteBatch batch){


        batch.draw(
                beta,
                0,
                0,
                Constants.BACKGROUND_WIDTH,
                Constants.BACKGROUND_HEIGHT
        );
        if(chun.getCollisionChun() == HIT.YES){
            beta1 = iciclesHitAnimation;
        }else{
            beta1 = iciclesNOanimation;
        }
        batch.draw(
                beta1,
                0,
                0,
                Constants.BACKGROUND_WIDTH,
                Constants.BACKGROUND_HEIGHT
        );
        coins.render(batch);
        enemies.render(batch);
        chun.render(batch);


    }

    public void dispose(){
        iciclesNOanimation.dispose();
        iciclesHitAnimation.dispose();
        beta.dispose();
        beta1.dispose();
    }

    public Enemies getEnemies() {
        return enemies;
    }
    public Coins getCoins(){ return  coins; }
    public Chun getChun() {
        return chun;
    }
}
