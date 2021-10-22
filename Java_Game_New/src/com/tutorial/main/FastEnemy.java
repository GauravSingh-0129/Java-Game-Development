package com.tutorial.main;

import java.awt.*;

public class FastEnemy extends GameObject{
    private Handler handler;
    
    public FastEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velX = 8;
        velY = 8;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    public void tick(){
        x+= velX;
        y += velY;
        
        if(x<=0 || x>=Game.width-48)    /* bound the enemy in the window */
            velX *= -1;
        if(y<=0 || y>=Game.height-64)   /* bound the enemy in the window */
            velY *= -1;
            
        handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 16, 16, 0.03f /* smaller the value bigger the trail */, handler));
    }

    public void render(Graphics g){
        g.setColor(Color.cyan);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}

