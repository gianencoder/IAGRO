package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import models.Localidad;

/**
 * Session Bean implementation class LocalidadBean
 */
@Stateless
@LocalBean
public class LocalidadBean {

	/**
	 * Default constructor.
	 */
	public LocalidadBean() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	EntityManager em;

	public void crear(Localidad localidad) throws Exception {

		try {

			em.persist(localidad);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo crear el localidad");
		}
	}

	public void actualizar(Localidad localidad) throws Exception {
		try {

			em.merge(localidad);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo actualizar el localidad");
		}

	}

	public void borrar(Long id) throws Exception {

		try {

			Localidad localidad = em.find(Localidad.class, id);
			em.remove(localidad);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo borrar el Localidad");
		}

	}

	public List<Localidad> obtenerTodos() {

		TypedQuery<Localidad> query = em.createQuery("SELECT e FROM Localidad e ORDER BY e.nombre", Localidad.class);

		return query.getResultList();
	}

	public Localidad getLocalidadById(Long id) {

		Localidad localidad = em.find(Localidad.class, id);
		return localidad;
	}

}
