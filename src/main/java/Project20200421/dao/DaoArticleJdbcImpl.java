package Project20200421.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Project20200421.model.Adherent;
import Project20200421.model.Article;
import Project20200421.model.Bluray;
import Project20200421.model.Dvd;
import Project20200421.model.Film;
import Project20200421.context.Context;

public class DaoArticleJdbcImpl implements DaoArticle {

	@Override
	public void insert(Article obj) {

		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement(
						"insert into article(id, nbDisques, bonus, troisd, id_adherent, id_film) "
								+ "values (nextval('seq_article'),?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS)) {

			if (obj.getNbDisques() != null) {
				ps.setInt(1, obj.getNbDisques());
			} else {
				ps.setNull(1, Types.INTEGER);
			}

			if (obj instanceof Dvd) {
				if (((Dvd) obj).getBonus() != null) {
					ps.setBoolean(2, true);
					ps.setBoolean(3, false);
				} else {
					ps.setNull(2, Types.BOOLEAN);
				}

			} else if (obj instanceof Bluray) {
				if (((Bluray) obj).getTroisD() != null) {
					ps.setBoolean(2, false);
					ps.setBoolean(3, true);
				} else {
					ps.setNull(3, Types.BOOLEAN);
				}
			}

			if (((Article) obj).getEmprunteur() != null) {
				ps.setInt(4, ((Dvd) obj).getEmprunteur().getId());
			} else {
				ps.setNull(4, Types.INTEGER);
			}

			if (((Article) obj).getFilm() != null) {
				ps.setInt(5, ((Dvd) obj).getFilm().getId());
			} else {
				ps.setNull(5, Types.INTEGER);
			}

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getInt(1));
			}
			Context.getInstance().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Context.getInstance().getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		Context.destroy();
	}

	
	@Override
	public Article update(Article obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("update article set nbDisques=?, bonus=?, troisd=?, id_adherent=?, id_film=?  where id=?")) {

			if (obj.getNbDisques() != null) {
				ps.setInt(1, obj.getNbDisques());
			} else {
				ps.setNull(1, Types.INTEGER);
			}

			if (obj instanceof Dvd) {
				if (((Dvd) obj).getBonus() != null) {
					ps.setBoolean(2, true);
					ps.setBoolean(3, false);
				} else {
					ps.setNull(2, Types.BOOLEAN);
				}

			} else if (obj instanceof Bluray) {
				if (((Bluray) obj).getTroisD() != null) {
					ps.setBoolean(2, false);
					ps.setBoolean(3, true);
				} else {
					ps.setNull(3, Types.BOOLEAN);
				}
			}
			
			if (((Article) obj).getEmprunteur() != null) {
				ps.setInt(4, ((Dvd) obj).getEmprunteur().getId());
			} else {
				ps.setNull(4, Types.INTEGER);
			}

			if (((Article) obj).getFilm() != null) {
				ps.setInt(5, ((Dvd) obj).getFilm().getId());
			} else {
				ps.setNull(5, Types.INTEGER);
			}

			ps.setInt(6, obj.getId());
			ps.executeUpdate();
			Context.getInstance().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Context.getInstance().getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		Context.destroy();
		return obj;
	}

	@Override
	public void delete(Article obj) {
		deleteById(obj.getId());
	}

	@Override
	public void deleteById(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from article where id=?")) {
			ps.setInt(1, key);
			ps.executeUpdate();
			Context.getInstance().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Context.getInstance().getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		Context.destroy();
	}
	

	@Override
	public List<Article> findAll() {
		List<Article> articles = new ArrayList<Article>();
		Article a = null;
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery("select a.id as id, a.nbdisques, a.bonus, a.troisd, ad.id_adherent, f.id_film"
											+ "from article a left join adherent ad on ad.id_adherent=a.id" 
											+ "left join film f on f.id_film=a.id"
											+ "where a.id=?");

			while (rs.next()) {
				if (rs.getBoolean("bonus") == true) {
					a = new Dvd(rs.getInt("id"), rs.getInt("nbDisques"), rs.getBoolean("bonus"));

				} else if (rs.getBoolean("troisd") == true) {
					a = new Bluray(rs.getInt("id"), rs.getInt("nbDisques"), rs.getBoolean("troisd"));
				}
				
				if (rs.getInt("ad.id_adherent")!=0) {
					((Article)a).setEmprunteur(new Adherent(rs.getInt("id_adherent")));
				}
				if (rs.getInt("f.id_film")!=0) {
					((Article)a).setFilm(new Film(rs.getInt("id_film")));
				}
				
			}
			articles.add(a);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return articles;
	}

	
	@Override
	public Optional<Article> findByKey(Integer key) {
		Article a = null;
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("select a.id as id, a.nbdisques, a.bonus, a.troisd, ad.id_adherent, f.id_film"
								+ "from article a left join adherent ad on ad.id_adherent=a.id" 
								+ "from article a left join film f on f.id_film=a.id"
								+ "where a.id=?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("bonus") == true) {
					a = new Dvd(rs.getInt("id"), rs.getInt("nbdisques"), rs.getBoolean("bonus"));
					
				} else if (rs.getBoolean("troisd") == true) {
					a = new Bluray(rs.getInt("id"), rs.getInt("nbdisques"), rs.getBoolean("troisd"));
				}
			
				if (rs.getInt("ad.id_adherent")!=0) {
					((Article)a).setEmprunteur(new Adherent(rs.getInt("id_adherent")));
				}
				if (rs.getInt("f.id_film")!=0) {
					((Article)a).setFilm(new Film(rs.getInt("id_film")));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(a);
	}
}
