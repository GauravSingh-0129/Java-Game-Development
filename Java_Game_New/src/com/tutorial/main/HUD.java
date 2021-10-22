package com.tutorial.main;

import java.awt.*;

public class HUD {
	public static float health = 100;
    private float greenVal = 255; /* to convert health-bar from green to red & 255 bec. RGB max val = 255 */
    
    private int score = 0;
    private int level = 1;
    
    public void tick(){        
        health = Game.clamp(health, 0, 100); /* does not go down past 0 */
        greenVal = Game.clamp(greenVal, 0, 255);
        greenVal = health*2;
        score++;    /* Increment Score */
    }
    
    public void render(Graphics g){
        g.setColor(Color.gray); /* Health bar */
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, (int)greenVal, 0)); /* respective R-G-B values */
        //g.setColor(Color.green);
        g.fillRect(15, 15, (int)health*2, 32);   /* Actual health */
        
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
        
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
    }
    
    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }
    public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }
}