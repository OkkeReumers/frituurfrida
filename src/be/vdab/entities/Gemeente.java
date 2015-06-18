package be.vdab.entities;

import java.io.Serializable;

public class Gemeente implements Serializable {

	private static final long serialVersionUID = 1L;
	private String naam;
	private int postcode;
	
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	
}
