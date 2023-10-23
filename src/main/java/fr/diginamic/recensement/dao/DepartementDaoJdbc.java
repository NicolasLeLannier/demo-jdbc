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

public class DepartementDaoJdbc implements DepartementDAO {

	
	// ----------------------------------------------- EXTRAIRE -----------------------------------------
	@Override
	public List<Departement> extraire() {
		
		Connection maConnection = null;
		Statement stat = null;
		ResultSet res = null;
		ArrayList<Departement> listDepartement = new ArrayList<>();

		try {
			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recensement", "root", "");
			stat = maConnection.createStatement();

			res = stat.executeQuery("SELECT * FROM departement");
			while (res.next()) {
				String code = res.getString("CODE");

				Departement d1 = new Departement(code);
				listDepartement.add(d1);
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

		return listDepartement;
	}

	
	// ----------------------------------------------- INSERT -----------------------------------------
	@Override
	public void insert(Departement departement) {

		Connection maConnection = null;
		PreparedStatement stat = null;
		
		try {
			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recensement", "root", "");

			stat = maConnection.prepareStatement("INSERT INTO departement (code) VALUES (?)");
			stat.setString(1, departement.getCode());
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
	public int update(int ancienCode, int nouveauCode) {

		Connection maConnection = null;
		PreparedStatement stat = null;
		int nbLigneModif = 0;

		try {
			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recensement", "root", "");
			

			stat = maConnection.prepareStatement("UPDATE `departement` SET `CODE`= ? WHERE CODE = ?");
			stat.setInt(1, nouveauCode);
			stat.setInt(2, ancienCode);
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
	public boolean delete(Departement departement) {

		Connection maConnection = null;
		PreparedStatement stat = null;
		
		int nbLigneDelete = 0;
		boolean valeurReturn = false;

		try {
			maConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/recensement", "root", "");
			
			stat = maConnection.prepareStatement("DELETE FROM departement WHERE code = ?");
			stat.setString(1, departement.getCode());
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
