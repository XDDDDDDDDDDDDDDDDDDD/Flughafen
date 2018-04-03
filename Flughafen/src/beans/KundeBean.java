package beans;

import javax.faces.context.FacesContext;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import entity.Buchungen;
import entity.Kunde;
import ejb.BuchungenEJB;
import ejb.KundenEJB;

@ManagedBean
@SessionScoped
public class KundeBean {

	@Inject
	KundenEJB kundenEJB;
	
	@Inject
	BuchungenEJB buchungenEJB;

	public String getVorname() {
		return vorname;
	}


	public void setVorname(String vorname) {
		this.vorname = vorname;
	}


	public String getNachname() {
		return nachname;
	}


	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	private String vorname;
	private String nachname;
	private String message;
	private List<Buchungen> alle;

	public List<Buchungen> getAlle() 
	{
		return alle;
	}


	public void setAlle(List<Buchungen> alle) {
		this.alle = alle;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	public List<Kunde> getAllKunden() {
		return kundenEJB.getAll();
	}


	public Kunde getKundeById(int id) {
		return kundenEJB.getKundeByID(id);
	}

	public void saveKunde(Kunde kunde) {
		kundenEJB.saveKunde(kunde);
	}

	public void deleteKunde(Kunde o) 
	{
		List<Buchungen> alleB=getBuchungen(o);
		if(alleB!=null)
		{
			for(Buchungen b:alleB)
			{
				kundenEJB.removeBuchung(o, b);
			}
		}
		kundenEJB.deleteKunde(o);
	}
	
	public List<Buchungen> getBuchungen(Kunde kunde)
	{
		return kundenEJB.getBuchungenByKunde(kunde);
	}
	
	public Buchungen getBuchungById(int id)
	{
		return buchungenEJB.getBuchungenByID(id);
	}

	public void erstellen() {
		if (inputTesten()) {
			Kunde f = new Kunde();
			f.setVorname(vorname);
			f.setNachname(nachname);
			kundenEJB.saveKunde(f);
			
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("kunden.xhtml");
			} catch (Exception e) {
			}
		}

	}

	private boolean inputTesten() {
		if (vorname.isEmpty()) {
			setMessage("Bitte einen Kundenamen eingeben.");
			return false;
		}
		if (nachname.isEmpty()) {
			setMessage("Bitte einen Kundenamen eingeben.");
			return false;
		}
		List<Kunde> l = getAllKunden();
		for (Kunde o : l) {
			if (o.getVorname().equals(vorname) && o.getNachname().equals(nachname)) {
				setMessage("Der Kunde existiert bereits.");
				return false;
			}
		}
		return true;
	}

	public void neu() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("neuerKunde.xhtml");
		} catch (Exception e) {
		}

	}
	
	public void alleBuchungen(Kunde kunde)
	{
		setAlle(getBuchungen(kunde));
		vorname=kunde.getVorname();
		nachname=kunde.getNachname();
		
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("kundeBuchungen.xhtml");
		} catch (Exception e) {
		}
	}
	
	public void removeBuchung(int k, int b)
	{
		Kunde kunde=getKundeById(k);
		Buchungen buch=getBuchungById(b);
		kundenEJB.removeBuchung(kunde, buch);
	}

}
