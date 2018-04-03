package ejb;

import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Buchungen;

@LocalBean
@Stateless
public class BuchungenEJB  //EJB für die Buchungen, hängt mit der Liste in der Kunden-Klasse zusammen
{
	
	@PersistenceContext
	EntityManager em;
	
	
	public void saveBuchungen(Buchungen buchung)
	{
		em.merge(buchung);
		buchung.getKunde().addBuchungen(buchung);
	}
	
	
	public void deleteBuchungen(Buchungen buchung)
	{
		if (!em.contains(buchung)) {
			buchung = em.merge(buchung);
		}
		em.remove(buchung);
		buchung.getKunde().removeBuchungen(buchung);  
	}
	
	public List<Buchungen> getAll()
	{
		Query q=em.createQuery("SELECT buchungen FROM Buchungen buchungen");
		return (List<Buchungen>) q.getResultList();
	}
	
	public Buchungen getBuchungenByID(int id)
	{
		Query q = em.createQuery(
				"SELECT DISTINCT buchungen FROM Buchungen buchungen WHERE buchungen.id = '" + id + "'");
		return (Buchungen) q.getSingleResult();
	}
	

}
