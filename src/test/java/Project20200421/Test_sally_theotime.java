package Project20200421;


import Project20200421.dao.DaoAdherent;
import Project20200421.dao.DaoAdherentFactory;
import Project20200421.model.Adherent;
import Project20200421.model.Adresse;
import Project20200421.model.Civilite;


public class Test_sally_theotime {

	public static void main(String[] args) {
		Adresse addresse1 = new Adresse(1, "de la rue", "75005", "Paris");
		Adresse addresse2 = new Adresse(1, "de la rue", "14000", "Caen");
		Adresse addresse3 = new Adresse(1, "de la rue", "78000", "Poissy");
		Adresse addresse4 = new Adresse(1, "de la rue", "78000", "Verneuil");
		
		
		Adherent adherent1 = new Adherent("Sally", "XXX", addresse1,Civilite.MLLE);
		Adherent adherent2 = new Adherent("Cédric", "XXX", addresse2,Civilite.M);
		Adherent adherent3 = new Adherent("Valentin", "XXX", addresse3,Civilite.M);
		Adherent adherent4 = new Adherent("theotime", "desgrouas", addresse4,Civilite.M);

//		Adherent adherentModif5 = new Adherent(111,"theotime", "desgrouas", addresse4,Civilite.M);
		
		DaoAdherent daoAdherent = DaoAdherentFactory.getInstance();

		daoAdherent.insert(adherent1);
		daoAdherent.insert(adherent2);
		daoAdherent.insert(adherent3);
		daoAdherent.insert(adherent4);
		
		
//		daoAdherent.update(adherentModif5);
//		daoAdherent.deleteById(111);
		
		System.out.println(daoAdherent.findAll());
		System.out.println(daoAdherent.findByKey(112));
		
	}

}
