package main;

import java.awt.Color;
import java.awt.Graphics2D;
import static main.GamePanel.*;

public class Pipe {
	
	int xPos, yPos, width, height, gap;
	double speed;
	
	Pipe( int y, int x, int g, double s){
		
		xPos = x;
		yPos = y;
		speed = s;
		width = 50;
		gap = g;
		
	}
	
	void render(Graphics2D g2d) {
		g2d.setColor(Color.green);
		g2d.fillRect(xPos-width, yPos, width, (HEIGHT-yPos));
		g2d.fillRect(xPos-width, 0, width, yPos-gap);
		
	}
	void update() {
		xPos -= speed;
		System.out.println(xPos + "   " +  speed);
		System.out.println(gap);

	}
}
