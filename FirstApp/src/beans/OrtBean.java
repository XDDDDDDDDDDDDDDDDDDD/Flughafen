package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import entity.Ort;
import ejb.OrteEJB;

@ManagedBean
@RequestScoped
public class OrtBean 
{

	@Inject
	OrteEJB ortEJB;

	private String name;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Ort> getAllUsers() {
		return ortEJB.getAll();
	}

	public Ort getUserByName(String name) {
		return ortEJB.getOrtByName(name);
	}


	public void saveUser(Ort ort) {
		ortEJB.saveOrt(ort);
	}

}
