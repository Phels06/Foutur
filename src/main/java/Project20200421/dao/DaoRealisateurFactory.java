package Project20200421.dao;

public class DaoRealisateurFactory {

	private static DaoRealisateur daoRealisateur = null;

	public static DaoRealisateur getInstance() {
		if (daoRealisateur == null) {
			daoRealisateur = new DaoRealisateurJdbcImpl();
		}
		return daoRealisateur;
	}




}
