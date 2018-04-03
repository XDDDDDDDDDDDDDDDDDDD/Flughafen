package beans;

import javax.faces.context.FacesContext;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import entity.Buchungen;
import entity.Fluege;
import entity.Kunde;
import ejb.BuchungenEJB;
import ejb.FluegeEJB;
import ejb.KundenEJB;

@ManagedBean
@ViewScoped
public class BuchenBean {

	@Inject
	FluegeEJB fluegeEJB;
	
	@Inject
	KundenEJB kundenEJB;
	
	@Inject
	BuchungenEJB buchungenEJB;

	
	private int kundenID;
	private int flugID;
	private String message;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getFlugID() {
		return flugID;
	}


	public void setFlugID(int flugID) {
		this.flugID = flugID;
	}


	public int getKundenID() {
		return kundenID;
	}


	public void setKundenID(int kundenID) {
		this.kundenID = kundenID;
	}


	public List<Fluege> getAllFluege() {
		return fluegeEJB.getAll();
	}


	public Fluege getFluegeById(int id) {
		return fluegeEJB.getFluegeByID(id);
	}
	
	public Kunde getKundeById(int id) {
		return kundenEJB.getKundeByID(id);
	}
	

	private boolean inputTesten() 
	{
		List<Buchungen> l=buchungenEJB.getAll();
		if(l.isEmpty())
		{
			return true;
		}
		for(Buchungen b:l)
		{
			b.getFluege().getId();
			if(b.getFluege().getId()==flugID && b.getKunde().getId()==kundenID)
			{
				setMessage("Der Kunde ist bereits dem Flug zugewiesen.");
				return false;
			}
		}
		

		return true;
	}


	
	public void buchen(int f)
	{
		
		flugID=f;
		Fluege flug=getFluegeById(f);
		if(inputTesten())
		{
			Buchungen b= new Buchungen();
			b.setFluege(flug);
			b.setKunde(getKundeById(kundenID));
			buchungenEJB.saveBuchungen(b);
			
			kundenEJB.addBuchung(getKundeById(kundenID), b);
			setMessage("Erfolgreich gebucht!");
		}
	}
	

}
