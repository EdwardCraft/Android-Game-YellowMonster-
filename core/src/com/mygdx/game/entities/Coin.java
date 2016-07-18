package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.util.Constants;

/**
 * Created by PEDRO on 29/01/2016.
 */
public class Coin {
    public static final String TAG = Coin.class.getName();

    Vector2 position;
    Vector2 velocity;
    Texture diamond;
    long startTime;

    public Coin(Vector2 position){
        this.position = position;
        velocity = new Vector2();
        diamond = new Texture(Constants.DIAMOND_SPRITE);
    }

    public void update(float delta){
        velocity.mulAdd(Constants.DIAMONDS_ACCELERATION, delta);
        position.mulAdd(velocity,delta);
    }

    public void render(SpriteBatch batch){

        batch.draw(
                diamond,
                position.x,
                position.y
        );
    }

    public void dispose(){
        diamond.dispose();
    }
}
