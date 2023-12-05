package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 600, HEIGHT = 600, DELAY = (int) (1000 / 60), PIPEDISTANCE = 400;

	public static boolean running = false;
	public static boolean over = false;

	Timer timer;

	public static int score = 0;

	Random rand = new Random();

	Bird b;
	Pipe p1;
	Pipe p2;
	Pipe p3;

	double pipeSpeed;
	int pipeGap;

	GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(this);

		pipeSpeed = 3;

		newPipeGap();

		b = new Bird();
		p1 = new Pipe(rand.nextInt(pipeGap + 10, HEIGHT - 10), WIDTH, pipeGap, pipeSpeed);
		p2 = new Pipe(rand.nextInt(pipeGap + 10, HEIGHT - 10), WIDTH + PIPEDISTANCE, pipeGap, pipeSpeed);
		p3 = new Pipe(rand.nextInt(pipeGap + 10, HEIGHT - 10), WIDTH + 2 * PIPEDISTANCE, pipeGap, pipeSpeed);

		startGame();
	}

	@Override
	public void paint(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if (running) {
			update();

			// draw bird and pipes
			b.render(g2d);
			p1.render(g2d);
			p2.render(g2d);
			p3.render(g2d);

			// draw text points
			g2d.setColor(Color.white);
			g2d.drawString("Score: " + score, 10, 10);

			// System.out.println("paint");
		} else if (over) {
			g2d.setColor(Color.white);

			g2d.drawString("GAME OVER", WIDTH / 2, (HEIGHT / 2) - 20);

			g2d.drawString("Score: " + score, WIDTH / 2, HEIGHT / 2);
		} else {
			g2d.setColor(Color.white);

			g2d.drawString("PRESS KEY TO START", WIDTH / 2 - 100, (HEIGHT / 2) - 20);
		}
	}

	public void update() {
		collisionCheck();
		b.update();
		pipeUpdate();
	}

	public void startGame() {
		timer = new Timer(DELAY, this);

		timer.setInitialDelay(DELAY);
		timer.start();

		// running = true;
	}

	public void pipeUpdate() {

		p1.update();
		p2.update();
		p3.update();

		// updatera position
		if (p1.xPos <= 0) {
			// ypos mellan 10 och HEIGHT-(10+gap)
			score++;
			p1.yPos = rand.nextInt(pipeGap + 10, HEIGHT - 10);
			p1.xPos = (p3.xPos + PIPEDISTANCE);
			newPipeGap();
			p1.gap = pipeGap;

		}
		if (p2.xPos <= 0) {
			score++;
			p2.yPos = rand.nextInt(pipeGap + 10, HEIGHT - 10);
			p2.xPos = (p1.xPos + PIPEDISTANCE);
			newPipeGap();
			p2.gap = pipeGap;

		}
		if (p3.xPos <= 0) {
			score++;
			p3.yPos = rand.nextInt(pipeGap + 10, HEIGHT - 10);
			p3.xPos = (p2.xPos + PIPEDISTANCE);
			newPipeGap();
			p3.gap = pipeGap;

		}
	}

	void newPipeGap() {
		pipeGap = (int) (200 * Math.exp(-0.01 * score) + 100);
	}

	void collisionCheck() {
		if (b.xPos + 5 >= p1.xPos - p1.width - 60 && b.xPos + 5 <= p1.xPos
				&& (b.yPos + 5 <= p1.yPos - p1.gap || b.yPos + 5 + 35 > p1.yPos)) {
			System.out.println("collision");
			running = false;
			over = true;
		} else if (b.xPos + 5 >= p2.xPos - p2.width - 60 && b.xPos + 5 <= p2.xPos
				&& (b.yPos + 5 <= p2.yPos - p2.gap || b.yPos + 5 + 35 > p2.yPos)) {
			System.out.println("collision");
			running = false;
			over = true;
		} else if (b.xPos + 5 >= p3.xPos - p3.width - 60 && b.xPos + 5 <= p3.xPos
				&& (b.yPos + 5 <= p3.yPos - p3.gap || b.yPos + 5 + 35 > p3.yPos)) {
			System.out.println("collision");
			running = false;
			over = true;

		}
	}

	void gameOver() {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (running) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				b.jump();
			}
		} else
			running = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
	}

}
