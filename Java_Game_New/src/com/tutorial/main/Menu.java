package com.tutorial.main;

import java.awt.event.*;
import java.util.Random;
import java.awt.*;

public class Menu extends MouseAdapter{
    private Handler handler;
    private Random r = new Random();
    private HUD hud;
    Game game;
    public Menu (Game game, Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }
    public void mousePressed(MouseEvent e){ 
        int mx = e.getX();
        int my = e.getY();
        
        if(Game.gameState == Game.STATE.Menu){
            // play button
            if(mouseOver(mx, my, 540, 150, 200, 64)){
            	Game.gameState = Game.STATE.Select;
            	return;
            }
            // help button
            if(mouseOver(mx, my, 540, 250, 200, 64)){
                Game.gameState = Game.STATE.Help;
                return;
            }
            // quit button
            if(mouseOver(mx, my, 540, 350, 200, 64)){
                System.exit(1);
                return;
            }
        }else if(Game.gameState == Game.STATE.Help){
        	// back button for help
        	if(mouseOver(mx, my, 540, 350, 200, 64)){
                Game.gameState = Game.STATE.Menu;
                return;
            }
        }else if(Game.gameState == Game.STATE.End){
        	// try again button
        	if(mouseOver(mx, my, 540, 350, 200, 64)){
                Game.gameState = Game.STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
                return;
            }
        }else if(Game.gameState == Game.STATE.Select){
            // normal game button
            if(mouseOver(mx, my, 540, 150, 200, 64)){
                Game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.width/2 - 32, Game.height/2 - 32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt(Game.width)-50, r.nextInt(Game.height)-50, ID.BasicEnemy, handler));
                game.diff = 0;
            }
            // hard game button
            if(mouseOver(mx, my, 540, 250, 200, 64)){
                Game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.width/2 - 32, Game.height/2 - 32, ID.Player, handler));
                handler.clearEnemies();
                handler.addObject(new HardEnemy(r.nextInt(Game.width)-50, r.nextInt(Game.height)-50, ID.BasicEnemy, handler));
                game.diff = 1;
            }
            // back button
            if(mouseOver(mx, my, 540, 350, 200, 64)){
            	Game.gameState = Game.STATE.Menu;
                return;
            }
        }
    }
    public void mouseReleased(MouseEvent e){
        
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x+width){
            if(my > y && my < y+height){
                return true;
            } else return false;
        }return false;
    } 
    
    public void tick(){
        
    }
    public void render(Graphics g){
        if(Game.gameState == Game.STATE.Menu){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Slip-Through", 490, 64);
            
            g.setFont(fnt2);
            g.drawRect(540, 150, 200, 64);
            g.drawString("Play", 605, 190); 
            
            g.drawRect(540, 250, 200, 64);
            g.drawString("Help", 605, 290);
            
            g.drawRect(540, 350, 200, 64);
            g.drawString("Quit", 605, 390);
        }else if(Game.gameState == Game.STATE.Select){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Select Difficulty", 480, 64);
            
            g.setFont(fnt2);
            g.drawRect(540, 150, 200, 64);
            g.drawString("Normal", 595, 190); 
            
            g.drawRect(540, 250, 200, 64);
            g.drawString("Hard", 605, 290);
            
            g.drawRect(540, 350, 200, 64);
            g.drawString("Back", 605, 390);
        }else if(Game.gameState == Game.STATE.Help){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
            
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 580, 64);
            
            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player & dodge enemies.", 400, 200);
            
            g.setFont(fnt2);
            g.drawRect(540, 350, 200, 64);
            g.drawString("Back", 605, 390);
        }else if(Game.gameState == Game.STATE.End){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
            
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 500, 64);
            
            g.setFont(fnt3);
            g.drawString("You died at the Score of : " + hud.getScore(), 500, 200);
            
            g.setFont(fnt2);
            g.drawRect(540, 350, 200, 64);
            g.drawString("Try Again", 570, 390);
        }
    }
} 