package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDelete {

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
//		ResultSet res = null;

		try {
			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");
			stat = maConnection.createStatement();
			
			String requeteInsert = "DELETE FROM fournisseur WHERE nom = 'La Maison des Peintures'";
			stat.executeUpdate(requeteInsert);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {

			// Ferme la connection à chaque impérativement
			try {
				if (maConnection != null && !maConnection.isClosed()) {
//					res.close();
					stat.close();
					maConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
