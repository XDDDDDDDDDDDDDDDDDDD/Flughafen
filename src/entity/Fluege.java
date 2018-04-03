package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

	@Temporal(TemporalType.TIMESTAMP)
	private Date abflugzeit;

	private String essen;

	private int gebucht;

	//bi-directional many-to-one association to Buchungen
	@OneToMany(mappedBy="fluege")
	private List<Buchungen> buchungens;

	//bi-directional many-to-one association to Flugzeug
	@ManyToOne
	@JoinColumn(name="Flugzeug")
	private Flugzeug flugzeug;

	//bi-directional many-to-one association to Route
	@ManyToOne
	@JoinColumn(name="route")
	private Route route;

	public Fluege() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAbflugzeit() {
		return this.abflugzeit;
	}

	public void setAbflugzeit(Date abflugzeit) {
		this.abflugzeit = abflugzeit;
	}

	public String getEssen() {
		return this.essen;
	}

	public void setEssen(String essen) {
		this.essen = essen;
	}

	public int getGebucht() {
		return this.gebucht;
	}

	public void setGebucht(int gebucht) {
		this.gebucht = gebucht;
	}

	public List<Buchungen> getBuchungens() {
		return this.buchungens;
	}

	public void setBuchungens(List<Buchungen> buchungens) {
		this.buchungens = buchungens;
	}

	public Buchungen addBuchungen(Buchungen buchungen) {
		getBuchungens().add(buchungen);
		buchungen.setFluege(this);

		return buchungen;
	}

	public Buchungen removeBuchungen(Buchungen buchungen) {
		getBuchungens().remove(buchungen);
		buchungen.setFluege(null);

		return buchungen;
	}

	public Flugzeug getFlugzeug() {
		return this.flugzeug;
	}

	public void setFlugzeug(Flugzeug flugzeug) {
		this.flugzeug = flugzeug;
	}

	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

}