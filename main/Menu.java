package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			AudioPlayer.getMusic("love").play();
			
			//PLAY BUTTON
			if (mouseOver(mx, my, 190, 135, 220, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));	
				AudioPlayer.getSound("svamp").play();
				AudioPlayer.getMusic("music").loop();
			}
			//QUIT BUTTON
			if (mouseOver(mx, my, 190, 335, 220, 64)) {
				System.exit(0);
			}
			//HELP BUTTON
			if (mouseOver(mx, my, 190, 235, 220, 64)) {
			   game.gameState = STATE.Help;
			   AudioPlayer.getSound("menu_sound").play();
			   
			}
			
		}
		
		
	 //BACK BUTTON FÖR HELP
	if(game.gameState == STATE.Help) {
		if(mouseOver(mx, my, 200, 350, 250, 70 )) {
			game.gameState = STATE.Menu;
			AudioPlayer.getSound("menu_sound").play();
			return;
		}
		}		
	//"PLAY AGAIN" KNAPPEN EFTER "GAME OVER"
	if(game.gameState == STATE.End) {
		if(mouseOver(mx, my, 173, 315, 320, 74)) {
			hud.setLevel(1);
			hud.setScore(0);
			game.gameState = STATE.Game;
			handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32, ID.Player, handler));
			handler.clearEnemys();
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));	
			AudioPlayer.getSound("svamp").play();
		}
		}		
	}
	
	public void mouseReleased (MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			}
			else return false;		
		}
		else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("Ishmeria", 1, 50);
			Font fnt2 = new Font("Ishmeria", 1, 30);
			Font fnt3 = new Font("Ishmeria", 1, 37);
			
			g.setFont(fnt3);
			g.setColor(Color.RED);
			g.drawString("WELLCOME TO AN ADVENTURES GAME", 10, 50);
			
			g.setFont(fnt);
			g.setColor(Color.RED);
			g.drawString("MENU", 240, 120);
			
			g.setFont(fnt2);
			g.drawRect(190, 135, 220, 64);
			g.setColor(Color.WHITE);
			g.drawString("PLAY", 265, 180);
			
			g.setColor(Color.RED);
			g.drawRect(190, 235, 220, 64);
			g.setColor(Color.WHITE);
			g.drawString("HELP", 265, 280);
			
			g.setColor(Color.RED);
			g.drawRect(190, 335, 220, 64);
			g.setColor(Color.WHITE);
		    g.drawString("QUIT", 265, 380);
		}
		else if(game.gameState == STATE.Help) {
			Font fnt = new Font("Ishmeria", 1, 70);
			Font fnt2 = new Font("Ishmeria", 1, 28);
			Font fnt3 = new Font("Ishmeria", 1, 40);
			Font fnt4 = new Font("Ishmeria", 1, 23);
			
			g.setFont(fnt);
			g.setColor(Color.RED);
			g.drawString("INSTRUCTIONS", 80, 70);
			
			g.setFont(fnt2);
			g.setColor(Color.ORANGE);
			g.drawString("USE W-A-S-D TO CONTROL YOUR CHARACTER", 10, 125);
			
			g.setFont(fnt3);
			g.setColor(Color.GREEN);
			g.drawString("DODGE STUFF", 200, 180);
			
			g.setFont(fnt3);
			g.setColor(Color.BLUE);
			g.drawString("EAT DONUTS", 208, 230);
			
			g.setFont(fnt3);
			g.setColor(Color.MAGENTA);
			g.drawString("MAY THE FORCE BE WITH YOU", 30, 280);
			
			g.setFont(fnt3);
			g.setColor(Color.RED);
			g.drawString("GAME BY ARASTO", 170, 330);
			
			g.setFont(fnt2);
			g.drawRect(200, 350, 250, 70);
			g.setColor(Color.RED);
		    g.drawString("RETURN TO MENU", 210, 395);
			
		}
		else if (game.gameState == STATE.End) {
			Font fnt = new Font("Ishmeria", 1, 100);
			Font fnt2 = new Font("Ishmeria", 1, 30);	
			Font fnt3 = new Font("Ishmeria", 1, 50);
			Font fnt4 = new Font("Ishmeria", 1, 60);	
			
			g.setFont(fnt);
			g.setColor(Color.RED);
			g.drawString("GAME OVER", 60, 100);
			
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawString("You have been defeated! Your Score: ", 10, 180);
			
			g.setFont(fnt4);
			g.setColor(Color.white);
			g.drawString("" + hud.getScore(), 250, 250);
			
			g.setFont(fnt3);
			g.drawRect(173, 315, 320, 74);
			g.setColor(Color.RED);
			g.drawString("Play Again?", 180, 370);
		}
		
	}
}
