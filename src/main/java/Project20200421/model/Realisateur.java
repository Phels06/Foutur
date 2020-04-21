package Project20200421.model;

import java.util.List;

public class Realisateur {
	private Integer id;
	private String prenom;
	private String nom;
	private List<Realisation> realisations;

//////////////////////////////

	public Realisateur() {
	}

	public Realisateur(Integer id, String prenom, String nom, List<Realisation> realisations) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.realisations = realisations;
	}
	
	public Realisateur(Integer id, String prenom, String nom) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
	}

////////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Realisation> getRealisations() {
		return realisations;
	}

	public void setRealisations(List<Realisation> realisations) {
		this.realisations = realisations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((realisations == null) ? 0 : realisations.hashCode());
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
		Realisateur other = (Realisateur) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (realisations == null) {
			if (other.realisations != null)
				return false;
		} else if (!realisations.equals(other.realisations))
			return false;
		return true;
	}
}
