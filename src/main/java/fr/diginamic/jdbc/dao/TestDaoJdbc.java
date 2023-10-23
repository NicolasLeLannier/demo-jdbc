package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDaoJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FournisseurDao fournisseurDao = null;
		Connection connection = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");
			fournisseurDao = new FournisseurDaoJdbc();
		}
		catch(ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
		
		fournisseurDao.insert(new Fournisseur(5, "France de matériaux"));
		for(Fournisseur f : fournisseurDao.extraire()) {
			System.out.println("Nom du fournisseur : " + f.getNom());
		}
		
		System.out.println(fournisseurDao.update("France de matériaux", "France matériaux"));
		for(Fournisseur f : fournisseurDao.extraire()) {
			System.out.println("Nom du fournisseur : " + f.getNom());
		}
		
		System.out.println(fournisseurDao.delete(new Fournisseur(5, "France matériaux")));
		for(Fournisseur f : fournisseurDao.extraire()) {
			System.out.println("Nom du fournisseur : " + f.getNom());
		}
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
