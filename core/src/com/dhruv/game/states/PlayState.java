package com.dhruv.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dhruv.game.FlappyDemo;
import com.dhruv.game.sprites.Bird;
import com.dhruv.game.sprites.Tube;


/**
 * Created by Dhruv on 13-07-2016.
 */
public class PlayState extends State {

    private int curTubeNumber=0;
    int score=0;
    Sound scoreIncrementSound;
    String totalScore ="Score: 0";
    private BitmapFont scoreCard;
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT=4;
    private static final int GROUND_OFFSET = -50;
    private Bird bird, flappy;
    private Texture background;
    private Texture ground;
    private Vector2 ground1,ground2;
    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        flappy = new Bird(50,350);
        tubes = new Array<Tube>();
        ground = new Texture("ground.png");
        background = new Texture("background.jpg");
        ground1= new Vector2(cam.position.x - cam.viewportWidth/2,GROUND_OFFSET);
        ground2 = new Vector2(cam.position.x - cam.viewportWidth/2 + ground.getWidth(),GROUND_OFFSET);
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        scoreCard = new BitmapFont();
        scoreIncrementSound = Gdx.audio.newSound(Gdx.files.internal("jump.ogg"));
        for(int i=1;i<=TUBE_COUNT;i++)
        {

            tubes.add(new Tube(100+ i*(TUBE_SPACING + Tube.TUBE_WIDTH )));
        }
        System.out.println(cam.position.x);
        System.out.println(cam.position.y);
        System.out.println(cam.position.z);



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
        updateGround();
        cam.position.x = bird.getPosition().x + 90; // offset value for bird

        // Tube reposition logic
        for(Tube tube: tubes)
        {
            if(cam.position.x - cam.viewportWidth/2 > (tube.getPosBotTube().x + tube.getTopTube().getWidth()))
            {
                tube.reposition(tube.getPosTopTube().x + ( (TUBE_SPACING + tube.TUBE_WIDTH)* TUBE_COUNT ));
            }
            if(tube.collides(bird.getBounds())) {
                gsm.set(new PlayState(gsm));
               break;
            }
            if(bird.getPosition().y <= ground.getHeight() + GROUND_OFFSET)
            {
                gsm.set(new PlayState(gsm));
                break;
            }

        }

        //Score Calculation Added
        if(bird.getPosition().x > tubes.get(curTubeNumber).getPosTopTube().x + tubes.get(curTubeNumber).getTopTube().getWidth() )
        {
            score++;
            scoreIncrementSound.play(0.2f);
            totalScore = "Score: "+ score;
            curTubeNumber++;
            System.out.println(score);
            if(curTubeNumber==4)
                curTubeNumber=0;
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,(cam.position.x - cam.viewportWidth/2),0);

        //scoreCard
        for(Tube tube : tubes) // all 4 tubes drawn here
        {
            sb.draw(tube.getTopTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);
            sb.draw(tube.getBotTube(),tube.getPosBotTube().x,tube.getPosBotTube().y);
        }
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y,Bird.BIRD_WIDTH,Bird.BIRD_HEIGHT);
        //sb.draw(ground,cam.position.x-cam.viewportWidth/2,GROUND_OFFSET); // only doing this seems like ground is not moving
        sb.draw(ground,ground1.x,ground1.y);
       sb.draw(ground,ground2.x,ground2.y);
        scoreCard.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        scoreCard.draw(sb,totalScore,cam.position.x - cam.viewportWidth/2+ 10,30);

       // sb.draw(flappy.getTexture(), flappy.getPosition().x, flappy.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube: tubes)
        {
            tube.dispose();
        }
        System.out.println("Play State Disposed");
    }
    private void updateGround()
    {
        if((cam.position.x-cam.viewportWidth/2 )> ground1.x + ground.getWidth())
        {
            ground1.add(ground.getWidth()*2,0); // adds on to the existing vector values
        }
        if((cam.position.x-cam.viewportWidth/2 )> ground2.x + ground.getWidth())
        {
            ground2.add(ground.getWidth()*2,0);
        }
    }
}
