package main;
import static main.GamePanel.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {

	int xPos;
	int yPos;
	double speed;
	double g;
	
	Image fågel;
	
	Bird(){
		
	    try {                
	        fågel = ImageIO.read(new File("src/main/Fågel.png"));
	     } catch (IOException ex) {
	          // handle exception...
	    	 System.out.println(ex);
	     }
	    
	    System.out.println(fågel.getWidth(null) + "        " + fågel.getHeight(null));
	    
		fågel = fågel.getScaledInstance(307/4, 184/4, Image.SCALE_DEFAULT);
		
		xPos = WIDTH/3;
		yPos = WIDTH/2;
		
		g = 0.5;
		
	}
		
	void render(Graphics2D g2d){
		
		
		g2d.setColor(Color.cyan);
		
		g2d.fillRect(xPos + 5, yPos+5, 60, 35);
		
		g2d.drawImage(fågel, xPos, yPos, null);
		


		//g2d.drawImage(fågel, xpos, ypos, null);
	}
	void update() {
		yPos += speed;
		speed += g;		
	}
	void jump() {
		speed = -10;
	}
	
}
