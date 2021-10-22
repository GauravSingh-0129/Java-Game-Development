package com.tutorial.main;

import java.awt.*;

public abstract class GameObject {	/* All the game objects */
	protected float x, y;
    protected ID id;
    protected float velX, velY;
    
    public GameObject(float x, float y, ID id){
        this.x= x;
        this.y= y;
        this.id= id;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();  /* Collision handling in the game. Contains intersects() method. It returns 'true' on contact else 'false' */
    
    public void setX(float x){
        this.x = x;
    }
    public float getX(){
        return x;
    }
    public void setY(float y){
        this.y = y;
    }
    public float getY(){
        return y;
    }
    public void setID(ID id){
        this.id = id;
    }
    public ID getID(){
        return id;
    }
    public void setVelX(float velX){
        this.velX = velX;
    }
    public float getVelX(){
        return velX;
    }
    public void setVelY(float velY){
        this.velY = velY;
    }
    public float getVelY(){
        return velY;
    }
}