package com.dhruv.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dhruv.game.FlappyDemo;

/**
 * Created by Dhruv on 13-07-2016.
 */
public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.jpg");
        playBtn = new Texture("play.png");
    }

    @Override
    public void handleInput() {

        if(Gdx.input.justTouched())//user has touched the screen
        {
            gsm.set(new PlayState(gsm));
            //disposed in GameStateManager
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {// Sprite batch needs to open and close.
        // open put everything in it and close and then itll render
        sb.begin();
        sb.draw(background,0,0, FlappyDemo.WIDTH,FlappyDemo.HEIGHT);
        sb.draw(playBtn,(FlappyDemo.WIDTH/2- playBtn.getWidth()/2),(FlappyDemo.HEIGHT/2+ playBtn.getHeight()/2));
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
