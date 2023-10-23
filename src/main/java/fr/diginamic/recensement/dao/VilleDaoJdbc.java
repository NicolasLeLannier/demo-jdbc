package fr.diginamic.recensement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.recensement.entites.Departement;
import fr.diginamic.recensement.entites.Ville;

public class VilleDaoJdbc implements VilleDAO {

	ResourceBundle conf = ResourceBundle.getBundle("conf");
	String url = conf.getString("url");
	String user = conf.getString("user");
	String pwd = conf.getString("password");
	
	// ----------------------------------------------- EXTRAIRE -----------------------------------------
		@Override
		public List<Ville> extraire() {
			
			Connection maConnection = null;
			Statement stat = null;
			ResultSet res = null;
			ArrayList<Ville> listVille = new ArrayList<>();

			try {
				
				maConnection = DriverManager.getConnection(url, user, pwd);
				
//				maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recensement", "root", "");
				stat = maConnection.createStatement();

				res = stat.executeQuery("SELECT * FROM ville");
				while (res.next()) {
					String nom = res.getString("NOM");
					int population = res.getInt("POPULATION");
					int idRegion = res.getInt("ID_REGION");
					int idDepartement = res.getInt("ID_DEPARTEMENT");

					Ville v1 = new Ville(nom, population, idRegion, idDepartement);
					listVille.add(v1);
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

			return listVille;
		}

		
		// ----------------------------------------------- INSERT -----------------------------------------
		@Override
		public void insert(Ville ville) {

			Connection maConnection = null;
			PreparedStatement stat = null;
			
			try {
//				maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recensement", "root", "");
				maConnection = DriverManager.getConnection(url, user, pwd);

				stat = maConnection.prepareStatement("INSERT INTO ville (`NOM`, `POPULATION`, `ID_DEPARTEMENT`, `ID_REGION`) VALUES (?, ?, ?, ?)");
				stat.setString(1, ville.getNom());
				stat.setInt(2, ville.getPopulation());
				stat.setInt(3, ville.getIdDepartement());
				stat.setInt(4, ville.getIdRegion());
				stat.executeUpdate();

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

		
//		// ----------------------------------------------- UPDATE -----------------------------------------
//		@Override
//		public int update(int ancienCode, int nouveauCode) {
//
//			Connection maConnection = null;
//			PreparedStatement stat = null;
//			int nbLigneModif = 0;
//
//			try {
//				maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recensement", "root", "");
//				
//
//				stat = maConnection.prepareStatement("UPDATE `departement` SET `CODE`= ? WHERE CODE = ?");
//				stat.setInt(1, nouveauCode);
//				stat.setInt(2, ancienCode);
//				nbLigneModif = stat.executeUpdate();
//
//			} catch (SQLException e) {
//				System.err.println(e.getMessage());
//				throw new RuntimeException(e);
//			} finally {
//				try {
//					if (maConnection != null && !maConnection.isClosed()) {
//						stat.close();
//						maConnection.close();
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			return nbLigneModif;
//			
//		}
//
//		
//		// ----------------------------------------------- DELETE -----------------------------------------
//		@Override
//		public boolean delete(Ville ville) {
//
//			Connection maConnection = null;
//			PreparedStatement stat = null;
//			
//			int nbLigneDelete = 0;
//			boolean valeurReturn = false;
//
//			try {
//				maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recensement", "root", "");
//				
//				stat = maConnection.prepareStatement("DELETE FROM departement WHERE code = ?");
//				stat.setInt(1, departement.getCode());
//				nbLigneDelete = stat.executeUpdate();
//				
//				if(nbLigneDelete == 0) {
//					valeurReturn = false;
//				} else {
//					valeurReturn = true;
//				}
//
//			} catch (SQLException e) {
//				System.err.println(e.getMessage());
//				throw new RuntimeException(e);
//			} finally {
//				try {
//					if (maConnection != null && !maConnection.isClosed()) {
//						stat.close();
//						maConnection.close();
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			
//			return valeurReturn;
//		}

}
