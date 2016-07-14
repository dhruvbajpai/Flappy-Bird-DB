package com.dhruv.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.dhruv.game.FlappyDemo;
import com.dhruv.game.sprites.Bird;
import com.dhruv.game.sprites.Tube;


/**
 * Created by Dhruv on 13-07-2016.
 */
public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT=4;
    private Bird bird, flappy;
    private Texture background;
    private Tube tube;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        flappy = new Bird(50,350);
        tube = new Tube(100);
        background = new Texture("background.jpg");
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        System.out.println(cam.position.x);
        System.out.println(cam.position.y);


    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
                bird.jump();
        }
    }

    @Override
    public void update(float dt) { // called periodically from the FlappyDemo
        // after the play state has been reched by click on MenuState
        handleInput();
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,(cam.position.x - cam.viewportWidth/2),0);
        sb.draw(tube.getTopTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);
        sb.draw(tube.getBotTube(),tube.getPosBotTube().x,tube.getPosBotTube().y);

        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);

       // sb.draw(flappy.getTexture(), flappy.getPosition().x, flappy.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
