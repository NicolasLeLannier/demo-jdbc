package fr.diginamic.recensement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.recensement.entites.Departement;
import fr.diginamic.recensement.entites.Region;

public class RegionDaoJdbc implements RegionDAO {

	// ----------------------------------------------- EXTRAIRE -----------------------------------------
		@Override
		public List<Region> extraire() {
			
			Connection maConnection = null;
			Statement stat = null;
			ResultSet res = null;
			ArrayList<Region> listRegion = new ArrayList<>();

			try {
				maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recensement", "root", "");
				stat = maConnection.createStatement();

				res = stat.executeQuery("SELECT * FROM REGION");
				while (res.next()) {
					String nom = res.getString("NOM");

					Region r1 = new Region(nom);
					listRegion.add(r1);
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

			return listRegion;
		}

		
		// ----------------------------------------------- INSERT -----------------------------------------
		@Override
		public void insert(Region region) {

			Connection maConnection = null;
			PreparedStatement stat = null;
			
			try {
				maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recensement", "root", "");

				String nomSQL = region.getNom().replaceAll("'", "''");
				
				stat = maConnection.prepareStatement("INSERT INTO REGION (NOM) VALUES (?)");
				stat.setString(1, nomSQL);
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

		
		// ----------------------------------------------- UPDATE -----------------------------------------
		@Override
		public int update(String ancienNom, String nouveauNom) {

			Connection maConnection = null;
			PreparedStatement stat = null;
			int nbLigneModif = 0;

			try {
				maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recensement", "root", "");
				

				stat = maConnection.prepareStatement("UPDATE `region` SET `NOM`= ? WHERE NOM = ?");
				stat.setString(1, nouveauNom);
				stat.setString(2, ancienNom);
				nbLigneModif = stat.executeUpdate();

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
		public boolean delete(Region region) {

			Connection maConnection = null;
			PreparedStatement stat = null;
			
			int nbLigneDelete = 0;
			boolean valeurReturn = false;

			try {
				maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recensement", "root", "");
				
				stat = maConnection.prepareStatement("DELETE FROM departement WHERE NOM = ?");
				stat.setString(1, region.getNom());
				nbLigneDelete = stat.executeUpdate();
				
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
