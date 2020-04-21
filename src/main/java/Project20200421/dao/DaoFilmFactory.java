package Project20200421.dao;

public class DaoFilmFactory {

	private static DaoFilm daoFilm = null;

	public static DaoFilm getInstance() {
		if (daoFilm == null) {
			daoFilm = new DaoFilmJdbcImpl();
		}
		return daoFilm;
	}


}
