package Project20200421.model;


import Project20200421.model.Article;



public class Bluray extends Article {
	private Boolean troisD;

///////////////////////////////////

	public Bluray() {
	}

	public Bluray(Integer id, Integer nbDisques, Adherent emprunteur, Film film, Boolean troisD) {
		super(id, nbDisques, emprunteur, film);
		this.troisD = troisD;
	}
	

public Bluray(Integer id, Integer nbDisques, Boolean troisD) {
		super(id, nbDisques);
		this.troisD = troisD;
	}

////////////////////////

	public Boolean getTroisD() {
		return troisD;
	}

	public void setTroisD(Boolean troisD) {
		this.troisD = troisD;
	}

}
