package ejb;

import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Flugzeug;

@LocalBean
@Stateless
public class FlugzeugEJB 
{
	
	@PersistenceContext
	EntityManager em;
	
	
	public void saveFlugzeug(Flugzeug flugzeug)
	{
		em.merge(flugzeug);
	}
	
	
	public void deleteFlugzeug(Flugzeug flugzeug)
	{
		if (!em.contains(flugzeug)) {
		    flugzeug = em.merge(flugzeug);
		}
		em.remove(flugzeug);
	}
	
	public List<Flugzeug> getAll()
	{
		Query q=em.createQuery("SELECT flugzeug FROM Flugzeug flugzeug");
		return (List<Flugzeug>) q.getResultList();
	}
	
	public Flugzeug getFlugzeugByCode(String code)
	{
		Query q = em.createQuery(
				"SELECT DISTINCT flugzeug FROM Flugzeug flugzeug WHERE flugzeug.code = '" + code + "'");
		return (Flugzeug) q.getSingleResult();
	}
	
	public Flugzeug getFlugzeugById(int id)
	{
		Query q = em.createQuery(
				"SELECT DISTINCT flugzeug FROM Flugzeug flugzeug WHERE flugzeug.id = '" + id + "'");
		return (Flugzeug) q.getSingleResult();
	}

}
