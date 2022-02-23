package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import models.Region;

/**
 * Session Bean implementation class RegionBean
 */
@Stateless
@LocalBean
public class RegionBean {

	public RegionBean() {
	}

	@PersistenceContext
	EntityManager em;

	public void crear(Region region) throws Exception {

		try {

			em.persist(region);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo crear el Region");
		}
	}

	public void actualizar(Region region) throws Exception {
		try {

			em.merge(region);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo actualizar el Region");
		}

	}

	public void borrar(Long id) throws Exception {

		try {

			Region region = em.find(Region.class, id);
			em.remove(region);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo borrar el Region");
		}

	}

	public List<Region> obtenerTodos() {

		TypedQuery<Region> query = em.createQuery("SELECT e FROM Region e ORDER BY e.nombre", Region.class);

		return query.getResultList();
	}

	public List<Region> obtenerTodos(String filtro) {

		TypedQuery<Region> query = em.createQuery("SELECT e FROM Region e WHERE e.nombre LIKE :nombre ", Region.class)
				.setParameter("nombre", filtro);

		return query.getResultList();

	}

	public Region getRegionById(Long id) {
		Region region = em.find(Region.class, id);
		return null;
	}

}
