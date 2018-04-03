package ejb;

import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Ort;

@LocalBean
@Stateless
public class OrteEJB //EJB für die Orte
{
	
	@PersistenceContext
	EntityManager em;
	
	
	public void saveOrt(Ort ort)
	{
		em.merge(ort);
	}
	
	
	public void deleteOrt(Ort ort)
	{
		if (!em.contains(ort)) {
			ort = em.merge(ort);
		}
		em.remove(ort);
	}
	
	public List<Ort> getAll()
	{
		Query q=em.createQuery("SELECT ort FROM Ort ort");
		return (List<Ort>) q.getResultList();
	}
	
	public Ort getOrtByID(int id)
	{
		Query q = em.createQuery(
				"SELECT DISTINCT ort FROM Ort ort WHERE ort.id = '" + id + "'");
		return (Ort) q.getSingleResult();
	}

}
