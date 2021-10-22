package com.tutorial.main;
import java.awt.*;
import java.util.ArrayList;

public class Handler{ /* loop all objects of our games, immediately render & update them */
    ArrayList<GameObject> object = new ArrayList<GameObject>();
    GameObject temp;
    
    public void tick(){     /* Updating individual objects */
        for(int i=0; i<object.size(); i++){
            temp = object.get(i);
            temp.tick();
        }
    }
    public void render(Graphics g){ /* Rendering individual objects */
        for(int i=0; i<object.size(); i++){
        	temp = object.get(i);
        	temp.render(g);
        }
    }
    public void clearEnemies(){
        for(int i=0; i<object.size(); i++){
            GameObject temp = object.get(i);
            if(temp.getID() != ID.Player){
                object.clear();
                if(Game.gameState != Game.STATE.End)
                    addObject(new Player((int)temp.getX(), (int)temp.getY(), ID.Player, this));
            }
        }
    }
    public void addObject(GameObject object){   /* Adding object to LL */
        this.object.add(object);
    }
    public void removeObject(GameObject object){    /* Removing object to LL */
        this.object.remove(object);
    }
}