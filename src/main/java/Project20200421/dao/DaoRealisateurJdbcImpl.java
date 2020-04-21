package Project20200421.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Project20200421.context.Context;
import Project20200421.model.Realisateur;

public class DaoRealisateurJdbcImpl implements DaoRealisateur {

	@Override
	public void insert(Realisateur obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into realisateur(id, nom, prenom) values(nextval('seq_realisateur'), ?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
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
	public Realisateur update(Realisateur obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("update realisateur set prenom=?, nom=? where id=?")) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
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
	public void delete(Realisateur obj) {
		deleteById(obj.getId());

	}

	@Override
	public void deleteById(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from realisateur where id=?")) {
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
	public List<Realisateur> findAll() {
		List<Realisateur> realisateurs = new ArrayList<Realisateur>();
		Realisateur rea = null;
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery("select * from realisateur");
			while (rs.next()) {
				rea = new Realisateur(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"));
				realisateurs.add(rea);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return realisateurs;
	}

	@Override
	public Optional<Realisateur> findByKey(Integer key) {
		Realisateur rea = null;
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("select * from realisateur where id=?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			rea = new Realisateur(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(rea);
	}

}
