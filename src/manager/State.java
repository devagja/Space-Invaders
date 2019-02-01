package manager;

import java.util.ArrayList;
import java.util.List;

import gameObject.Enemy;
import gameObject.GameObject;
import listener.FinOleadaEvent;
import listener.FinOleadaListener;
import states.GameState;

public class State implements FinOleadaListener {
	private List<FinOleadaListener> finOleadaListeners = new ArrayList<FinOleadaListener>();

	public void addFinOleadaListener(FinOleadaListener listener) {
		finOleadaListeners.add(listener);
	}

	public void removeFinOleadaListener(FinOleadaListener listener) {
		finOleadaListeners.remove(listener);

	}

	@Override
	public void finOleada(FinOleadaEvent event) {
		new ArrayList<FinOleadaEvent>();
	}

	private boolean finOleadaEnemigos() {
		for (GameObject finOleadaListener : GameState.getObjetos()) {
			if (finOleadaListener instanceof Enemy) {
				return false;
			}

		}
		return true;
	}
}
