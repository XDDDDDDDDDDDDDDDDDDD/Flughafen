package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the benutzer database table.
 * 
 */
@Entity
@NamedQuery(name="Benutzer.findAll", query="SELECT b FROM Benutzer b")
public class Benutzer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nachname;

	private String nutzername;

	private String passwort;

	private String rolle;

	private String vorname;

	public Benutzer() {
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

	public String getNutzername() {
		return this.nutzername;
	}

	public void setNutzername(String nutzername) {
		this.nutzername = nutzername;
	}

	public String getPasswort() {
		return this.passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getRolle() {
		return this.rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

}