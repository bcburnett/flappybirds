package com.bcburnett.flappybirds.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bcburnett.flappybirds.FlappyBirds;

/**
 * Created by brian on 12/26/17.
 */

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    public MenuState(GameStateManager gsm){
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }
    @Override
    public void handleInput() {
    if(Gdx.input.justTouched()){
        gsm.set(new PlayState(gsm));
        dispose();
    }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
    sb.begin();
        sb.draw(background,0,0, FlappyBirds.WIDTH,FlappyBirds.HEIGHT);
        sb.draw(playBtn,(FlappyBirds.WIDTH/2) - (playBtn.getWidth()/2),(FlappyBirds.HEIGHT/2) - (playBtn.getHeight()/2));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
