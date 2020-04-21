package Project20200421;

<<<<<<< HEAD
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Project20200421.dao.DaoFilm;
import Project20200421.dao.DaoFilmFactory;
import Project20200421.model.Film;
=======
import Project20200421.dao.DaoArticle;
import Project20200421.dao.DaoArticleFactory;
import Project20200421.model.Bluray;
import Project20200421.model.Dvd;
>>>>>>> valentin

public class App {

	public static void main(String[] args) {
<<<<<<< HEAD
		DaoFilm daoFilm = DaoFilmFactory.getInstance();

		System.out.println("findAll:" + daoFilm.findAll());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Film f;
		try {
			f = new Film(1,"film test", sdf.parse("12/03/2020"));
			daoFilm.insert(f);
=======
		
		DaoArticle daoArticle = DaoArticleFactory.getInstance();
		
		
		Dvd harrypotter = new Dvd(1,2,true);
		Bluray seigneur = new Bluray(2,2,true);
		
		daoArticle.insert(harrypotter);
		daoArticle.insert(seigneur);
>>>>>>> valentin

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
