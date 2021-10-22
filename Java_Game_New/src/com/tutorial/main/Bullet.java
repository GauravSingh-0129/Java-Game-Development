package com.tutorial.main;

import java.awt.*;
import java.util.*;

public class Bullet extends GameObject{
    private Handler handler;
    Random r = new Random();
    
    public Bullet(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velX = (r.nextInt(5 - -5) + -5);
        velY = 5;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    public void tick(){
        x+= velX;
        y += velY;
        /*
        if(x<=0 || x>=Game.width-48)    // bound the enemy in the window
            velX *= -1;
        if(y<=0 || y>=Game.height-64)   // bound the enemy in the window
            velY *= -1;
        */
        if(y >= Game.height) handler.removeObject(this);
        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.03f /* smaller the value bigger the trail */, handler));
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
