package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the kunde database table.
 * 
 */
@Entity
@NamedQuery(name="Kunde.findAll", query="SELECT k FROM Kunde k")
public class Kunde implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String nachname;

	private String vorname;

	//bi-directional many-to-one association to Buchungen
	@OneToMany(mappedBy="kunde")
	private List<Buchungen> buchungens;

	public Kunde() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNachname() {
		return this.nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public List<Buchungen> getBuchungens() {
		return this.buchungens;
	}

	public void setBuchungens(List<Buchungen> buchungens) {
		this.buchungens = buchungens;
	}

	public Buchungen addBuchungen(Buchungen buchungen) {
		getBuchungens().add(buchungen);
		buchungen.setKunde(this);

		return buchungen;
	}

	public Buchungen removeBuchungen(Buchungen buchungen) {
		getBuchungens().remove(buchungen);
		buchungen.setKunde(null);

		return buchungen;
	}

}