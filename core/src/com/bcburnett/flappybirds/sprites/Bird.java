package com.bcburnett.flappybirds.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by brian on 12/26/17.
 */

public class Bird {
    public static final int GRAVITY = -15;
    public static final int MOVEMENT = 100;
    private Rectangle bounds;
    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;


    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bird = new Texture("bird.png");
        bounds = new Rectangle(x,y,bird.getWidth(),bird.getHeight());
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setVelocity(Vector3 velocity) {
        this.velocity = velocity;
    }

    public void update(float dt){
        if(position.y >0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT*dt,velocity.y,0);
        velocity.scl(1/dt);
        if(position.y < 0){
            position.y=0;
        }
        bounds.setPosition(position.x,position.y);

    }

    public void jump(){velocity.y =250;}

    public Vector3 getPosition() {return position;}

    public Texture getBird() {return bird;}

    public Rectangle getBounds() {return bounds;}


}
