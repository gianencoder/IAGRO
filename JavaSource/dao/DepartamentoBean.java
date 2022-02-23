package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import models.Departamento;

/**
 * Session Bean implementation class DepartamentoBean
 */
@Stateless
@LocalBean
public class DepartamentoBean {

	/**
	 * Default constructor.
	 */
	public DepartamentoBean() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	EntityManager em;

	public void crear(Departamento departamento) throws Exception {

		try {

			em.persist(departamento);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo crear el departamento");
		}
	}

	public void actualizar(Departamento departamento) throws Exception {
		try {

			em.merge(departamento);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo actualizar el departamento");
		}

	}

	public void borrar(Long id) throws Exception {

		try {

			Departamento departamento = em.find(Departamento.class, id);
			em.remove(departamento);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo borrar el Departamento");
		}

	}

	public List<Departamento> obtenerTodos() {

		TypedQuery<Departamento> query = em.createQuery("SELECT e FROM Departamento e ORDER BY e.nombre",
				Departamento.class);

		return query.getResultList();
	}

	public List<Departamento> obtenerTodos(String filtro) {

		TypedQuery<Departamento> query = em
				.createQuery("SELECT e FROM Departamento e WHERE e.nombre LIKE :nombre ", Departamento.class)
				.setParameter("nombre", filtro);

		return query.getResultList();

	}

	public Departamento getDepartamentoById(Long id) {

		Departamento departamento = em.find(Departamento.class, id);
		return departamento;
	}

}
