package com.mygdx.game.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by PEDRO on 24/01/2016.
 */
public class Utils {

    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, Vector2 position, Vector2 offset){

    }
    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region,float x, float y){

    }
    public static float secondsSince(long tineNanos){
        return MathUtils.nanoToSec * (TimeUtils.nanoTime() - tineNanos);
    }
}
