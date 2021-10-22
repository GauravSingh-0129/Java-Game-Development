package com.tutorial.main;

import java.awt.*;
import java.util.*;

public class MenuParticle extends GameObject{
    private Handler handler;
    Random r = new Random();
    
    private int red = r.nextInt(255);
    private int green = r.nextInt(255);
    private int blue = r.nextInt(255);
    private Color col;
    public MenuParticle(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velX = (r.nextInt(5 - -5) + -5);
        velY = (r.nextInt(5 - -5) + -5);
        if(velX == 0) velX = 1;
        if(velY == 0) velY = 1;
        col = new Color(red, green, blue);
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
            
        handler.addObject(new Trail(x, y, ID.Trail, col, 16, 16, 0.03f /* smaller the value bigger the trail */, handler));
    }

    public void render(Graphics g){
        g.setColor(col);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
