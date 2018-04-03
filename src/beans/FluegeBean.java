package beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ejb.FluegeEJB;
import ejb.FlugzeugEJB;
import ejb.RouteEJB;
import entity.Fluege;
import entity.Flugzeug;
import entity.Route;

@ManagedBean
@RequestScoped
public class FluegeBean {
	@Inject
	FlugzeugEJB flugzeugEJB;

	@Inject
	RouteEJB routeEJB;

	@Inject
	FluegeEJB fluegeEJB;

	private int flugzeugID;
	private int routeID;
	private String cal;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getFlugzeugID() {
		return flugzeugID;
	}

	public void setFlugzeugID(int flugzeugID) {
		this.flugzeugID = flugzeugID;
	}

	public int getRouteID() {
		return routeID;
	}

	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}


	public String getCal() {
		return cal;
	}

	public void setCal(String cal) {
		this.cal = cal;
	}

	public void erstellen()  //Neuer Flug
	{
		if (inputTesten()) {
			Fluege f = new Fluege();
			Date dateTime=null;
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try
			{
				dateTime=formater.parse(cal);
			}
			catch(ParseException e)
			{
				setMessage("Das Datumsformat ist falsch.");
			}
			
			
			f.setAbflugzeit(dateTime);
			Flugzeug flugz = flugzeugEJB.getFlugzeugById(flugzeugID);
			f.setFlugzeug(flugz);
			Route r = routeEJB.getRouteByID(routeID);
			f.setRoute(r);
			f.setEssen("Pizza");   //Pizza>Käse-Schinken- Brot
			fluegeEJB.saveFluege(f);

			try {
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect("flugplan.xhtml");
			} catch (Exception e) {
				setMessage("Ein Fehler ist aufgetreten");
			}
		}

	}
	

	public boolean inputTesten() {
		if (cal == "") {
			setMessage("Bitte ein Abflugdatum angeben");
			return false;
		}

		return true;

	}
}
