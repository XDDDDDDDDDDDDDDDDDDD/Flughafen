package ejb;

import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Benutzer;

@LocalBean
@Stateless
public class BenutzerEJB 
{
	
	@PersistenceContext
	EntityManager em;
	
	
	public void saveUser(Benutzer benutzer)
	{
		em.merge(benutzer);
	}
	
	
	public void deleteUser(Benutzer benutzer)
	{
		em.remove(benutzer);
	}
	
	public List<Benutzer> getAll()
	{
		Query q=em.createQuery("SELECT benutzer FROM Benutzer benutzer");
		return (List<Benutzer>) q.getResultList();
	}
	
	public Benutzer getUserByName(String name)
	{
		Query q = em.createQuery(
				"SELECT DISTINCT benutzer FROM Benutzer benutzer WHERE benutzer.nutzername = '" + name + "'");
		return (Benutzer) q.getSingleResult();
	}

}
