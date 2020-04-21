package Project20200421.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Film {
	private Integer id;
	private String titre;
	private Date dateDeSortie;
	private Set<Article> articles;
	private List<Realisation> realisations;

/////////////////////////////

	public Film() {

	}

	public Film(Integer id, String titre, Date dateDeSortie) {
		this.id = id;
		this.titre = titre;
		this.dateDeSortie = dateDeSortie;
	}

	public Film(Integer id, String titre, Date dateDeSortie, Set<Article> articles, List<Realisation> realisations) {
		this.id = id;
		this.titre = titre;
		this.dateDeSortie = dateDeSortie;
		this.articles = articles;
		this.realisations = realisations;
	}

//////////////////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDateDeSortie() {
		return dateDeSortie;
	}

	public void setDateDeSortie(Date dateDeSortie) {
		this.dateDeSortie = dateDeSortie;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
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
		result = prime * result + ((articles == null) ? 0 : articles.hashCode());
		result = prime * result + ((dateDeSortie == null) ? 0 : dateDeSortie.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((realisations == null) ? 0 : realisations.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
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
		Film other = (Film) obj;
		if (articles == null) {
			if (other.articles != null)
				return false;
		} else if (!articles.equals(other.articles))
			return false;
		if (dateDeSortie == null) {
			if (other.dateDeSortie != null)
				return false;
		} else if (!dateDeSortie.equals(other.dateDeSortie))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (realisations == null) {
			if (other.realisations != null)
				return false;
		} else if (!realisations.equals(other.realisations))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
}
