package Project20200421.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Project20200421.context.Context;
import Project20200421.model.Film;
import Project20200421.model.Realisateur;
import Project20200421.model.Realisation;

public class DaoRealisationJdbcImpl implements DaoRealisation {

	@Override
	public void insert(Realisation obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into film(id_film, id_realisateur) values( ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, obj.getFilm().getId());
			ps.setInt(2, obj.getRealisateur().getId());
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
	public Realisation update(Realisation obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("update realisation set id_realisateur=? where id_film=?")) {
			ps.setInt(1, obj.getRealisateur().getId());
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
	public void delete(Realisation obj) {
		deleteById(obj.getFilm().getId());
	}

	@Override
	public void deleteById(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from realisation where id_film=?")) {
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
	public Optional<Realisation> findByKey(Integer key) {
		Realisation r = null;
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("select r.id_realisateur as id_realisteur, "
						+ "f.titre as titre, f.date_de_sortie as date_de_sortie, "
						+ "rea.prenom as prenom, rea.nom as nom " + "from realisation r "
						+ "left join film f f.id=r.id_film " + "left join realisateur rea rea.id=r.id_realisateur"
						+ "where r.id_film=?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			r = new Realisation(new Film(rs.getInt("r.id_film"), rs.getString("titre"), rs.getDate("date_de_sortie")),
					new Realisateur(rs.getInt("id_realisateur"), rs.getString("prenom"), rs.getString("nom")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(r);
	}

	@Override
	public List<Realisation> findAll() {
		List<Realisation> realisations = new ArrayList<Realisation>();
		Realisation r = null;
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery("select r.id_film as id_film, r.id_realisateur as id_realisteur, "
					+ "f.titre as titre, f.date_de_sortie as date_de_sortie, " + "rea.prenom as prenom, rea.nom as nom "
					+ "from realisation r " + "left join film f f.id=r.id_film "
					+ "left join realisateur rea rea.id=r.id_realisateur");
			while (rs.next()) {
				r = new Realisation(
						new Film(rs.getInt("r.id_film"), rs.getString("titre"), rs.getDate("date_de_sortie")),
						new Realisateur(rs.getInt("id_realisateur"), rs.getString("prenom"), rs.getString("nom")));
				realisations.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return realisations;
	}
}
