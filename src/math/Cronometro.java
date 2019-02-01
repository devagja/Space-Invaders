package math;

public class Cronometro {
	private long delta, lastTime, time;
	private boolean running;

	public Cronometro() {
		super();
		this.delta = 0;
		this.lastTime = 0;
		this.running = false;
	}

	public void run(long time) {
		running = true;
		this.time = time;
	}
           
	public void update() {
		if (running) {
			delta += System.currentTimeMillis() - lastTime;
		}
		if (delta >= time) {
			running = false;
			delta = 0;
		}
		lastTime = System.currentTimeMillis();
	}
	
	public boolean isRunning() {
		return running;
	}

}
