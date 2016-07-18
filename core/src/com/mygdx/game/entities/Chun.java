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
    Animation walkRigthAnimation;
    Animation walkLeftAnimation;
    Animation standingAnimation;
    Animation standingLeftAnimation;

    long walkStartime;
    long startJump;
    Level level;


    JumpState jumpState;
    Direction facing;
    WalkState walkState;
    HIT collisionChun;

    int hits;
    int score;
    boolean coinCatch;

    public Chun(Vector2 position, ExtendViewport viewport , Level level){
        this.level = level;
        this.viewport = viewport;
        this.position = position;
        velocity = new Vector2();

        jumpLeft = new Texture(Constants.JUMP_LEFT);
        jumpRight = new Texture(Constants.JUMP_RIGHT);
        walk = new Texture(Constants.WALKING_LEFT_2);

        Array<TextureRegion> walkRightFrames = new Array<TextureRegion>();

        walkRightFrames.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_1)));
        walkRightFrames.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_2)));
        walkRightFrames.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_3)));
        walkRightFrames.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_4)));
        walkRightFrames.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_5)));
        walkRightFrames.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_6)));
        walkRightFrames.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_7)));
        walkRightFrames.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_8)));

        walkRigthAnimation = new Animation(Constants.WALK_LOOP_FRAME_DURATION,walkRightFrames,PlayMode.LOOP);

        Array<TextureRegion> walkLeftFrames = new Array<TextureRegion>();

        walkLeftFrames.add(new TextureRegion(new Texture(Constants.WALKING_LEFT)));
        walkLeftFrames.add(new TextureRegion(new Texture(Constants.WALKING_LEFT_2)));
        walkLeftFrames.add(new TextureRegion(new Texture(Constants.WALKING_LEFT_3)));
        walkLeftAnimation = new Animation(Constants.WALK_LOOP_FRAME_DURATION,walkLeftFrames,PlayMode.LOOP);

        Array<TextureRegion> standingSprites = new Array<TextureRegion>();
        standingSprites.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_1)));
        standingSprites.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_2)));
        standingSprites.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_3)));
        standingSprites.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_4)));
        standingSprites.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_5)));
        standingSprites.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_6)));
        standingSprites.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_7)));
        standingSprites.add(new TextureRegion(new Texture(Constants.FOX_RUNNING_SPRITE_8)));
        standingAnimation = new Animation(Constants.CHUN_STANDING_LOOP_DURATION,standingSprites,PlayMode.LOOP);

        Array<TextureRegion> standingLeftSprites = new Array<TextureRegion>();
        standingLeftSprites.add(new TextureRegion(new Texture(Constants.CHUN_STANDING_LEFT_SPRITE_1)));
        standingLeftSprites.add(new TextureRegion(new Texture(Constants.CHUN_STANDING_LEFT_SPRITE_2)));
        standingLeftSprites.add(new TextureRegion(new Texture(Constants.CHUN_STANDING_LEFT_SPRITE_3)));
        standingLeftSprites.add(new TextureRegion(new Texture(Constants.CHUN_STANDING_LEFT_SPRITE_4)));
        standingLeftAnimation = new Animation(Constants.CHUN_STANDING_LOOP_DURATION,standingLeftSprites,PlayMode.LOOP);

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


        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            moveLeft(delta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            moveRight(delta);
        }else{
            walkState = WalkState.STANDING;
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
        if(jumpState == JumpState.GROUNDED){
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


        }


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
        walkStartime = TimeUtils.nanoTime();
        walkState = WalkState.WAlKING;
        facing = Direction.LEFT;
        position.x -= delta * Constants.CHUN_MOVE_SPEED;
    }

    private void moveRight(float delta){
        walkStartime = TimeUtils.nanoTime();
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


               if (facing == Direction.RIGHT && jumpState == JumpState.GROUNDED && walkState == WalkState.STANDING ) {
                   float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartime);
                   updateBatch = standingAnimation.getKeyFrame(walkTimeSeconds);
                   batch.draw(
                           updateBatch,
                           position.x - Constants.CHUN_EYE_POSITION.x,
                           position.y - Constants.CHUN_EYE_POSITION.y,
                           updateBatch.getRegionWidth(),
                           updateBatch.getRegionHeight()
                   );
               } else if(facing == Direction.RIGHT && jumpState == JumpState.GROUNDED && walkState == WalkState.WAlKING ){
                   float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartime);
                   updateBatch = walkRigthAnimation.getKeyFrame(walkTimeSeconds);
                   Gdx.app.log(TAG, "RIght");
                   batch.draw(
                           updateBatch,
                           position.x - Constants.CHUN_EYE_POSITION.x,
                           position.y - Constants.CHUN_EYE_POSITION.y,
                           updateBatch.getRegionWidth(),
                           updateBatch.getRegionHeight()
                   );
               }else if(facing == Direction.RIGHT && jumpState != JumpState.GROUNDED){
                   jumpUpdate = jumpRight;
                   batch.draw(
                           jumpUpdate,
                           position.x - Constants.CHUN_EYE_POSITION.x,
                           position.y - Constants.CHUN_EYE_POSITION.y,
                           jumpUpdate.getWidth(),
                           jumpUpdate.getHeight()
                   );
               } else if(facing == Direction.LEFT && jumpState == JumpState.GROUNDED && walkState == WalkState.STANDING) {
                   float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartime);
                   updateBatch = standingLeftAnimation.getKeyFrame(walkTimeSeconds);
                   batch.draw(
                           updateBatch,
                           position.x - Constants.CHUN_EYE_POSITION.x,
                           position.y - Constants.CHUN_EYE_POSITION.y,
                           updateBatch.getRegionWidth(),
                           updateBatch.getRegionHeight()
                   );
               }else if(facing == Direction.LEFT && jumpState == JumpState.GROUNDED && walkState == WalkState.WAlKING) {
                   float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartime);
                   updateBatch = walkLeftAnimation.getKeyFrame(walkTimeSeconds);
                   batch.draw(
                           updateBatch,
                           position.x - Constants.CHUN_EYE_POSITION.x,
                           position.y - Constants.CHUN_EYE_POSITION.y,
                           updateBatch.getRegionWidth(),
                           updateBatch.getRegionHeight()
                   );
               }else if(facing == Direction.LEFT && jumpState != JumpState.GROUNDED ){
                   jumpUpdate = jumpLeft;
                   batch.draw(
                           jumpUpdate,
                           position.x - Constants.CHUN_EYE_POSITION.x,
                           position.y - Constants.CHUN_EYE_POSITION.y,
                           jumpUpdate.getWidth(),
                           jumpUpdate.getHeight()
                   );
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
