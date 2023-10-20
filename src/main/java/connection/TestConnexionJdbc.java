package connection;

import java.sql.Statement;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnexionJdbc {

	public static void main(String[] args) {

		try {
			DriverManager.registerDriver(new org.mariadb.jdbc.Driver());

		} catch (SQLException e) {
			System.err.println("Erreur lors de la connexion : " + e.getMessage());
//			System.exit(0);

			// Stop l'appli ici
			throw new RuntimeException(e);
		}

		Connection maConnection = null;
		Statement stat = null;
		ResultSet res = null;
		ArrayList<Utilisateur> listeUtilisateur = new ArrayList<>();
		
		try {
			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/compta", "root", "");
//			System.out.println("Connexion à la base de donnée réussis : " + maConnection);

			
			// Requête SQL
			stat = maConnection.createStatement();
			
			
			String requete = "INSERT INTO utilisateurs (nom, prenom) values ('LE LANNIER', 'Nicolas')";
			stat.executeUpdate(requete);
			
			String requete1 = "INSERT INTO utilisateurs (nom, prenom) values ('LE LANNIER', 'Nicolas')";
			stat.executeUpdate(requete1);
			
			String requete2 = "INSERT INTO utilisateurs (nom, prenom) values ('LE LANNIER', 'Nicolas')";
			stat.executeUpdate(requete2);
			
			
			
			
			res = stat.executeQuery("SELECT * FROM utilisateurs");
			while(res.next()) { // Déplacement dans les  lignes de résultats
				int id = res.getInt("ID");
				String  nom = res.getString("NOM");
				String  prenom = res.getString("PRENOM");
				
//				System.out.println(id + " " + nom + " " + prenom);
				Utilisateur u1 = new Utilisateur(id, nom, prenom);
				listeUtilisateur.add(u1);
			}
			
			
//			String requete = "INSERT INTO utilisateurs (nom, prenom) values ('LE LANNIER', 'Nicolas')";
//			String requete = "DELETE FROM utilisateurs";
//			int nbLignes = stat.executeUpdate(requete);
//			System.out.println(nbLignes);
		
			
			
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
		
		for (Utilisateur u : listeUtilisateur) {
			System.out.println(u);
		}
	}

}
