package beans;

import javax.faces.context.FacesContext;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import entity.Buchungen;
import entity.Fluege;
import entity.Kunde;
import ejb.BuchungenEJB;
import ejb.FluegeEJB;
import ejb.KundenEJB;

@ManagedBean
@SessionScoped
public class GebuchtBean {

	@Inject
	FluegeEJB fluegeEJB;
	
	@Inject
	KundenEJB kundenEJB;
	
	@Inject
	BuchungenEJB buchungenEJB;

	
	private int flugId;
	private Fluege flug;
	List<Buchungen> bu;


	public List<Buchungen> getBu() {
		return bu;
	}


	public void setBu(List<Buchungen> bu) {
		this.bu = bu;
	}


	public int getFlugId() {
		return flugId;
	}


	public void setFlugId(int flugId) {
		this.flugId = flugId;
	}


	public Fluege getFluegeById(int id) {
		return fluegeEJB.getFluegeByID(id);
	}
	
	public Kunde getKundeById(int id) {
		return kundenEJB.getKundeByID(id);
	}
	

public void uebersicht(int flugid)   //Anzeige der Buchungen eines Fluges
{
	this.flugId=flugid;
	this.flug=getFluegeById(flugid);
	
	bu=flug.getBuchungens();
	
	try {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().redirect("fluegeKunden.xhtml");
	} catch (Exception e) {
	}
}


public Fluege getFlug() {
	return flug;
}


public void setFlug(Fluege flug) {
	this.flug = flug;
}
	

}
