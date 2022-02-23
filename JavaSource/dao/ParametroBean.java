package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import models.Parametro;

/**
 * Session Bean implementation class ParametroBean
 */
@Stateless
@LocalBean
public class ParametroBean {

	@PersistenceContext
	EntityManager em;

	public ParametroBean() {
		// TODO Auto-generated constructor stub
	}

	public Parametro crear(Parametro parametro) throws Exception {

		try {

			em.persist(parametro);
			em.flush();
			return parametro;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("No se pudo crear el parametro");
		}

	}

	public void actualizar(Parametro parametro) throws Exception {

		try {

			em.merge(parametro);

			em.flush();

		} catch (Exception e) {

			System.out.println("No se pudo actualizar el parametro");
		}

	}

	public void borrar(Long id) throws Exception {

		try {

			Parametro p = em.find(Parametro.class, id);

			em.remove(p);

			em.flush();

		} catch (Exception e) {

			System.out.println("No se pudo eliminar el parametro");
		}

	}

	public List<Parametro> obtenerTodos() {

		TypedQuery<Parametro> query = em.createQuery("SELECT p FROM Parametro p", Parametro.class);
		return query.getResultList();
	}

	public List<Parametro> obtenerTodos1(String filtro) {

		TypedQuery<Parametro> query = em
				.createQuery("SELECT p FROM PARAMETRO p WHERE p.nombre = :nombre", Parametro.class)
				.setParameter(1, filtro);
		return query.getResultList();
	}

	public List<Parametro> porFormulario(Long id) {

		List<Parametro> lista = new ArrayList<Parametro>();

		try {
			TypedQuery<Parametro> query = em.createQuery("SELECT p FROM Parametro p", Parametro.class);

			List<Parametro> parametros = query.getResultList();
			for (Parametro parametro : parametros) {
				if (parametro.getFormulario().getId() == id) {
					lista.add(parametro);
				}
			}

			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Parametro getParametroById(Long id) {
		Parametro p = em.find(Parametro.class, id);
		return p;
	}

}
