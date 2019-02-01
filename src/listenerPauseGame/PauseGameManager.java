package listenerPauseGame;

import java.util.ArrayList;
import java.util.List;

public class PauseGameManager {

	public static List<PauseGameListener> pauseGameListeners = new ArrayList<PauseGameListener>();

	public static void addEndGameListener(PauseGameListener newListener) {
		pauseGameListeners.add(newListener);
	}

	public static void removeEndGameListener(PauseGameListener newListener) {
		pauseGameListeners.remove(newListener);
	}

	public static void fireEndGameListeners(boolean isPaused) {
		for (PauseGameListener pauseGameListener : pauseGameListeners) {
			pauseGameListener.pause(new PauseGameEvent(isPaused));
		}
	}
}
