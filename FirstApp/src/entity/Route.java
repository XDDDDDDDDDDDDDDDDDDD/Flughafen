package entity;

import java.io.Serializable;
import javax.persistence.*;


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

	private int start;

	private int ziel;

	public Route() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStart() {
		return this.start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getZiel() {
		return this.ziel;
	}

	public void setZiel(int ziel) {
		this.ziel = ziel;
	}

}