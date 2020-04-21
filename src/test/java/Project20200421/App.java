package Project20200421;

import Project20200421.dao.DaoArticle;
import Project20200421.dao.DaoArticleFactory;
import Project20200421.model.Bluray;
import Project20200421.model.Dvd;

public class App {

	public static void main(String[] args) {
		
		DaoArticle daoArticle = DaoArticleFactory.getInstance();
		
		
		Dvd harrypotter = new Dvd(1,2,true);
		Bluray seigneur = new Bluray(2,2,true);
		
		daoArticle.insert(harrypotter);
		daoArticle.insert(seigneur);

	}

}
