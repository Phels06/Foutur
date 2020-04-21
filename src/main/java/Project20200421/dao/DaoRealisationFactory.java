package Project20200421.dao;

public class DaoRealisationFactory {

	private static DaoRealisation daoRealisation = null;

	public static DaoRealisation getInstance() {
		if (daoRealisation == null) {
			daoRealisation = new DaoRealisationJdbcImpl();
		}
		return daoRealisation;
	}




}
