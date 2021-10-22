package com.tutorial.main;

import java.awt.*;
import java.util.*;

public class BossEnemy extends GameObject{
    private Handler handler;
    Random r = new Random();
    
    private int timer = 80;
    private int timer2 = 50;
    
    public BossEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velX = 0;
        velY = 5;
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    public void tick(){
        x+= velX;
        y += velY;
        
        timer--;
        if(timer <= 0) {
            velY = 0;
            timer2--; 
        }
        else timer--;
        
        if(timer2 <=0) {
            if(velX == 0) velX = 4;
            if(velX > 0) velX += 0.005f;
            if(velX < 0) velX -= 0.005f;
            velX = Game.clamp(velX, -8, 8);
            
            int spawn = r.nextInt(20);
            if(spawn == 0) handler.addObject(new Bullet((int)x+48, (int)y+48, ID.BasicEnemy, handler));
        }
        
        if(x<=0 || x>=Game.width-96)    // bound the enemy in the window
            velX *= -1;
        /*
        if(y<=0 || y>=Game.height-64)   // bound the enemy in the window
            velY *= -1;
        */ 
            
        // handler.addObject(new Trail(x, y, ID.Trail, Color.red, 96, 96, 0.03f /* smaller the value bigger the trail */, handler));
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 96, 96);
    }
}
