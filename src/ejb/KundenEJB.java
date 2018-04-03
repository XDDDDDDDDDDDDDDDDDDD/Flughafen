package ejb;

import java.util.List;

import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Buchungen;
import entity.Kunde;

@LocalBean
@Stateless
public class KundenEJB //EJB für die Kunden
{
	
	@PersistenceContext
	EntityManager em;
	
	
	public void saveKunde(Kunde kunde)
	{
		em.merge(kunde);
	}
	
	
	public void deleteKunde(Kunde kunde)
	{
		if (!em.contains(kunde)) {
			kunde = em.merge(kunde);
		}
		em.remove(kunde);
	}
	
	public List<Kunde> getAll()
	{
		Query q=em.createQuery("SELECT kunde FROM Kunde kunde");
		return (List<Kunde>) q.getResultList();
	}
	
	public Kunde getKundeByID(int id)
	{
		Query q = em.createQuery(
				"SELECT DISTINCT kunde FROM Kunde kunde WHERE kunde.id = '" + id + "'");
		return (Kunde) q.getSingleResult();
	}
	
	public List<Buchungen> getBuchungenByKunde(Kunde kunde)
	{
		return kunde.getBuchungens();
	}
	
	public void removeBuchung(Kunde k,Buchungen buchung)
	{
		k.removeBuchungen(buchung);
		if (!em.contains(buchung)) {
			buchung = em.merge(buchung);
		}
		em.remove(buchung);
	}
	
	public void addBuchung(Kunde k,Buchungen buchung)
	{
		k.addBuchungen(buchung);
	}

}
