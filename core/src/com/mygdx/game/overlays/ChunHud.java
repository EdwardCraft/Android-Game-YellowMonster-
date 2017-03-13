package com.mygdx.game.overlays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.util.Constants;

/**
 * Created by PEDRO on 29/01/2016.
 */
public class ChunHud {

    BitmapFont font;
    Viewport viewport;
    Texture standingRight;

    public ChunHud(Viewport viewport, BitmapFont font){
        this.viewport = viewport;
        this.font = font;
        standingRight = new Texture(Constants.ROLL_SPRITE_1);
        standingRight.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

    }

    public void render(SpriteBatch batch, int lives, int score){
        String hudString = Constants.HUD_SCORE_LEBEl + score + "\n";
        font.getData().setScale(.13f);
        font.draw(batch, hudString, viewport.getWorldWidth() - 125,
                viewport.getWorldHeight() - (Constants.HUD_MARGIN * 2) - 10);
        for(int i = 1; i <= lives; i++){
           Vector2 drawPosition = new Vector2(
                    viewport.getWorldWidth() - i * (Constants.HUD_MARGIN / 2 + Constants.HUD_SPRITE_SIZE),
                    viewport.getWorldHeight() - Constants.HUD_MARGIN - Constants.HUD_SPRITE_SIZE + 5
            );
            batch.draw(
                    standingRight,
                    drawPosition.x,
                    drawPosition.y,
                    Constants.HUD_SPRITE_SIZE,
                    Constants.HUD_SPRITE_SIZE
            );
        }


    }
}
