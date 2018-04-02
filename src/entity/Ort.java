package entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ort database table.
 * 
 */
@Entity
@NamedQuery(name="Ort.findAll", query="SELECT o FROM Ort o")
public class Ort implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

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

}