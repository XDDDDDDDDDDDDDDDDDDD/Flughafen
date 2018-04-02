package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the fluege database table.
 * 
 */
@Entity
@NamedQuery(name="Fluege.findAll", query="SELECT f FROM Fluege f")
public class Fluege implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date datum;

	private String essen;

	private int flugzeug;

	private int gebucht;

	private int route;

	private Time zeit;

	public Fluege() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getEssen() {
		return this.essen;
	}

	public void setEssen(String essen) {
		this.essen = essen;
	}

	public int getFlugzeug() {
		return this.flugzeug;
	}

	public void setFlugzeug(int flugzeug) {
		this.flugzeug = flugzeug;
	}

	public int getGebucht() {
		return this.gebucht;
	}

	public void setGebucht(int gebucht) {
		this.gebucht = gebucht;
	}

	public int getRoute() {
		return this.route;
	}

	public void setRoute(int route) {
		this.route = route;
	}

	public Time getZeit() {
		return this.zeit;
	}

	public void setZeit(Time zeit) {
		this.zeit = zeit;
	}

}