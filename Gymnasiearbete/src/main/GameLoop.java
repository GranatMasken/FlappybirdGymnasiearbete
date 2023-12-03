package main;

public class GameLoop {

	private float fps;
	long lastTime = System.nanoTime();
	double ns;
	double delta = 0;
	long timer = System.currentTimeMillis();
	int updates = 0;
	
	public GameLoop(float fps) {
		this.fps = fps;
		ns = 1000000000 / this.fps;
	}
	
	public boolean shouldUpdate() {
		boolean result = false;
		long now = System.nanoTime();
		delta += (now - lastTime) / ns;
		lastTime = now;
		while(delta >= 1){
			result = true;
			updates++;
			delta--;
		}
				
		if(System.currentTimeMillis() - timer > 1000){
			timer += 1000;
			System.out.println("FPS: " + updates);
			updates = 0;
		}
		
		return result;
	}
}
