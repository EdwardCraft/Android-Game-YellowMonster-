package com.mygdx.game.entities;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.util.Constants;
import com.mygdx.game.Level;
import com.badlogic.gdx.utils.SnapshotArray;


/**
 * Created by PEDRO on 29/01/2016.
 */
public class Coins {
    public static final String TAG = Coins.class.getName();

    DelayedRemovalArray<Coin> coinList;
    SnapshotArray<Coin> coinListTwo;
    Viewport viewport;
    Level level;

    public Coins(Viewport viewport,Level level){
        this.viewport = viewport;
        this.level = level;
        coinList = new DelayedRemovalArray<Coin>(false, 100);
        coinListTwo = new SnapshotArray<Coin>(false,100);
    }

    public void init(){

    }

    public void update(float delta){

        if(MathUtils.random() < delta * Constants.DIAMONDS_SPAWN_PER_SECOND){
            Vector2 newDiamond = new Vector2(
                    MathUtils.random() * viewport.getWorldWidth(),
                    viewport.getWorldHeight()
            );
            Coin newCoin = new Coin(newDiamond);
            coinListTwo.add(newCoin);
        }
        for(Coin coin : coinListTwo){
            coin.update(delta);
        }
        coinListTwo.begin();
        for(int i = 0; i < coinListTwo.size; i++){
            if(coinListTwo.get(i).position.y < - 20 ){
                coinListTwo.removeIndex(i);
            }

        }
        coinListTwo.end();
    }


    public void render(SpriteBatch batch){
        for(Coin coin : coinListTwo){
            coin.render(batch);
        }

    }

    public SnapshotArray<Coin> getCoinListTwo() {
        return coinListTwo;
    }
}
