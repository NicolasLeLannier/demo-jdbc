package fr.diginamic.recensement.entites;

public class Region {

	protected int id;
	protected String nom;
	
	/** Constructor
	 * @param id
	 * @param nom
	 */
	public Region(String nom) {
		super();
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Region [id=" + id + ", nom=" + nom + "]";
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
	
	
}
