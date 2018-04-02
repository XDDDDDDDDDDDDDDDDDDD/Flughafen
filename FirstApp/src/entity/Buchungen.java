package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the buchungen database table.
 * 
 */
@Entity
@NamedQuery(name="Buchungen.findAll", query="SELECT b FROM Buchungen b")
public class Buchungen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int fluegeID;

	private int kundeID;

	public Buchungen() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFluegeID() {
		return this.fluegeID;
	}

	public void setFluegeID(int fluegeID) {
		this.fluegeID = fluegeID;
	}

	public int getKundeID() {
		return this.kundeID;
	}

	public void setKundeID(int kundeID) {
		this.kundeID = kundeID;
	}

}