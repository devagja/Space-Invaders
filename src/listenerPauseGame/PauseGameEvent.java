package listenerPauseGame;

public class PauseGameEvent {
	boolean gamePaused;

	public PauseGameEvent(boolean gamePaused) {
		super();
		this.gamePaused = gamePaused;
	}

	public boolean isGamePaused() {
		return gamePaused;
	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}
	

}
