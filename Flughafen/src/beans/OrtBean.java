package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import entity.Ort;
import ejb.OrteEJB;

@ManagedBean
@ViewScoped
public class OrtBean {

	@Inject
	OrteEJB orteEJB;

	private String name;
	private String message;

	public String getMessage() {
		return message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Ort> getAllOrte() {
		return orteEJB.getAll();
	}


	public Ort getOrtById(int id) {
		return orteEJB.getOrtByID(id);
	}

	public void saveOrt(Ort ort) {
		orteEJB.saveOrt(ort);
	}

	public void deleteOrt(Ort o) {
		orteEJB.deleteOrt(o);
	}

	public void erstellen() {
		if (inputTesten()) {
			Ort f = new Ort();
			f.setName(name);
			orteEJB.saveOrt(f);
			
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("orte.xhtml");
			} catch (Exception e) {
			}
		}

	}

	private boolean inputTesten() {
		if (name.isEmpty()) {
			setMessage("Bitte einen Ortnamen eingeben.");
			return false;
		}
		List<Ort> l = getAllOrte();
		for (Ort o : l) {
			if (o.getName().equals(name)) {
				setMessage("Der Flughafen existiert bereits.");
				return false;
			}
		}
		return true;
	}

	public void neu() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("neuerOrt.xhtml");
		} catch (Exception e) {
		}

	}

}
