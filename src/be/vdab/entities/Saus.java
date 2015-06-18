package be.vdab.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Saus implements Serializable {

	private static final long serialVersionUID = 1L;
	private long nummer;
	private String naam;
	private List<String> ingredienten = new ArrayList<>();
	
	public Saus(long nummer, String naam, Iterable<String> ingredienten) {
		this.nummer = nummer;
		this.naam = naam;
		for (String ingredient : ingredienten) {
			this.ingredienten.add(ingredient);
		}
	}
	
	public long getNummer() {
		return nummer;
	}
	
	public void setNummer(long nummer) {
		this.nummer = nummer;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public List<String> getIngredienten() {
		return ingredienten;
	}
	
	public void addIngredient(String ingredient) {
		ingredienten.add(ingredient);
	}
	
	
	
}
