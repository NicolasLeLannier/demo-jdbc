package fr.diginamic.recensement.dao;

import java.util.List;

import fr.diginamic.recensement.entites.Departement;
import fr.diginamic.recensement.entites.Region;
import fr.diginamic.recensement.entites.Ville;

public interface VilleDAO {

	List<Ville> extraire();
	void insert(Ville fournisseur);
//	int update(String ancienNom, String nouveauNom);
//	boolean delete(Ville fournisseur);
}
