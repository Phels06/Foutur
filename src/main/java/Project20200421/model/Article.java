package Project20200421.model;

public abstract class Article {
	private Integer id;
	private Integer nbDisques;
	private Adherent emprunteur;
	private Film film;
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
	
>>>>>>> valentin
>>>>>>> 2dd41b85b1e5ca01713a61df920d632579f6c137
	
///////////////////////
	
	public Article() {
	}
	
	public Article(Integer id, Integer nbDisques, Adherent emprunteur, Film film) {
		this.id = id;
		this.nbDisques = nbDisques;
		this.emprunteur = emprunteur;
		this.film = film;
	}
	
	public Article(Integer id, Integer nbDisques) {
		this.id = id;
		this.nbDisques = nbDisques;
	}
	
	
//////////////////////////
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNbDisques() {
		return nbDisques;
	}
	public void setNbDisques(Integer nbDisques) {
		this.nbDisques = nbDisques;
	}
	public Adherent getEmprunteur() {
		return emprunteur;
	}
	public void setEmprunteur(Adherent emprunteur) {
		this.emprunteur = emprunteur;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emprunteur == null) ? 0 : emprunteur.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nbDisques == null) ? 0 : nbDisques.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (emprunteur == null) {
			if (other.emprunteur != null)
				return false;
		} else if (!emprunteur.equals(other.emprunteur))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nbDisques == null) {
			if (other.nbDisques != null)
				return false;
		} else if (!nbDisques.equals(other.nbDisques))
			return false;
		return true;
	}
}
