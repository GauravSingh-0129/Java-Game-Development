package com.tutorial.main;

import java.util.*;

public class Spawn{
    
    private Handler handler;
    private HUD hud;
    Game game;
    private int scoreKeep = 0;
    private Random r = new Random();
    
    public Spawn(Handler handler,HUD hud, Game game){
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }
    
    public void tick(){ /* Either score system or time quantum system */
        scoreKeep++;
        if(scoreKeep >= 500){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel()+1);
            // continuous addition of enemies at each new level
            if(game.diff == 0) {
            	if((hud.getLevel() >= 2 && hud.getLevel() <= 3)||(hud.getLevel() >= 7 && hud.getLevel() <= 9))
                  	handler.addObject(new BasicEnemy(r.nextInt(Game.width-50), r.nextInt(Game.height-50), ID.BasicEnemy, handler));
                if(hud.getLevel() == 4 || hud.getLevel() == 6)
                    handler.addObject(new FastEnemy(r.nextInt(Game.width-50), r.nextInt(Game.height-50), ID.FastEnemy, handler));
                if(hud.getLevel() == 5)
                  	handler.addObject(new SmartEnemy(r.nextInt(Game.width-50), r.nextInt(Game.height-50), ID.SmartEnemy, handler));
                if(hud.getLevel() == 10){
                    handler.clearEnemies();
                    handler.addObject(new SmartEnemy(r.nextInt(Game.width-50), r.nextInt(Game.height-50), ID.SmartEnemy, handler));
                    handler.addObject(new BossEnemy((Game.width/2)-48, -100, ID.BossEnemy, handler));
                    }
            }else if(game.diff == 1) {
            	if((hud.getLevel() >= 2 && hud.getLevel() <= 3)||(hud.getLevel() >= 7 && hud.getLevel() <= 9))
                  	handler.addObject(new HardEnemy(r.nextInt(Game.width-50), r.nextInt(Game.height-50), ID.BasicEnemy, handler));
                if(hud.getLevel() == 4 || hud.getLevel() == 6)
                    handler.addObject(new FastEnemy(r.nextInt(Game.width-50), r.nextInt(Game.height-50), ID.FastEnemy, handler));
                if(hud.getLevel() == 5)
                  	handler.addObject(new SmartEnemy(r.nextInt(Game.width-50), r.nextInt(Game.height-50), ID.SmartEnemy, handler));
                if(hud.getLevel() == 10){
                    handler.clearEnemies();
                    handler.addObject(new SmartEnemy(r.nextInt(Game.width-50), r.nextInt(Game.height-50), ID.SmartEnemy, handler));
                    handler.addObject(new BossEnemy((Game.width/2)-48, -100, ID.BossEnemy, handler));
                    }
            }
          }
    }
}