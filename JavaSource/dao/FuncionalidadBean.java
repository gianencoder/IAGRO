package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import models.Funcionalidad;

/**
 * Session Bean implementation class FuncionalidadBean
 */
@Stateless
@LocalBean
public class FuncionalidadBean {

	@PersistenceContext
	EntityManager em;

	public FuncionalidadBean() {

	}

	public void crear(Funcionalidad funcionalidad) throws Exception {

		try {

			Funcionalidad f = new Funcionalidad();

			em.persist(f);

			em.flush();

		} catch (Exception e) {

			System.out.println("No se pudo crear la funcionalidad ");
		}

	}

	public void modificar(Funcionalidad funcionalidad) throws Exception {

		try {

			em.merge(funcionalidad);

			em.flush();

		} catch (Exception e) {

			System.out.println("No se pudo actualizar la funcionalidad");
		}

	}

	public void eliminar(Long id) throws Exception {

		try {

			Funcionalidad f = em.find(Funcionalidad.class, id);

			em.remove(f);

			em.flush();

		} catch (Exception e) {

			System.out.println("No se pudo eliminar la funcionalidad");
		}

	}

	public List<Funcionalidad> obtenerTodos() {

		TypedQuery<Funcionalidad> query = em.createQuery("SELECT f FROM FUNCIONALIDAD f", Funcionalidad.class);

		return query.getResultList();
	}

	public List<Funcionalidad> obtenerTodos(String filtro) {

		TypedQuery<Funcionalidad> query = em.createQuery("SELECT f FROM FUNCIONALIDAD f WHERE f.nombre LIKE : nombre",
				Funcionalidad.class);

		return query.getResultList();

	}
}
