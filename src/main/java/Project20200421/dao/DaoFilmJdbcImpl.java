package Project20200421.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Project20200421.context.Context;
import Project20200421.model.Film;

public class DaoFilmJdbcImpl implements DaoFilm {

	@Override
	public void insert(Film obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into film(id, titre, date_de_sortie) values(nextval('seq_videoclub'), ?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, obj.getTitre());
			if (obj.getDateDeSortie() != null) {
				ps.setDate(2, new Date(obj.getDateDeSortie().getTime()));
			} else {
				ps.setNull(2, Types.DATE);
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
	public Film update(Film obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("update film set titre=?, date_de_sortie=? where id=?")) {
			ps.setString(1, obj.getTitre());
			if (obj.getDateDeSortie() != null) {
				ps.setDate(2, new Date(obj.getDateDeSortie().getTime()));
			} else {
				ps.setNull(2, Types.DATE);
			}
			ps.setInt(3, obj.getId());
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
	public void delete(Film obj) {
		deleteById(obj.getId());
	}

	@Override
	public void deleteById(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from film where id=?")) {
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
	public Optional<Film> findByKey(Integer key) {
		Film f = null;
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("select id, titre, date_de_sortie from film" + "where id=?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			f = new Film(rs.getInt("id"), rs.getString("titre"), rs.getDate("date_de_sortie"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(f);
	}

	@Override
	public List<Film> findAll() {
		List<Film> films = new ArrayList<Film>();
		Film f = null;
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery("select id, titre, date_de_sortie from film");
			while (rs.next()) {
				f = new Film(rs.getInt("id"), rs.getString("titre"), rs.getDate("date_de_sortie"));
				films.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return films;
	}

	public String findRealisateur(String titre) {
		String r = null;
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("select r.prenom as prenom, r.nom as nom, "
						+ "from film f left join realisation rea on f.id=rea.id_film "
						+ "left join realisateur r on rea.id_realisateur=r.id where f.id=?")) {
			ps.setString(1, titre);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				r = rs.getString("prenom") + " " + rs.getString("nom");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return r;
	}

//	public Set<Article> findArticle() {
//		Set<Article> set = new HashSet<Article>();
//		Article a = null;
//		try (Statement st = Context.getInstance().getConnection().createStatement()) {
//			ResultSet rs = st.executeQuery("select a.id as id, a.nb_disques as nb_disques, "
//					+ "from article a left join film f on f.id=a.id_film ");
//			if (rs.next()) {
//				a = new Article(rs.getInt("id"),rs.get("nom");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		Context.destroy();
//		return set;
//	}

}
