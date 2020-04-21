package Project20200421.dao;

import Project20200421.dao.DaoFilm;
import Project20200421.dao.DaoFilmJdbcImpl;

public class DaoFilmFactory {

	private static DaoFilm daoFilm = null;

	public static DaoFilm getInstance() {
		if (daoFilm == null) {
			daoFilm = new DaoFilmJdbcImpl();
		}
		return daoFilm;
	}


}
