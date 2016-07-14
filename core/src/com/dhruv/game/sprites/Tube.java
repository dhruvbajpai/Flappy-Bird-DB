package com.dhruv.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Dhruv on 14-07-2016.
 */
public class Tube {

    private Texture topTube, botTube;
    private Vector2 posTopTube,posBotTube;
    private static final int FLUCTUATION = 130;
    private static final int TUBEGAP = 100;
    private static final int LOWESTOPENING = 120;

    private Random rand;

    public Tube(float x) {

        rand = new Random();
        topTube = new Texture("Toptube.png");
        botTube  = new Texture("Bottube.png");
        posTopTube = new Vector2(x,rand.nextInt(FLUCTUATION)+ TUBEGAP +LOWESTOPENING );
        posBotTube = new Vector2(x, posTopTube.y - TUBEGAP - botTube.getHeight());

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

    public void reposition(int x)
    {
        posTopTube.set(x,rand.nextInt(FLUCTUATION)+ TUBEGAP +LOWESTOPENING );
        posBotTube.set(x, posTopTube.y - TUBEGAP - botTube.getHeight());
    }
}
