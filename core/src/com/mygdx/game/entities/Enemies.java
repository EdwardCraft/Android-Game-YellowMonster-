package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Level;
import com.mygdx.game.util.Constants.Difficulty;
import com.mygdx.game.util.Enums.WalkState;


/**
 * Created by PEDRO on 24/01/2016.
 */
public class Enemies {

    public static final String TAG = Enemies.class.getName();

    DelayedRemovalArray<Enemy> enemyList;
    Viewport viewport;
    Difficulty difficulty;
    Level level;
    public Enemies(){

    }

    public void init(Viewport viewport, Difficulty difficulty, Level level){
        this.level = level;
        this.difficulty = difficulty;
        this.viewport = viewport;
        enemyList = new DelayedRemovalArray<Enemy>(false,100);

    }

    public void update(float delta){

        if(MathUtils.random() < delta * difficulty.getSpawnRate() /*&& level.getChun().getWalkState() == WalkState.STANDING*/){
            Vector2 newEnemyPosition = new Vector2(
                    MathUtils.random() * viewport.getWorldWidth(),
                    viewport.getWorldHeight()
            );
                Enemy newEnemy = new Enemy(newEnemyPosition);
                enemyList.add(newEnemy);
        }

        for(Enemy enemy : enemyList){
            enemy.update(delta);
        }

        enemyList.begin();
        for(int i=0; i < enemyList.size; i++){
            if(enemyList.get(i).position.y < -30){
                enemyList.removeIndex(i);
            }
        }
        enemyList.end();

    }

    public void render(SpriteBatch batch){
        for(Enemy enemy: enemyList){
            enemy.render(batch);
        }
    }


    public DelayedRemovalArray<Enemy> getEnemyList() {
        return enemyList;
    }



}
