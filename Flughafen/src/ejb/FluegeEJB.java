package ejb;

import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Fluege;

@LocalBean
@Stateless
public class FluegeEJB 
{
	
	@PersistenceContext
	EntityManager em;
	
	
	public void saveFluege(Fluege fluege)
	{
		em.merge(fluege);
	}
	
	
	public void deleteFluege(Fluege fluege)
	{
		if (!em.contains(fluege)) {
		    fluege = em.merge(fluege);
		}
		em.remove(fluege);
	}
	
	public List<Fluege> getAll()
	{
		Query q=em.createQuery("SELECT fluege FROM Fluege fluege");
		return (List<Fluege>) q.getResultList();
	}
	
	public Fluege getFluegeByID(int id)
	{
		Query q = em.createQuery(
				"SELECT DISTINCT fluege FROM Fluege fluege WHERE fluege.id = '" + id + "'");
		return (Fluege) q.getSingleResult();
	}

}
