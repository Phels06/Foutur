package Project20200421.dao;

public class DaoArticleFactory {

	// singleton
	
	private static DaoArticle daoArticle=null;
	
	public static DaoArticle getInstance() {
		if(daoArticle == null) {
		daoArticle = new DaoArticleJdbcImpl();
	}
		return daoArticle;
	}
}
