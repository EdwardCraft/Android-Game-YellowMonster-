package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.util.Constants;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

/**
 * Created by PEDRO on 24/01/2016.
 */
public class Enemy {

    public static final String TAG = Enemy.class.getName();

    Vector2 position;
    Vector2 velocity;
    TextureRegion icicles;
    Animation iceAnimation;
    long starTime;

    public Enemy(Vector2 position){
        this.position = position;
        velocity = new Vector2();


        Array<TextureRegion> iciclesFrame = new Array<TextureRegion>();
        iciclesFrame.add(new TextureRegion(new Texture(Constants.ICE_SPRITE_1)));
        iciclesFrame.add(new TextureRegion(new Texture(Constants.ICE_SPRITE_2)));
        iciclesFrame.add(new TextureRegion(new Texture(Constants.ICE_SPRITE_3)));
        iciclesFrame.add(new TextureRegion(new Texture(Constants.ICE_SPRITE_4)));
        iceAnimation = new Animation(Constants.ICICLES_LOOP_ANIMATION,iciclesFrame,PlayMode.LOOP);
        starTime = TimeUtils.nanoTime();
    }


    public void update(float delta){

        velocity.mulAdd(Constants.ENEMY_ACCELERATION, delta);
        position.mulAdd(velocity, delta);

    }

    public void render(SpriteBatch batch){

        float duration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - starTime);
        icicles = iceAnimation.getKeyFrame(duration);
        batch.draw(
                icicles,
                position.x,
                position.y,
                0,
                0,
                Constants.ENEMY_WIDTH,
                Constants.ENEMY_HEIGHT,
                6,
                6,
                0

        );
    }

    public Vector2 getPosition() {
        return position;
    }
}
