package Project20200421.model;

import Project20200421.model.Article;

public class Dvd extends Article{
	private Boolean bonus;

///////////////////////////

	public Dvd() {
	}

	public Dvd(Integer id, Integer nbDisques, Adherent emprunteur, Film film, Boolean bonus) {
		super(id, nbDisques, emprunteur, film);
		this.bonus = bonus;
	}
	
	
	

	public Dvd(Integer id, Integer nbDisques, Boolean bonus) {
		super(id, nbDisques);
		this.bonus = bonus;
	}

//////////////////////////////////

	public Boolean getBonus() {
		return bonus;
	}

	public void setBonus(Boolean bonus) {
		this.bonus = bonus;
	}
}
