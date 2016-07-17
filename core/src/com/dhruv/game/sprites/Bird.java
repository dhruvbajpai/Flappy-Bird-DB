package com.dhruv.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Dhruv on 13-07-2016.
 */
public class Bird {

    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    public static final int BIRD_WIDTH = 36;
    public static final int BIRD_HEIGHT = 26;
    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Rectangle bounds;
    private Texture texture;
    private Animation birdAnimation;
    private Sound flappingSound;

    public Bird(int x, int y) // starting position
    {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
       // bird = new Texture("bird.png");
        texture = new Texture("birdanim.jpg");
        birdAnimation = new Animation(new TextureRegion(texture),3,0.5f);
        //bounds = new Rectangle(x,y,bird.getWidth(),bird.getHeight());
       // bounds = new Rectangle(x,y,texture.getWidth()/3,texture.getHeight()/3);
        bounds = new Rectangle(x,y,BIRD_WIDTH,BIRD_HEIGHT);
        flappingSound = Gdx.audio.newSound(Gdx.files.internal("swing.ogg"));
    }
    public void update(float dt)
    {
        birdAnimation.update(dt);
        if(position.y >0)
            velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);
        velocity.scl(1/dt);
        if(position.y <0)
            position.y =0;
        bounds.setPosition(position.x,position.y);

    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }
    public void jump()
    {
        velocity.y = 250;

        flappingSound.play(0.5f);
    }
    public Rectangle getBounds()
    {
        return bounds;
    }
    public void dispose()
    {
       // bird.dispose();
        texture.dispose();
        flappingSound.dispose();
    }
}
