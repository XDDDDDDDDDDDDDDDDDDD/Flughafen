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

	//bi-directional many-to-one association to Fluege
	@ManyToOne
	@JoinColumn(name="fluegeID")
	private Fluege fluege;

	//bi-directional many-to-one association to Kunde
	@ManyToOne
	@JoinColumn(name="kundeID")
	private Kunde kunde;

	public Buchungen() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fluege getFluege() {
		return this.fluege;
	}

	public void setFluege(Fluege fluege) {
		this.fluege = fluege;
	}

	public Kunde getKunde() {
		return this.kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

}