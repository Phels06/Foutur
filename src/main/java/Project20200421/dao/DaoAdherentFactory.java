package Project20200421.dao;

public class DaoAdherentFactory {
	private static DaoAdherent singleton = null;

	public static DaoAdherent getInstance() {
		if (singleton == null) {
			singleton = new DaoAdherentjbdcImpl();
		}
		return singleton;
	}
	
}
