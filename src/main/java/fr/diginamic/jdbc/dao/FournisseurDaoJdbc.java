package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {
//	Connection maConnection = null;
//	Statement stat = null;
	
	public FournisseurDaoJdbc() {
//		this.maConnection = maConnection;
	}

	// ----------------------------------------------- SELECT -----------------------------------------
	@Override
	public List<Fournisseur> extraire() {

		Connection maConnection = null;
		Statement stat = null;
		ResultSet res = null;
		ArrayList<Fournisseur> listFournisseur = new ArrayList<>();

		try {
			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");
			stat = maConnection.createStatement();

			res = stat.executeQuery("SELECT * FROM fournisseur");
			while (res.next()) {
				int id = res.getInt("ID");
				String nom = res.getString("NOM");

				Fournisseur f1 = new Fournisseur(id, nom);
				listFournisseur.add(f1);
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				if (maConnection != null && !maConnection.isClosed()) {
					res.close();
					stat.close();
					maConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listFournisseur;
	}

	
	// ----------------------------------------------- INSERT -----------------------------------------
	@Override
	public void insert(Fournisseur fournisseur) {

		Connection maConnection = null;
		Statement stat = null;
		
		try {
			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");
			stat = maConnection.createStatement();
			
			String nomSQL = fournisseur.getNom().replaceAll("'", "''");

			String requeteInsert = "INSERT INTO fournisseur (id, nom) VALUES (" + fournisseur.getId() + ", '" + nomSQL + "')";
			stat.executeUpdate(requeteInsert);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				if (maConnection != null && !maConnection.isClosed()) {
					stat.close();
					maConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	
	// ----------------------------------------------- UPDATE -----------------------------------------
	@Override
	public int update(String ancienNom, String nouveauNom) {

		Connection maConnection = null;
		Statement stat = null;
		int nbLigneModif = 0;

		try {
			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");
			stat = maConnection.createStatement();
			
			// Ou alors faire un preparedStatement avec les ? pour les valeurs puis un setString ou setInt pour mettre les valeurs puis executeUpdate
			String requeteUpdate = "UPDATE `fournisseur` SET `NOM`='" + nouveauNom + "' WHERE NOM = '" + ancienNom + "'";
			nbLigneModif = stat.executeUpdate(requeteUpdate);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				if (maConnection != null && !maConnection.isClosed()) {
					stat.close();
					maConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nbLigneModif;
	}

	
	// ----------------------------------------------- DELETE -----------------------------------------
	@Override
	public boolean delete(Fournisseur fournisseur) {

		Connection maConnection = null;
		Statement stat = null;
		
		int nbLigneDelete = 0;
		boolean valeurReturn = false;

		try {
			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");
			stat = maConnection.createStatement();
			
			String requeteInsert = "DELETE FROM fournisseur WHERE nom = '" + fournisseur.getNom() + "'";
			nbLigneDelete = stat.executeUpdate(requeteInsert);
			
			if(nbLigneDelete == 0) {
				valeurReturn = false;
			} else {
				valeurReturn = true;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			try {
				if (maConnection != null && !maConnection.isClosed()) {
					stat.close();
					maConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return valeurReturn;
	}

}
