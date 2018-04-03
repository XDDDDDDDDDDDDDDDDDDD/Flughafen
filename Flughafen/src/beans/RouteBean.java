package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ejb.OrteEJB;
import ejb.RouteEJB;
import entity.Ort;
import entity.Route;

@ManagedBean
@RequestScoped
public class RouteBean 
{
	@Inject
	OrteEJB orteEJB;
	
	@Inject
	RouteEJB routeEJB;
	
	
	private int start;
	private int ziel;
	private boolean both;
	public boolean isBoth() {
		return both;
	}

	public void setBoth(boolean both) {
		this.both = both;
	}

	private String message;
	private List<Ort> orte;
	
	@PostConstruct
    public void init() {
        orte = getAllOrte();
	}
	
	public List<Ort> getOrte() {
		return orte;
	}

	public void setOrte(List<Ort> orte) {
		this.orte = orte;
	}

	
	public String getMessage() {
		return message;
	}
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getZiel() {
		return ziel;
	}

	public void setZiel(int ziel) {
		this.ziel = ziel;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Ort getOrtById(int id) {
		return orteEJB.getOrtByID(id);
	}
	
	public List<Ort> getAllOrte() {
		return orteEJB.getAll();
	}
	
	public List<Route> getAllRouten() {
		return routeEJB.getAll();
	}
	
	public void saveRoute(Route route) {
		routeEJB.saveRoute(route);
	}

	public void deleteRoute(Route route) {
		routeEJB.deleteRoute(route);
	}
	
	public void erstellen()
	{
		Ort s=getOrtById(start);
		Ort z=getOrtById(ziel);
		
		if(inputTesten())
		{
			Route r = new Route();
			r.setOrt1(s);
			r.setOrt2(z);
			routeEJB.saveRoute(r);
			if(both==true)
			{
				Route r2 = new Route();
				r2.setOrt1(z);
				r2.setOrt2(s);
				routeEJB.saveRoute(r2);
			}
			
			FacesContext context = FacesContext.getCurrentInstance();
			try {
				context.getExternalContext().redirect("routen.xhtml");
			} catch (Exception e) {
			}
		}
	}
	
	private boolean inputTesten() {
		if (start==ziel) {
			setMessage("Startort und Ziel müssen unterschiedlich sein.");
			return false;
		}
		List<Route> l = getAllRouten();
		for (Route o : l) {
			if (o.getOrt1().getId()==start && o.getOrt2().getId()==ziel) {
				setMessage("Die Verbindung existiert bereits.");
				return false;
			}
		}
		return true;
	}
	
	public void neu()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("neueRoute.xhtml");
		} catch (Exception e) {
		}

	}
	

}
