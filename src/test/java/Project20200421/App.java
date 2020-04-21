package Project20200421;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import Project20200421.dao.DaoFilm;
import Project20200421.dao.DaoFilmFactory;
import Project20200421.model.Film;

public class App {

	public static void main(String[] args) {
		DaoFilm daoFilm = DaoFilmFactory.getInstance();

		System.out.println("findAll:" + daoFilm.findAll());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Film f;
		try {
			f = new Film(1,"film test", sdf.parse("12/03/2020"));
			daoFilm.insert(f);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
