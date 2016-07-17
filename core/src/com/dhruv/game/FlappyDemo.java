package com.dhruv.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dhruv.game.states.GameStateManager;
import com.dhruv.game.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Flappy Bird";
	private GameStateManager gsm;
	private SpriteBatch batch; // very heavy files..only need one and pass it around to different states
	//Texture img;
	private Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.3f);//10% volume
		music.play();
		gsm.push(new MenuState(gsm));
		//img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () { // this method here is on loop.. it renders over and over

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);//wipes the screen clean with this and then redraw the SpriteBatch
		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
		gsm.update(Gdx.graphics.getDeltaTime());// update and  render of the topmost state of the
		//stack from the GameStateManager
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
		//img.dispose();
	}
}
