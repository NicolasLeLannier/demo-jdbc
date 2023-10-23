package fr.diginamic.recensement.essaie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import fr.diginamic.recensement.dao.DepartementDAO;
import fr.diginamic.recensement.dao.DepartementDaoJdbc;
import fr.diginamic.recensement.dao.RegionDAO;
import fr.diginamic.recensement.dao.RegionDaoJdbc;
import fr.diginamic.recensement.dao.VilleDAO;
import fr.diginamic.recensement.dao.VilleDaoJdbc;
import fr.diginamic.recensement.entites.Departement;
import fr.diginamic.recensement.entites.Region;
import fr.diginamic.recensement.entites.Ville;

public class TestRecensement {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		long start = System.currentTimeMillis();
		
		int enlevePremierLigne = 0;

		// Chemin du fichier
		Path pathFichierDeBase = Paths
				.get("C:/temp/TP 16 Manipulation de fichier/tp 16 - recensement population 2016.csv");

		// Initialisations des listes
		List<String> lectureFichier = Files.readAllLines(pathFichierDeBase);
		DepartementDAO depDAO = new DepartementDaoJdbc();
		RegionDAO regDao = new RegionDaoJdbc();
		VilleDAO villeDao = new VilleDaoJdbc();
		List<Ville> listeVille = new ArrayList<>();

		Collections.sort(lectureFichier);

		// Ajout des données que l'on veux dans la liste listeVilleDeBase d'après la
		// classe Recensement
		for (String afficherFichier : lectureFichier) {
			String[] tokens = afficherFichier.split(";");
			if (enlevePremierLigne != 0) {
				tokens[9] = tokens[9].replaceAll("\\s", "");

				if (depDAO.extraire().isEmpty()) {
					DepartementDAO depDaoUnique = new DepartementDaoJdbc();
					depDaoUnique.insert(new Departement(tokens[2]));
				}
				
				if (regDao.extraire().isEmpty()) {
					RegionDAO regDaoUnique = new RegionDaoJdbc();
					regDaoUnique.insert(new Region(tokens[1]));
				}
				
//				if (villeDao.extraire().isEmpty()) {
					ResourceBundle conf = ResourceBundle.getBundle("conf");
					String url = conf.getString("url");
					String user = conf.getString("user");
					String pwd = conf.getString("password");

					Connection maConnection = null;
					Statement stat = null;
					ResultSet res = null;
					int idDepartement = 0;
					int idRegion = 0;
					try {
						maConnection = DriverManager.getConnection(url, user, pwd);

						stat = maConnection.createStatement();
						res = stat.executeQuery("SELECT ID FROM departement WHERE CODE = '" + tokens[2] + "'");

						while (res.next()) {
							idDepartement = res.getInt("ID");
						}
						res.close();
						
						
						res = stat.executeQuery("SELECT ID FROM region WHERE NOM = '" + tokens[1] + "'");

						while (res.next()) {
							idRegion = res.getInt("ID");
						}
						
						listeVille.add(new Ville(tokens[6], Integer.parseInt(tokens[9]), idRegion, idDepartement));
						
						res.close();
						stat.close();
						maConnection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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

					for(Ville v : listeVille) {
						VilleDAO villeDaoUnique = new VilleDaoJdbc();
						villeDaoUnique.insert(v);
					}
					
//				}

			} else {
				enlevePremierLigne++;
			}
		}

		long fin = System.currentTimeMillis();
		
		System.out.println("Temps d'execution : " + (fin-start));
	}

}
