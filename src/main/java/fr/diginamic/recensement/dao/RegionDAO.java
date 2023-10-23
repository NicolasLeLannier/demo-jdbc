package fr.diginamic.recensement.dao;

import java.util.List;

import fr.diginamic.recensement.entites.Departement;
import fr.diginamic.recensement.entites.Region;

public interface RegionDAO {

	List<Region> extraire();
	void insert(Region region);
	int update(String ancienNom, String nouveauNom);
	boolean delete(Region region);
}
