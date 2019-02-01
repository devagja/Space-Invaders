package listenerEndGame;

import java.util.ArrayList;
import java.util.List;

public class EndGameManager {

	public static List<EndGameListener> endGameListeners = new ArrayList<EndGameListener>();

	public static void addEndGameListener(EndGameListener newListener) {
		endGameListeners.add(newListener);
	}

	public static void removeEndGameListener(EndGameListener newListener) {
		endGameListeners.remove(newListener);
	}

	public static void fireEndGameListeners(boolean isEnded) {
		for (EndGameListener endGameListener : endGameListeners) {
			endGameListener.finJuego(new EndGameEvent(isEnded));
		}
	}
}
