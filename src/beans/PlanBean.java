package beans;

import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import ejb.FluegeEJB;
import entity.Fluege;

@ManagedBean
@SessionScoped
public class PlanBean 
{
	
	private Date cal;
	private List<Fluege> alle;
	
	
	public Date getCal() {
		return cal;
	}

	public void setCal(Date cal) {
		this.cal = cal;
	}

	public List<Fluege> getAlle() {
		return alle;
	}

	public void setAlle(List<Fluege> alle) {
		this.alle = alle;
	}

	@Inject
	FluegeEJB fluegeEJB;
	
	@PostConstruct
	public void init()
	{
		updaten();
	}
	
	
	//Sortieren und erstellen der Liste mit Inputs
	@SuppressWarnings("deprecation")
	public void updaten()              //updaten der Form und der Zeit
	{
		
		Timestamp time=new Timestamp(System.currentTimeMillis());
		Date date=new Date(time.getTime());
		cal=date;
		
		List<Fluege> allFluege =fluegeEJB.getAll();
		for(Fluege f:allFluege)
		{
			Date d= f.getAbflugzeit();
			if(d.getYear()<cal.getYear())
			{
				allFluege.remove(f);
				break;
			}
			if(d.getMonth()<cal.getMonth())
			{
				allFluege.remove(f);
				break;
			}
			if(d.getDay()<cal.getDay())
			{
				allFluege.remove(f);
				break;
			}
			if(d.getHours()<cal.getHours())
			{
				allFluege.remove(f);
				break;
			}
			if(d.getMinutes()<cal.getMinutes())
			{
				allFluege.remove(f);
				break;
			}
		}
		
	allFluege.sort(Comparator.comparing(Fluege::getAbflugzeit));
		
		
		
	}


}
