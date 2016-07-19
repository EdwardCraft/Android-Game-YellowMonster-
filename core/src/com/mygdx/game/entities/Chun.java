package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Level;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.Enums.Direction;
import com.mygdx.game.util.Enums.WalkState;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.util.Enums.HIT;
import com.mygdx.game.util.Enums.JumpState;
import com.badlogic.gdx.Application;



/**
 * Created by PEDRO on 20/01/2016.
 */
public class Chun {
    public static  final String TAG = Chun.class.getSimpleName();

    Vector2 position;
    Vector2 velocity;
    ExtendViewport viewport;

    Texture jumpUpdate;
    Texture jumpLeft;
    Texture jumpRight;
    Texture walk;

    TextureRegion updateBatch;
    TextureRegion toBallBatch;
    TextureRegion rollBatch;


    Animation walkRigthAnimation;
    Animation walkLeftAnimation;
    Animation standingAnimationSprites;
    Animation toBallAnimation;
    Animation rollBallAnimation;
    Animation hitBallLeftAnimation;

    long walkStartime;
    long startJump;
    private float toBallStartTime;
    private float rollingStartTime;

    Level level;


    JumpState jumpState;
    Direction facing;
    WalkState walkState;
    HIT collisionChun;

    private Boolean done;
    private Boolean done1;


    int hits;
    int score;
    boolean coinCatch;

    public Chun(Vector2 position, ExtendViewport viewport , Level level){
        this.level = level;
        this.viewport = viewport;
        this.position = position;
        velocity = new Vector2();
        done = false;
        done1 = false;
        walkStartime = TimeUtils.nanoTime();
        getAnimationFrames();
        init();


    }


    public void init(){
        velocity.setZero();
        facing = Direction.RIGHT;
        walkState = WalkState.STANDING;
        collisionChun = HIT.NO;
        jumpState = JumpState.FALLING;
        hits = Constants.CHUN_LIVES;
        score = 0;
    }


    private void getAnimationFrames(){

        idleSprites();
        toBallSprites();
        ballSprites();

    }


    private void idleSprites(){
        Array<TextureRegion> standingSprites = new Array<TextureRegion>();

        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_1)));
        standingSprites.get(0).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_2)));
        standingSprites.get(1).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_3)));
        standingSprites.get(2).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_4)));
        standingSprites.get(3).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_5)));
        standingSprites.get(4).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_4)));
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_3)));
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_2)));
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_1)));
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_6)));
        standingSprites.get(5).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_7)));
        standingSprites.get(6).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_8)));
        standingSprites.get(7).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_9)));
        standingSprites.get(8).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_8)));
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_7)));
        standingSprites.add(new TextureRegion(new Texture(Constants.BALL_IDLE_SPRITE_6)));

        standingAnimationSprites = new Animation(Constants.CHUN_STANDING_LOOP_DURATION,standingSprites,PlayMode.LOOP);


    }


    private void toBallSprites(){
        Array<TextureRegion> toBallSprites = new Array<TextureRegion>();

        toBallSprites.add(new TextureRegion(new Texture(Constants.TO_BALL_SPRITE_1)));
        toBallSprites.add(new TextureRegion(new Texture(Constants.TO_BALL_SPRITE_2)));
        toBallSprites.add(new TextureRegion(new Texture(Constants.TO_BALL_SPRITE_3)));
        toBallSprites.add(new TextureRegion(new Texture(Constants.TO_BALL_SPRITE_4)));
        toBallSprites.add(new TextureRegion(new Texture(Constants.TO_BALL_SPRITE_5)));
        toBallSprites.add(new TextureRegion(new Texture(Constants.TO_BALL_SPRITE_6)));
        toBallSprites.add(new TextureRegion(new Texture(Constants.TO_BALL_SPRITE_7)));
        toBallSprites.add(new TextureRegion(new Texture(Constants.TO_BALL_SPRITE_8)));
        toBallSprites.add(new TextureRegion(new Texture(Constants.TO_BALL_SPRITE_9)));
        toBallSprites.add(new TextureRegion(new Texture(Constants.TO_BALL_SPRITE_10)));

        for(int i = 0; i < toBallSprites.size; i++){
            toBallSprites.get(i).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        toBallAnimation = new Animation(Constants.TO_BALL_LOOP_DURATION, toBallSprites, PlayMode.NORMAL);




    }



    private void ballSprites(){
        Array<TextureRegion> ballSprites=  new Array<TextureRegion>();
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_1)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_2)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_3)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_4)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_5)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_6)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_7)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_8)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_9)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_10)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_11)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_12)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_13)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_14)));
        ballSprites.add(new TextureRegion(new Texture(Constants.ROLL_SPRITE_15)));

        for(int i = 0 ; i < ballSprites.size; i++){
            ballSprites.get(i).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        rollBallAnimation = new Animation(Constants.ROLL_BALL_LOOP_ANIMATION, ballSprites, PlayMode.LOOP);




    }










    public void update(float delta){
        velocity.y -=Constants.GRAVITATIONAL_ACCELERATION;
        position.mulAdd(velocity, delta);

        if(jumpState != JumpState.JUMPING ){
            jumpState = JumpState.FALLING;
            if(position.y - Constants.CHUN_EYE_HEIGHT < 0){
                jumpState = JumpState.GROUNDED;
                collisionChun = HIT.NO;
                position.y = Constants.CHUN_EYE_HEIGHT;
                velocity.y = 0;
                velocity.x = 0;
            }
        }

        if(jumpState == JumpState.GROUNDED){
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                moveLeft(delta);
            }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                moveRight(delta);
            }else{
                if(done)done = false;
                if(done1)done1 = false;
                walkState = WalkState.STANDING;
            }
        }



        if(onMobile()){
            if(jumpState == JumpState.GROUNDED){
                float tempPositionX = position.x;
                float accelerometerInput = -Gdx.input.getAccelerometerY() / ( Constants.ACCELEROMETER_SENSITIVITY * Constants.GRAVITATIONAL_ACCELERATION);
                position.x  += -delta * accelerometerInput * Constants.CHUN_MOVE_SPEED * 2;
                if(tempPositionX > position.x){
                    walkState = WalkState.WAlKING;
                    facing = Direction.LEFT;
                }else if(tempPositionX < position.x){
                    walkState = WalkState.WAlKING;
                    facing = Direction.RIGHT;
                }else{
                    walkState = WalkState.STANDING;
                }
            }
        }


        //Collie with icicles
        Rectangle chunBounds = new Rectangle(
                position.x - Constants.CHUN_STANCE_WIDTH / 2,
                position.y - Constants.CHUN_EYE_HEIGHT,
                Constants.CHUN_STANCE_WIDTH,
                Constants.CHUN_HEIGHT
        );

      /*  if(jumpState == JumpState.GROUNDED){
            for(Enemy enemy : level.getEnemies().getEnemyList()){
                Rectangle enemyBounds = new Rectangle(
                        enemy.position.x + 7,
                        enemy.position.y ,
                        0,
                        0
                );
                if(chunBounds.overlaps(enemyBounds)){
                    collisionChun = HIT.YES;
                    hits --;
                    if(position.x  < enemy.position.x ){
                        recoilFromEnemy(Direction.LEFT);
                    }else if(position.x  > enemy.position.x ){
                        recoilFromEnemy(Direction.RIGHT);
                    }
                }
            }
            for(Coin coin : level.getCoins().getCoinListTwo()){
                Rectangle coinBouds = new Rectangle(
                        coin.position.x,
                        coin.position.y,
                        0,
                        0
                );
                if(chunBounds.overlaps(coinBouds)){
                        score ++;
                        level.getCoins().getCoinListTwo().removeValue(coin,false);
                }

            }


        }*/


        ensureInBounds();

    }

    private boolean onMobile(){
        return Gdx.app.getType() == Application.ApplicationType.Android;
    }




    private void ensureInBounds(){
        if(position.x - 12 < 0){
            position.x =  12;
        }
        if(position.x + 13 > viewport.getWorldWidth() ){
            position.x = viewport.getWorldWidth() - 13;
        }
        if(position.y + 10 > viewport.getWorldHeight()){
            position.y = viewport.getWorldHeight() - 10;
        }
    }

    private void moveLeft(float delta){
        if(!done){
            toBallStartTime = TimeUtils.nanoTime();
            done = true;
        }
        if(!done1){
            rollingStartTime = TimeUtils.nanoTime();
        }

        walkState = WalkState.WAlKING;
        facing = Direction.LEFT;
        position.x -= delta * Constants.CHUN_MOVE_SPEED;
    }

    private void moveRight(float delta){
        if(!done){
            toBallStartTime = TimeUtils.nanoTime();
            done = true;
        }
        if(!done1){
            rollingStartTime = TimeUtils.nanoTime();
        }

        walkState = WalkState.WAlKING;
        facing = Direction.RIGHT;
        position.x += delta * Constants.CHUN_MOVE_SPEED;
    }
    private void startJump(){
        jumpState = JumpState.JUMPING;
        startJump = TimeUtils.nanoTime();
        continueJump();
    }

    private  void continueJump(){
        if(jumpState == JumpState.JUMPING){
            float jumpDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - startJump);
            if(jumpDuration < Constants.MAX_JUMP_DURATiON){
                velocity.y = Constants.JUMP_SPEED;
            }else {
                endJump();
            }
        }
    }

    private void endJump(){
        if(jumpState == JumpState.JUMPING){
            jumpState = JumpState.FALLING;
        }
    }

    private void recoilFromEnemy(Direction direction){
        velocity.y = Constants.KNOCKBACK_VELOCITY.y;
        if(direction == Direction.LEFT){
            velocity.x = -Constants.KNOCKBACK_VELOCITY.x;
        }else{
            velocity.x = Constants.KNOCKBACK_VELOCITY.x;
        }

    }


    public void render(SpriteBatch batch){

        if(facing == Direction.LEFT && jumpState == JumpState.GROUNDED && walkState == WalkState.STANDING){
            float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartime);
            updateBatch = standingAnimationSprites.getKeyFrame(walkTimeSeconds);
            batch.draw(
                    updateBatch,
                    position.x - Constants.CHUN_EYE_POSITION.x,
                    position.y - Constants.CHUN_EYE_POSITION.y,
                    17,
                    17
            );
        }else  if(facing == Direction.RIGHT && jumpState == JumpState.GROUNDED && walkState == WalkState.STANDING){
            float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartime);
            updateBatch = standingAnimationSprites.getKeyFrame(walkTimeSeconds);
            batch.draw(
                    updateBatch,
                    position.x,
                    position.y - Constants.CHUN_EYE_POSITION.y,
                    -17,
                    17

            );
        }else if(facing == Direction.LEFT && jumpState == JumpState.GROUNDED && walkState == WalkState.WAlKING){
            float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - toBallStartTime);
            if(toBallAnimation.isAnimationFinished(walkTimeSeconds))done1 = true;
            if(!done1){
                toBallBatch = toBallAnimation.getKeyFrame(walkTimeSeconds);
                batch.draw(
                        toBallBatch,
                        position.x - Constants.CHUN_EYE_POSITION.x,
                        position.y - Constants.CHUN_EYE_POSITION.y,
                        17,
                        17

                );
            }else{
               float rollStartTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - rollingStartTime);
                rollBatch = rollBallAnimation.getKeyFrame(rollStartTime);
                batch.draw(
                        rollBatch,
                        position.x - Constants.CHUN_EYE_POSITION.x,
                        position.y - Constants.CHUN_EYE_POSITION.y,
                        17,
                        17

                );
            }

        }else if(facing == Direction.RIGHT && jumpState == JumpState.GROUNDED && walkState == WalkState.WAlKING){
            float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - toBallStartTime);
            if(toBallAnimation.isAnimationFinished(walkTimeSeconds))done1 = true;
            if(!done1){
                toBallBatch = toBallAnimation.getKeyFrame(walkTimeSeconds);
                batch.draw(
                        toBallBatch,
                        position.x,
                        position.y - Constants.CHUN_EYE_POSITION.y,
                        -17,
                        17

                );
            }else{
                float rollStartTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - rollingStartTime);
                rollBatch = rollBallAnimation.getKeyFrame(rollStartTime);
                batch.draw(
                        rollBatch,
                        position.x ,
                        position.y - Constants.CHUN_EYE_POSITION.y,
                        -17,
                        17

                );
            }


        }







    }




    public void dispose(){
         jumpUpdate.dispose();
         jumpLeft.dispose();
         jumpRight.dispose();
         walk.dispose();
    }

    public HIT getCollisionChun() {
        return collisionChun;
    }

    public void setCollisionChun(HIT collisionChun) {
        this.collisionChun = collisionChun;
    }

    public int getHits() {
        return hits;
    }

    public boolean isCoinCatch() {
        return coinCatch;
    }

    public void setCoinCatch(boolean coinCatch) {
        this.coinCatch = coinCatch;
    }

    public int getScore() {
        return score;
    }

    public JumpState getJumpState() {
        return jumpState;
    }

    public WalkState getWalkState() {
        return walkState;
    }
}
