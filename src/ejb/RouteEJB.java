package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.Route;

@LocalBean
@Stateless
public class RouteEJB 
{
	
	@PersistenceContext
	EntityManager em;
	
	
	public void saveRoute(Route route)
	{
		em.merge(route);
	}
	
	
	public void deleteRoute(Route route)
	{
		if (!em.contains(route)) {
			route = em.merge(route);
		}
		em.remove(route);
	}
	
	public List<Route> getAll()
	{
		Query q=em.createQuery("SELECT route FROM Route route");
		return (List<Route>) q.getResultList();
	}
	
	public Route getRouteByID(int id)
	{
		Query q = em.createQuery(
				"SELECT DISTINCT route FROM Route route WHERE route.id = '" + id + "'");
		return (Route) q.getSingleResult();
	}

}
