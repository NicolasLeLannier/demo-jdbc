package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.Utilisateur;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			DriverManager.registerDriver(new org.mariadb.jdbc.Driver());

		} catch (SQLException e) {
			System.err.println("Erreur lors de la connexion : " + e.getMessage());
			throw new RuntimeException(e);
		}

		Connection maConnection = null;
		Statement stat = null;
		ResultSet res = null;
		ArrayList<Fournisseur> listFournisseur = new ArrayList<>();

		try {
			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");
			stat = maConnection.createStatement();
			
			res = stat.executeQuery("SELECT * FROM fournisseur");
			while(res.next()) { // Déplacement dans les  lignes de résultats
				int id = res.getInt("ID");
				String  nom = res.getString("NOM");
				
				Fournisseur f1 = new Fournisseur(id, nom);
				listFournisseur.add(f1);
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {

			// Ferme la connection à chaque impérativement
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
		
		for(Fournisseur fournisseur : listFournisseur) {
			System.out.println(fournisseur);
		}
	}

}
