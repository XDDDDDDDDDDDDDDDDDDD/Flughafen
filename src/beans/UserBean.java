package beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import entity.Benutzer;
import ejb.BenutzerEJB;

@ManagedBean
@SessionScoped
public class UserBean 
{

	@Inject
	BenutzerEJB benutzerEJB;

	private String nutzername;
	private String vorname;
	private String nachname;
	private String rolle;
	private String message;
	private String including;
	private String passwort;
	private String passwortConfirm;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIncluding() {
		return including;
	}

	public void setIncluding(String including) {
		this.including = including;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getPasswortConfirm() {
		return passwortConfirm;
	}

	public void setPasswortConfirm(String passwortConfirm) {
		this.passwortConfirm = passwortConfirm;
	}

	public String getNutzername() {
		return nutzername;
	}

	public void setNutzername(String nutzername) {
		this.nutzername = nutzername;
	}

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

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	public List<Benutzer> getAllUsers() {
		return benutzerEJB.getAll();
	}

	public Benutzer getUserByName(String nutzername) {
		return benutzerEJB.getUserByName(nutzername);
	}

	public void einloggen() {
		try {
			Benutzer user = getUserByName(getNutzername());

			int c = passwort.hashCode();
			String pass = Integer.toString(c);

			if (pass.equals(user.getPasswort())) {
				setNutzername(user.getNutzername());
				setVorname(user.getVorname());
				setNachname(user.getNachname());
				setRolle(user.getRolle());
				sessionAndRole();
			} else {
				setMessage("Benutzername oder Passwort war falsch.");
			}
		} catch (Exception e) {
			setMessage("User existiert nicht.");
		}

	}

	public void registrierenRedirect() {
		setNutzername("");
		setPasswort("");
		setMessage("");
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("register.xhtml");
		} catch (Exception e) {
			setMessage("Ein Fehler ist aufgetreten");
		}
	}

	public void ausloggen() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
		try {
			context.getExternalContext().redirect("index.xhtml");
		} catch (Exception e) {
			setMessage("Ein Fehler ist aufgetreten");
		}
	}

	private void sessionAndRole() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("nutzername", getNutzername());
			context.getExternalContext().getSessionMap().put("vorname", getVorname());
			context.getExternalContext().getSessionMap().put("nachname", getNachname());
			context.getExternalContext().getSessionMap().put("rolle", getRolle());
			setMessage("");
			if (getRolle().equals("Manager")) {
				setIncluding("/resources/navigation.xhtml");
			} else {
				setIncluding("/resources/navigation2.xhtml");
			}

			context.getExternalContext().redirect("flugplan.xhtml");
		} catch (Exception e) {
			setMessage("Ein Fehler ist aufgetreten");
		}

	}

	public void registrieren() {
		if (inputTesten()) {
			if (istDoppelt()) {
				Benutzer benutzer = new Benutzer();
				benutzer.setNachname(nachname);
				benutzer.setVorname(vorname);
				String help = Integer.toString(passwort.hashCode());
				benutzer.setPasswort(help);
				benutzer.setRolle(rolle);
				benutzer.setNutzername(nutzername);
				saveUser(benutzer);

				sessionAndRole();
				setPasswort("");
				setPasswortConfirm("");}
		}
	}

	private boolean inputTesten() {
		if (nutzername == null || nutzername.isEmpty()) {
			setMessage("Bitte einen Nutzernamen eintragen.");
			return false;
		}
		if (vorname == null || vorname.equals("")) {
			setMessage("Bitte einen Vornamen eintragen.");
			return false;
		}
		if (nachname == null || nachname.equals("")) {
			setMessage("Bitte einen Nachnamen eintragen.");
			return false;
		}
		if (passwort == null || passwort.equals("")) {
			setMessage("Bitte ein Passwort eintragen.");
			return false;
		}
		if (passwortConfirm == null || passwortConfirm.equals("")) {
			setMessage("Bitte das Passwort wiederholen.");
			return false;
		}
		if (!passwort.equals(passwortConfirm)) {
			setMessage("Die Passwörter stimmen nicht überein.");
			return false;
		}
		return true;
	}

	private boolean istDoppelt() {
		List<Benutzer> alle = getAllUsers();
		for (Benutzer ben : alle) {
			if (ben.getNutzername() == nutzername) {
				return false;
			}
		}
		return true;
	}

	public void saveUser(Benutzer benutzer) {
		benutzerEJB.saveUser(benutzer);
	}

}
