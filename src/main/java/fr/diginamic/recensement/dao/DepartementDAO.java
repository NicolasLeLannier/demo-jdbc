package fr.diginamic.recensement.dao;

import java.util.List;

import fr.diginamic.recensement.entites.Departement;

public interface DepartementDAO {

	List<Departement> extraire();
	void insert(Departement departement);
	int update(int ancienCode, int nouveauCode);
	boolean delete(Departement departement);
}
