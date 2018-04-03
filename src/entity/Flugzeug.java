package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the flugzeug database table.
 * 
 */
@Entity
@NamedQuery(name="Flugzeug.findAll", query="SELECT f FROM Flugzeug f")
public class Flugzeug implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String code;

	private String flugzeugtyp;

	private String hersteller;

	private int maxPassagiere;

	//bi-directional many-to-one association to Fluege
	@OneToMany(mappedBy="flugzeug")
	private List<Fluege> flueges;

	public Flugzeug() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFlugzeugtyp() {
		return this.flugzeugtyp;
	}

	public void setFlugzeugtyp(String flugzeugtyp) {
		this.flugzeugtyp = flugzeugtyp;
	}

	public String getHersteller() {
		return this.hersteller;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public int getMaxPassagiere() {
		return this.maxPassagiere;
	}

	public void setMaxPassagiere(int maxPassagiere) {
		this.maxPassagiere = maxPassagiere;
	}

	public List<Fluege> getFlueges() {
		return this.flueges;
	}

	public void setFlueges(List<Fluege> flueges) {
		this.flueges = flueges;
	}

	public Fluege addFluege(Fluege fluege) {
		getFlueges().add(fluege);
		fluege.setFlugzeug(this);

		return fluege;
	}

	public Fluege removeFluege(Fluege fluege) {
		getFlueges().remove(fluege);
		fluege.setFlugzeug(null);

		return fluege;
	}

}