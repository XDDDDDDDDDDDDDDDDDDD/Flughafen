package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the route database table.
 * 
 */
@Entity
@NamedQuery(name="Route.findAll", query="SELECT r FROM Route r")
public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Fluege
	@OneToMany(mappedBy="route")
	private List<Fluege> flueges;

	//bi-directional many-to-one association to Ort
	@ManyToOne
	@JoinColumn(name="start")
	private Ort ort1;

	//bi-directional many-to-one association to Ort
	@ManyToOne
	@JoinColumn(name="ziel")
	private Ort ort2;

	public Route() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Fluege> getFlueges() {
		return this.flueges;
	}

	public void setFlueges(List<Fluege> flueges) {
		this.flueges = flueges;
	}

	public Fluege addFluege(Fluege fluege) {
		getFlueges().add(fluege);
		fluege.setRoute(this);

		return fluege;
	}

	public Fluege removeFluege(Fluege fluege) {
		getFlueges().remove(fluege);
		fluege.setRoute(null);

		return fluege;
	}

	public Ort getOrt1() {
		return this.ort1;
	}

	public void setOrt1(Ort ort1) {
		this.ort1 = ort1;
	}

	public Ort getOrt2() {
		return this.ort2;
	}

	public void setOrt2(Ort ort2) {
		this.ort2 = ort2;
	}

}