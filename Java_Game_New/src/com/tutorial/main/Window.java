package com.tutorial.main;

import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.*;

public class Window extends Canvas{
	
	private static final long serialVersionUID = -7259108873705494293L;

	public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);   /* Creating a frame of the game using inbuilt Java class */
        
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   /* For the x button to work properly else the thread will keep running even on exiting out */
        frame.setResizable(false);  /* Non-re sizable window */
        frame.setLocationRelativeTo(null);  /* Where will the window open (center) */
        frame.add(game);    /* Adding game class to the frame */
        frame.setVisible(true);
        game.start();
    }
}
