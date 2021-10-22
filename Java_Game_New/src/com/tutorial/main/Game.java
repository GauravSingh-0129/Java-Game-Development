package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.*;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 7154398223659329361L;
	public static final int width = 1280, height = 720;
    private Thread thread;
    private boolean running = false;
    public static boolean paused = false;
    public int diff = 0; // 0-normal & 1-hard
    
    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    
    public enum STATE{
        Menu, Help, Game, End, Select
    };
    
    public static STATE gameState = STATE.Menu;
    
    public Game(){
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        
        new Window(width, height, "Build a game!", this);   /* start -> run -> render -> handler. So handler object is created first */
        spawner = new Spawn(handler, hud, this);
        r = new Random();
        
        if(gameState == STATE.Game){
            handler.addObject(new Player(width/2 - 32, height/2 - 32, ID.Player, handler));
            handler.addObject(new FastEnemy(r.nextInt(width)-50, r.nextInt(height)-50, ID.BasicEnemy, handler));
        } else if(gameState == STATE.Menu){
            for(int i=0; i<20; i++)
                handler.addObject(new MenuParticle(r.nextInt(width), r.nextInt(height), ID.MenuParticle, handler));
        }
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running= true;
    }
    
    public synchronized void stop(){
        try{
            thread.join();  /* Killing the thread */
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void run(){  /* Game Loop ***Don't Memorize*** */
        this.requestFocus(); /* don't have to click the frame every time we run the game */
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/ amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime)/ ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS " + frames);
                frames = 0;
            }
        }
        stop();
    }
    private void tick(){
        if(gameState == STATE.Game){
        	if(!paused) {
        		handler.tick();
        		hud.tick();
                spawner.tick();
                
                if(HUD.health <= 0){
                    HUD.health = 100;
                    gameState = STATE.End;
                    handler.clearEnemies();
                    for(int i=0; i<20; i++)
                        handler.addObject(new MenuParticle(r.nextInt(width), r.nextInt(height), ID.MenuParticle, handler));
                }
        	}
        } else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select){
        	menu.tick();
        	handler.tick();
        }
    }
    private void render(){
    	BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
    
        handler.render(g);
        
        if(paused) {
        	g.setColor(Color.white);
        	g.drawString("PAUSED", 610, 350);
        }

        if(gameState == STATE.Game){
            hud.render(g); /* 1st render all the objects & then render HUD putting HUD on top of other elements **depth concept** */
        }else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
            menu.render(g);
        }
        g.dispose();
        bs.show();
    }
    
    public static float clamp(float var, float min, float max){ /* To keep the player in the frame */
        if(var >= max) return var = max;
        else if(var <= min) return var = min;
        else return var;
    }
    
    public static void main(String args[]){
        new Game();
    }
}