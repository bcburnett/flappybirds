package com.bcburnett.flappybirds.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.bcburnett.flappybirds.FlappyBirds;
import com.bcburnett.flappybirds.sprites.Bird;
import com.bcburnett.flappybirds.sprites.Tube;

/**
 * Created by brian on 12/26/17.
 */

public class PlayState extends State {
    public static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT = 4;
    private Bird bird;
    private Texture bg;
    private Tube tube;
    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm){
        super(gsm);
        bird = new Bird(50,400);
        cam.setToOrtho(false, FlappyBirds.WIDTH/2,FlappyBirds.HEIGHT/2);
        bg = new Texture("bg.png");
        tubes = new Array<Tube>();
        for(int i =0; i<TUBE_COUNT;i++){
            tubes.add(new Tube(i*(TUBE_SPACING+Tube.TUBE_WIDTH)));

        }
    }
    @Override
    public void handleInput() {
    if(Gdx.input.justTouched()) {
        bird.jump();
    }

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x+80;
        for(Tube tube: tubes){
            if(cam.position.x-(cam.viewportWidth/2)> tube.getPosTopTube().x+tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x+((Tube.TUBE_WIDTH + TUBE_SPACING)*TUBE_COUNT));
            }
            if (tube.collides(bird.getBounds()))
                gsm.set(new PlayState(gsm));
        }
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,cam.position.x - cam.viewportWidth/2,0);
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y);
        for(Tube tube: tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        sb.end();

    }

    @Override
    public void dispose() {

    }


}
