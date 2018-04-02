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

	//uni-directional one-to-one association to Ort
	@OneToOne
	@JoinColumn(name="start")
	private Ort ort1;

	//uni-directional one-to-one association to Ort
	@OneToOne
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