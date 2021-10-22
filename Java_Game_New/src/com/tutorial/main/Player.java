package com.tutorial.main;

import java.util.Random;
import java.awt.*;

public class Player extends GameObject{
    
    Random r = new Random();
    Handler handler;
    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        //velX = r.nextInt(5)+1;
        //velY = r.nextInt(5);
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int) y, 32, 32);
    }
    public void tick(){
        x += velX;
        y += velY;
        
        x = Game.clamp((int)x, 0, Game.width-48);    /* bound the player in the window */
        y = Game.clamp((int)y, 0, Game.height-64);   /* bound the player in the window */
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.white, 32, 32, 0.15f, handler));
        collision();
    }
    
    private void collision(){
        for(int i=0; i<handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            if(temp.getID() == ID.BasicEnemy || temp.getID() == ID.FastEnemy || temp.getID() == ID.SmartEnemy){  /* Temporary Object is now the basic enemy */
                if(getBounds().intersects(temp.getBounds())){
                    //collision code
                    HUD.health -= 2;
                }
            }
            if(temp.getID() == ID.BossEnemy){
                if(getBounds().intersects(temp.getBounds())){
                    //collision code
                    HUD.health -= 200;
                }
            }
        }
    } 
    
    public void render(Graphics g){
        //Graphics2D g2d = (Graphics2D)g;
        //g2d.setColor(Color.red);
        //g2d.draw(getBounds());
        
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);
        //if(id == ID.Player2) g.setColor(Color.green);
    }
}