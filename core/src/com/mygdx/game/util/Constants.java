package com.mygdx.game.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by PEDRO on 20/01/2016.
 */
public class Constants {

    public static final Color BACKGRAOUND_COLOR = Color.BLACK;
    public static final float WOLRD_SIZE = 125;
    //Beta

    //HUD
    public static final float HUD_FONT_REFERENCE_SCREEN_SIZE = 270.0f;
    public static final float HUD_MARGIN = 10.0f;
    public static final String HUD_SCORE_LEBEl = "SCORE: ";

    //world
    public static final float ACCELEROMETER_SENSITIVITY = 0.5f;
    public static final float GRAVITATIONAL_ACCELERATION = 9.8f;
    public static final Vector2 DIAMONDS_ACCELERATION = new Vector2(0, -200.f);
    public static final float  DIAMONDS_SPAWN_PER_SECOND = 2f;
    public static final float ICICLES_LOOP_ANIMATION = .09f;
    public static final float BACKGROUND_ICICLE_FRAME_DURATION = 0.05f;
    public static final Vector2 KNOCKBACK_VELOCITY = new Vector2(120, 120);

    //DIFFICULTY SCREEN
    public static final Color EASY_COLOR = new Color(0.2f, 0.2f, 1, 1);
    public static final Color MEDIUM_COLOR = new Color(0.5f, 0.5f, 1, 1);
    public static final Color HARD_COLOR = new Color(0.7f, 0.7f, 1, 1);
    public static final float DIFFICULTY_WORLD_SIZE = 480.0f;
    public static final float DIFFICULTY_BUBBLE_RADIUS = DIFFICULTY_WORLD_SIZE / 9;
    public static final float DIFFICULTY_lABEL_SCALE = .15f;
    public static final Vector2 EASY_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 4, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 MEDIUM_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE / 2, DIFFICULTY_WORLD_SIZE / 2);
    public static final Vector2 HARD_CENTER = new Vector2(DIFFICULTY_WORLD_SIZE * 3 / 4, DIFFICULTY_WORLD_SIZE / 2);


    // Chun
    public static final Vector2 CHUN_EYE_POSITION = new Vector2(16, 24);
    public static final int CHUN_LIVES = 3;
    public static final float WALK_LOOP_FRAME_DURATION = 0.01f;
    public static final float CHUN_EYE_HEIGHT = 24.0f;
    public static final float CHUN_STANCE_WIDTH = 21.0f;
    public static final float CHUN_HEIGHT = 23.0f;
    public static final float CHUN_MOVE_SPEED =  250;
    public static final float MAX_JUMP_DURATiON = .1f;
    public static final float JUMP_SPEED = 1.5f * WOLRD_SIZE;
    public static final float CHUN_STANDING_LOOP_DURATION = .4f;

    // Enemy

    public static final Vector2 ENEMY_ACCELERATION = new Vector2(0, -350.0f);
    public static final float ENEMY_SPAWMS_PER_SECOND = 4f;
    public static final int NINE_PATCH_EDGE = 2;
    public static final float ENEMY_COLLISION_RADIUS = 10;
    public static final float ENEMY_HEIGHT = 4;
    public static final float ENEMY_WIDTH = 2;

    //DIFFICULTY lABELS
    public static final String EASY_LABEL = "EASY";
    public static final String MEDIUM_LABEL = "MEDIUM";
    public static final String HARD_LEBEL = "HARD";

    //SPAWN RATES
    public static final float EASY_SPAWNS_PER_SECOND = 3;
    public static final float MEDIUM_SPAWNS_PER_SECOND = 6;
    public static final float HARD_SPAWNS_PER_SECOND = 12;

    //Sprites
    public static final String  ENEMY_SPRITE = "sprites/enemy.png";
    public static final String STANDING_RIGHT = "sprites/standing-right.png";
    public static final String STANDING_LEFT = "sprites/fox-walking-right-1.png";
    public static final String WALKING_LEFT = "sprites/chun-waking-left-1.png";
    public static final String WALKING_LEFT_2 = "sprites/chun-waking-left-2.png";
    public static final String WALKING_LEFT_3 = "sprites/chun-waking-left-3.png";
    public static final String WALKING_RIGHT = "sprites/chun-waking-right-1.png";
    public static final String WALKING_RIGHT_2 = "sprites/chun-waking-right-2.png";
    public static final String WALKING_RIGHT_3 = "sprites/chun-waking-right-3.png";
    public static final String ICICLES_SPRITE_1 = "sprites/icicles-1.png";
    public static final String ICICLES_SPRITE_2 = "sprites/icicles-2.png";
    public static final String BETA_LEVEL_1 = "sprites/beta-1.png";
    public static final String ICE_SPRITE_1 = "sprites/ice-1.png";
    public static final String ICE_SPRITE_2 = "sprites/ice-2.png";
    public static final String ICE_SPRITE_3 = "sprites/ice-3.png";
    public static final String ICE_SPRITE_4 = "sprites/ice-4.png";
    public static final String JUMP_LEFT = "sprites/jumping-left.png";
    public static final String JUMP_RIGHT = "sprites/jumping-right.png";
    public static final String DIAMOND_SPRITE = "sprites/Coin.png";
    public static final String CHUN_STANDING_SPRITE_1 = "sprites/chun-standing-right-1.png";
    public static final String CHUN_STANDING_SPRITE_2 = "sprites/chun-standing-right-2.png";
    public static final String CHUN_STANDING_SPRITE_3 = "sprites/chun-standing-right-3.png";
    public static final String CHUN_STANDING_SPRITE_4 = "sprites/chun-standing-right-4.png";
    public static final String CHUN_STANDING_LEFT_SPRITE_1 = "sprites/chun-standing-left-1.png";
    public static final String CHUN_STANDING_LEFT_SPRITE_2 = "sprites/chun-standing-left-2.png";
    public static final String CHUN_STANDING_LEFT_SPRITE_3 = "sprites/chun-standing-left-3.png";
    public static final String CHUN_STANDING_LEFT_SPRITE_4 = "sprites/chun-standing-left-4.png";
    public static final String FOX_IDLE_SPRITE_1 = "fox idle/fox-idle-sprite-1.png";
    public static final String FOX_IDLE_SPRITE_2 = "fox idle/fox-idle-sprite-2.png";
    public static final String FOX_IDLE_SPRITE_3 = "fox idle/fox-idle-sprite-3.png";
    public static final String FOX_IDLE_SPRITE_4 = "fox idle/fox-idle-sprite-4.png";
    public static final String FOX_RUNNING_SPRITE_1 = "running right/fox-walking-right-1.png";
    public static final String FOX_RUNNING_SPRITE_2 = "running right/fox-walking-right-2.png";
    public static final String FOX_RUNNING_SPRITE_3 = "running right/fox-walking-right-3.png";
    public static final String FOX_RUNNING_SPRITE_4 = "running right/fox-walking-right-4.png";
    public static final String FOX_RUNNING_SPRITE_5 = "running right/fox-walking-right-5.png";
    public static final String FOX_RUNNING_SPRITE_6 = "running right/fox-walking-right-6.png";
    public static final String FOX_RUNNING_SPRITE_7 = "running right/fox-walking-right-7.png";
    public static final String FOX_RUNNING_SPRITE_8 = "running right/fox-walking-right-8.png";

    public enum Difficulty{
        EASY(EASY_SPAWNS_PER_SECOND, EASY_LABEL),
        MEDIUM(MEDIUM_SPAWNS_PER_SECOND, MEDIUM_LABEL),
        HARD(HARD_SPAWNS_PER_SECOND, HARD_LEBEL);

        float spawnRate;
        String label;

        Difficulty(float spawnRate, String label){
            this.spawnRate = spawnRate;
            this.label = label;
        }

        public float getSpawnRate(){
            return spawnRate;
        }

        public String getLabel(){
            return label;
        }
    }
}
