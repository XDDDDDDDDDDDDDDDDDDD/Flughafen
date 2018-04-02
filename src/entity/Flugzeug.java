package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the flugzeug database table.
 * 
 */
@Entity
@NamedQuery(name="Flugzeug.findAll", query="SELECT f FROM Flugzeug f")
public class Flugzeug implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String code;

	private String flugzeugtyp;

	private String hersteller;

	private int maxPassagiere;

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

}