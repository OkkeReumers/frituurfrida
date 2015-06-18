package be.vdab.entities;

import java.io.Serializable;
import java.util.Random;

public class ZoekDeFrietSpel implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int AANTAL_DEUREN = 7;
	private final int deurMetFriet = new Random().nextInt(AANTAL_DEUREN);
	private final boolean[] deurIsOpen = new boolean[AANTAL_DEUREN];
	
	public void openDeur(int volgNummer){
		deurIsOpen[volgNummer] = true;
	}
	
	public int getDeurMetFriet() {
		return deurMetFriet;
	}
	
	public boolean[] getDeurIsOpen() {
		return deurIsOpen;
	}

}
