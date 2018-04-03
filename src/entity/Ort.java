package entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ort database table.
 * 
 */
@Entity
@NamedQuery(name="Ort.findAll", query="SELECT o FROM Ort o")
public class Ort implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to Route
	@OneToMany(mappedBy="ort1")
	private List<Route> routes1;

	//bi-directional many-to-one association to Route
	@OneToMany(mappedBy="ort2")
	private List<Route> routes2;

	public Ort() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Route> getRoutes1() {
		return this.routes1;
	}

	public void setRoutes1(List<Route> routes1) {
		this.routes1 = routes1;
	}

	public Route addRoutes1(Route routes1) {
		getRoutes1().add(routes1);
		routes1.setOrt1(this);

		return routes1;
	}

	public Route removeRoutes1(Route routes1) {
		getRoutes1().remove(routes1);
		routes1.setOrt1(null);

		return routes1;
	}

	public List<Route> getRoutes2() {
		return this.routes2;
	}

	public void setRoutes2(List<Route> routes2) {
		this.routes2 = routes2;
	}

	public Route addRoutes2(Route routes2) {
		getRoutes2().add(routes2);
		routes2.setOrt2(this);

		return routes2;
	}

	public Route removeRoutes2(Route routes2) {
		getRoutes2().remove(routes2);
		routes2.setOrt2(null);

		return routes2;
	}

}