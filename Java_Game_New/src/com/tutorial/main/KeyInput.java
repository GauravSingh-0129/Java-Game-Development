package com.tutorial.main;

import java.awt.event.*;

public class KeyInput extends KeyAdapter{
    
    public Handler handler;
    Game game;
    private boolean[] keyDown = new boolean[4]; /* for handling sticky keys */
    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
        
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i=0; i<handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            
            if(temp.getID() == ID.Player){  /* KeyEvents for Player 01 */
                if(key == KeyEvent.VK_W) { temp.setVelY(-12); keyDown[0] = true; }
                if(key == KeyEvent.VK_S) { temp.setVelY(12); keyDown[1] = true; }
                if(key == KeyEvent.VK_D) { temp.setVelX(12); keyDown[2] = true; }
                if(key == KeyEvent.VK_A) { temp.setVelX(-12); keyDown[3] = true; }
            }
        }
        if(key == KeyEvent.VK_P) {
        	if(Game.gameState == Game.STATE.Game) {
        		if(Game.paused) Game.paused = false;
            	else Game.paused = true;
        	}
        }
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i=0; i<handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            
            if(temp.getID() == ID.Player){  /* KeyEvents for Player 01 */
                if(key == KeyEvent.VK_W) keyDown[0] = false;    //temp.setVelY(0);
                if(key == KeyEvent.VK_S) keyDown[1] = false;    //temp.setVelY(0);
                if(key == KeyEvent.VK_D) keyDown[2] = false;    //temp.setVelX(0);
                if(key == KeyEvent.VK_A) keyDown[3] = false;    //temp.setVelX(0);
                
                // vertical movement
                if(!keyDown[0] && !keyDown[1]) temp.setVelY(0);
                // horizontal movement
                if(!keyDown[2] && !keyDown[3]) temp.setVelX(0);
            }
            //if(temp.getID() == ID.Player2){  /* KeyEvents for Player 01 */
                //if(key == KeyEvent.VK_UP) temp.setVelY(0);
                //if(key == KeyEvent.VK_DOWN) temp.setVelY(0);
                //if(key == KeyEvent.VK_RIGHT) temp.setVelX(0);
                //if(key == KeyEvent.VK_LEFT) temp.setVelX(0);
            //}
        }
    }
}