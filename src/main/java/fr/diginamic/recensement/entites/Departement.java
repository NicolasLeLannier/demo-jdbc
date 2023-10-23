package fr.diginamic.recensement.entites;

public class Departement {

	protected int id;
	protected String code;
	
	/** Constructor
	 * @param code
	 */
	public Departement(String code) {
		super();
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "Region [id=" + id + ", code=" + code + "]";
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/** Setter
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
