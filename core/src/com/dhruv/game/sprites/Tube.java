package com.dhruv.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Dhruv on 14-07-2016.
 */
public class Tube {


    public static final int TUBE_WIDTH = 32;
    private Texture topTube, botTube;
    private Vector2 posTopTube, posBotTube;
    private Rectangle topBound, botBound;
    private static final int FLUCTUATION = 180;
    private static final int TUBEGAP = 100;
    private static final int LOWESTOPENING = 80;

    private Random rand;

    public Tube(float x) {

        rand = new Random();
        topTube = new Texture("Toptube.png"); // This should have been static for
        botTube = new Texture("Bottube.png");// better efficieny
        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBEGAP + LOWESTOPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBEGAP - botTube.getHeight());
        topBound = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        botBound = new Rectangle(posBotTube.x, posBotTube.y, botTube.getWidth(), botTube.getHeight());
        System.out.println("Position Top Tube:" + posTopTube.y);
        System.out.println("Position Botton Tube:" + posBotTube.y);

    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBotTube() {
        return botTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBEGAP + LOWESTOPENING);
        posBotTube.set(x, posTopTube.y - TUBEGAP - botTube.getHeight());
        botBound.setPosition(posBotTube.x, posBotTube.y);
        topBound.setPosition(posTopTube.x,posTopTube.y);

    }
    public boolean collides(Rectangle player)
    {
        return player.overlaps(topBound)|| player.overlaps(botBound);
    }
    public void dispose()
    {
        botTube.dispose();
        topTube.dispose();
    }
}

