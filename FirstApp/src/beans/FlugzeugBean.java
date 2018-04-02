package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ejb.FlugzeugEJB;
import entity.Benutzer;
import entity.Flugzeug;

@ManagedBean
@RequestScoped
public class FlugzeugBean {

	@Inject
	FlugzeugEJB flugzeugEJB;

	private String code;
	private String hersteller;
	private String typ;
	private int maxPassagiere;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FlugzeugEJB getFlugzeugEJB() {
		return flugzeugEJB;
	}

	public void setFlugzeugEJB(FlugzeugEJB flugzeugEJB) {
		this.flugzeugEJB = flugzeugEJB;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHersteller() {
		return hersteller;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public int getMaxPassagiere() {
		return maxPassagiere;
	}

	public void setMaxPassagiere(int maxPassagiere) {
		this.maxPassagiere = maxPassagiere;
	}

	public List<Flugzeug> getAllFlugzeuge() {
		return flugzeugEJB.getAll();
	}

	public Flugzeug getUserByName(String code) {
		return flugzeugEJB.getFlugzeugByCode(code);
	}
	
	public void deleteFlugzeug(Flugzeug f)
	{
		flugzeugEJB.deleteFlugzeug(f);
	}

	public void neu() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("neuesFlugzeug.xhtml");
		} catch (Exception e) 
		{
		}
		
	}

	public void erstellen() {
		if (inputTesten()) 
		{
			Flugzeug f= new Flugzeug();
			f.setCode(code);
			f.setFlugzeugtyp(typ);
			f.setHersteller(hersteller);
			f.setMaxPassagiere(maxPassagiere);
			flugzeugEJB.saveFlugzeug(f);
					
			
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("flotte.xhtml");
			} catch (Exception e) {
			}
		}

	}

	private boolean inputTesten()
	{
		if(code.isEmpty())
		{
			setMessage("Bitte einen Flugzeugcode eingeben.");
			return false;
		}
		if(typ.isEmpty())
		{
			setMessage("Bitte einen Flugzeugtyp angeben.");
			return false;
		}
		if(maxPassagiere==0)
		{
			setMessage("Bitte eine maximale Anzahl an zu befördernden Passagieren eingeben.");
			return false;
		}
		return true;
	}
	

}
