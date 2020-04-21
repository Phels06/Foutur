package Project20200421.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Project20200421.context.Context;
import Project20200421.model.Adherent;
import Project20200421.model.Adresse;
import Project20200421.model.Civilite;


public class DaoAdherentjbdcImpl implements DaoAdherent {
	
	@Override
	public void insert(Adherent obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"insert into adherent(id,prenom,nom,numero,rue,codepostal,ville,civilite) values(nextval('seq_adherent'),?,?,?,?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
			ps.setInt(3, obj.getAdresse().getNumero());
			ps.setString(4, obj.getAdresse().getRue());
			ps.setString(5, obj.getAdresse().getCodePostal());
			ps.setString(6, obj.getAdresse().getVille());
			if (obj.getCivilite() != null) {
				ps.setString(7, obj.getCivilite().toString());
			} else {
				ps.setNull(7, Types.VARCHAR);
			}
			ps.executeUpdate();
			//creation d'un constructeur sans l'id pour Adhérent
			Context.getInstance().getConnection().commit();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getInt(1));
			}
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
	public Adherent update(Adherent obj) {
		try (PreparedStatement ps = Context.getInstance().getConnection().prepareStatement(
				"update adherent set prenom=?, nom=?, numero=?,rue=?,codepostal=?,ville=?,civilite=? where id=?")) {
			ps.setString(1, obj.getPrenom());
			ps.setString(2, obj.getNom());
			ps.setInt(3, obj.getAdresse().getNumero());
			ps.setString(4, obj.getAdresse().getRue());
			ps.setString(5, obj.getAdresse().getCodePostal());
			ps.setString(6, obj.getAdresse().getVille());
			if (obj.getCivilite() != null) {
				ps.setString(7, obj.getCivilite().toString());
			} else {
				ps.setNull(7, Types.VARCHAR);
			}
			ps.setInt(8, obj.getId());
			ps.executeUpdate();
			//creation d'un constructeur sans l'id pour Adhérent
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
	public void delete(Adherent obj) {
		deleteById(obj.getId());
	}

	
	@Override
	public void deleteById(Integer key) {
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("delete from adherent where id=?")) {
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
	public List<Adherent> findAll() {
		List<Adherent> adherents = new ArrayList<Adherent>();
		Adherent adherent = null;
		Adresse adresse = null;
		try (Statement st = Context.getInstance().getConnection().createStatement()) {
			ResultSet rs = st.executeQuery("select * from adherent");
			while (rs.next()) {
				adresse = new Adresse(rs.getInt("numero"),rs.getString("rue"),rs.getString("codepostal"),rs.getString("ville"));
				adherent = new Adherent(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"), adresse);
				if (rs.getString("civilite") != null) {
					adherent.setCivilite(Civilite.valueOf(rs.getString("civilite")));
				}
				adherents.add(adherent);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return adherents;
	}

	
	
	
	@Override
	public Optional<Adherent> findByKey(Integer key) {
		Adherent adherent = null;
		try (PreparedStatement ps = Context.getInstance().getConnection()
				.prepareStatement("select * from adherent where id=?")) {
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				adherent = new Adherent(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Context.destroy();
		return Optional.ofNullable(adherent);
	}





}
