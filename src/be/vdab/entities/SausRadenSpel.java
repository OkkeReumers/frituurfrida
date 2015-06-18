package be.vdab.entities;

import java.io.Serializable;

public class SausRadenSpel implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int MAX_BEURTEN = 10;
	private final String saus;
	private final StringBuilder sausMetPuntjes;
	private int verkeerdeBeurten;
	public SausRadenSpel(String saus) {
		this.saus = saus;
		sausMetPuntjes = new StringBuilder(saus.length());
		for (int teller = 0; teller != saus.length(); teller++) {
			sausMetPuntjes.append('.');
		}
	}
	public void doeGok(char letter) {
		int letterIndex = saus.indexOf(letter);
		if (letterIndex == -1) {
			verkeerdeBeurten++;
		} else {
			do {
				sausMetPuntjes.setCharAt(letterIndex, letter);
				letterIndex = saus.indexOf(letter, letterIndex + 1);
			} while (letterIndex != -1);
		}
	}
	public String getSausMetPuntjes() {
		return sausMetPuntjes.toString();
	}
	public String getSaus() {
		return saus;
	}
	public int getVerkeerdeBeurten() {
		return verkeerdeBeurten;
	}
	public boolean isGewonnen() {
		return sausMetPuntjes.indexOf(".") == -1;
	}
	public boolean isVerloren() {
		return verkeerdeBeurten == MAX_BEURTEN;
	}
}