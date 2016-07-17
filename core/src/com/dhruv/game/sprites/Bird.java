package com.dhruv.game.sprites;

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
    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Rectangle bounds;
    private Texture texture;
    private Animation birdAnimation;

    public Bird(int x, int y) // starting position
    {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
       // bird = new Texture("bird.png");
        texture = new Texture("birdanim.jpg");

        birdAnimation = new Animation(new TextureRegion(texture),3,0.5f);
        //bounds = new Rectangle(x,y,bird.getWidth(),bird.getHeight());
       // bounds = new Rectangle(x,y,texture.getWidth()/3,texture.getHeight()/3);
        bounds = new Rectangle(x,y,36,26);
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
    }
    public Rectangle getBounds()
    {
        return bounds;
    }
    public void dispose()
    {
       // bird.dispose();
        texture.dispose();
    }
}
