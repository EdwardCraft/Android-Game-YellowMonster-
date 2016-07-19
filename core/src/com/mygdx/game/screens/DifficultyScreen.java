package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.DogeMania;
import com.mygdx.game.util.Constants;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

/**
 * Created by PEDRO on 30/01/2016.
 */
public class DifficultyScreen extends InputAdapter implements Screen{
    public static final String TAG = DifficultyScreen.class.getName();

    DogeMania dogeMania;
    ShapeRenderer renderer;
    SpriteBatch batch;
    FitViewport viewport;
    ExtendViewport viewportExtended;
    BitmapFont font;
    Texture background;
    Texture ice;
    Animation standingAnimationSprites;
    TextureRegion chun;
    float startTime;
    public DifficultyScreen(DogeMania dogeMania){
        this.dogeMania = dogeMania;
    }


    @Override
    public void show() {
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        viewport = new FitViewport(Constants.DIFFICULTY_WORLD_SIZE, Constants.DIFFICULTY_WORLD_SIZE);
        viewportExtended = new ExtendViewport(Constants.WOLRD_SIZE, Constants.WOLRD_SIZE);
        Gdx.input.setInputProcessor(this);
        font = new BitmapFont(Gdx.files.internal("sprites/font-1-export.fnt"));
        font.getData().setScale(Constants.DIFFICULTY_lABEL_SCALE);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        background =new Texture(Constants.BETA_LEVEL_1);
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        ice = new Texture(Constants.ICICLES_SPRITE_1);
        ice.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        getSpritesYellowBall();




        startTime = TimeUtils.nanoTime();
    }


    private void getSpritesYellowBall(){
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


    @Override
    public void render(float delta) {

        viewportExtended.apply();
        Gdx.gl.glClearColor(Constants.BACKGRAOUND_COLOR.r, Constants.BACKGRAOUND_COLOR.g,
                Constants.BACKGRAOUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewportExtended.getCamera().combined);
        batch.begin();
        batch.draw(
                background,
                0,
                0,
                Constants.BACKGROUND_WIDTH,
                Constants.BACKGROUND_HEIGHT
        );
        batch.draw(
                ice,
                0,
                0,
                Constants.BACKGROUND_WIDTH,
                Constants.BACKGROUND_HEIGHT
        );
        float timeInSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - startTime);
        chun = standingAnimationSprites.getKeyFrame(timeInSeconds);
        batch.draw(
                chun,
                Constants.STARTING_POINT,
                0,
                -Constants.YELLOW_BALL_WIDTH,
                Constants.YELLOW_BALL_HEIGHT

        );

        batch.end();


        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.setColor(Constants.EASY_COLOR);
        renderer.circle(Constants.EASY_CENTER.x, Constants.EASY_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.setColor(Constants.MEDIUM_COLOR);
        renderer.circle(Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.setColor(Constants.HARD_COLOR);
        renderer.circle(Constants.HARD_CENTER.x, Constants.HARD_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS);

        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        final GlyphLayout easyLayout = new GlyphLayout(font, Constants.EASY_LABEL);
        font.draw(batch, Constants.EASY_LABEL, Constants.EASY_CENTER.x,
                Constants.EASY_CENTER.y + easyLayout.height / 2, 0, Align.center, false);

        final GlyphLayout mediumLayout = new GlyphLayout(font, Constants.MEDIUM_LABEL);
        font.draw(batch, Constants.MEDIUM_LABEL, Constants.MEDIUM_CENTER.x,
                Constants.MEDIUM_CENTER.y + mediumLayout.height / 2, 0, Align.center, false);

        final GlyphLayout hardLayout = new GlyphLayout(font, Constants.HARD_LEBEL);
        font.draw(batch, Constants.HARD_LEBEL, Constants.HARD_CENTER.x,
                Constants.HARD_CENTER.y + hardLayout.height / 2, 0, Align.center, false);
        batch.end();



    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height, true);
        viewportExtended.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        batch.dispose();
        font.dispose();
        renderer.dispose();
        ice.dispose();
        background.dispose();

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        renderer.dispose();
        ice.dispose();
        background.dispose();

    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        if(worldTouch.dst(Constants.EASY_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS){
            dogeMania.showDogeScreen(Constants.Difficulty.EASY);
        }
        if(worldTouch.dst(Constants.MEDIUM_CENTER) < Constants.DIFFICULTY_BUBBLE_RADIUS){
            dogeMania.showDogeScreen(Constants.Difficulty.HARD);
        }
        if(worldTouch.dst(Constants.HARD_CENTER ) < Constants.DIFFICULTY_BUBBLE_RADIUS){
            dogeMania.showDogeScreen(Constants.Difficulty.HARD);
        }


        return true;
    }
}
