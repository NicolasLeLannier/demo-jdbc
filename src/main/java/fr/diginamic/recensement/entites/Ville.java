package fr.diginamic.recensement.entites;

public class Ville {
	
	protected int id;
	protected String nom;
	protected int population;
	protected int idRegion;
	protected int idDepartement;
	
	/** Constructor
	 * @param id
	 * @param nom
	 * @param population
	 * @param idRegion
	 * @param idDepartement
	 */
	public Ville(String nom, int population, int idRegion, int idDepartement) {
		super();
		this.nom = nom;
		this.population = population;
		this.idRegion = idRegion;
		this.idDepartement = idDepartement;
	}
	
	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", population=" + population + ", idRegion=" + idRegion
				+ ", idDepartement=" + idDepartement + "]";
	}

	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/** Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/** Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/** Getter
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}
	/** Setter
	 * @param population the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}
	/** Getter
	 * @return the idRegion
	 */
	public int getIdRegion() {
		return idRegion;
	}
	/** Setter
	 * @param idRegion the idRegion to set
	 */
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}
	/** Getter
	 * @return the idDepartement
	 */
	public int getIdDepartement() {
		return idDepartement;
	}
	/** Setter
	 * @param idDepartement the idDepartement to set
	 */
	public void setIdDepartement(int idDepartement) {
		this.idDepartement = idDepartement;
	}
}
