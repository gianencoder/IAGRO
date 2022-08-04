package dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import models.Metodo;

@Stateless
@LocalBean
public class MetodoBean {

	@PersistenceContext
	EntityManager em;

	public MetodoBean() {
	}

	public Metodo crear(Metodo metodo) throws Exception {

		try {

			em.persist(metodo);
			em.flush();
			return metodo;
		} catch (PersistenceException e) {
			throw new Exception("No se pudo crear el metodo-->" + e.getMessage());
		}
	}

	public void actualizar(Metodo metodo) throws Exception {
		try {

			em.merge(metodo);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo actualizar el metodo-->" + e.getMessage());
		}

	}

	public void borrar(Long id) throws Exception {

		try {

			Metodo metodo = em.find(Metodo.class, id);
			em.remove(metodo);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo borrar el metodo-->" + e.getMessage());
		}

	}

	public List<Metodo> obtenerTodos() {

		TypedQuery<Metodo> query = em.createQuery("SELECT m FROM Metodo m ORDER BY m.nombre", Metodo.class);
		return query.getResultList();

	}

	public Metodo getMetodoById(Long id) {
		Metodo metodo = em.find(Metodo.class, id);
		return metodo;
	}
}
