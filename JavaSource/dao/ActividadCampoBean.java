package dao;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import models.ActividadCampo;

@Stateless
@LocalBean
public class ActividadCampoBean {

	@PersistenceContext
	EntityManager em;

	public ActividadCampoBean() {
	}

	public ActividadCampo crear(ActividadCampo actividadCampo) throws Exception {

		try {

			em.persist(actividadCampo);
			em.flush();
			return actividadCampo;
		} catch (PersistenceException e) {
			throw new Exception("No se pudo crear el ActividadCampo");
		}
	}

	public void actualizar(ActividadCampo actividadCampo) throws Exception {
		try {

			em.merge(actividadCampo);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo actualizar el ActividadCampo");
		}

	}

	public void borrar(Long id) throws Exception {

		try {

			ActividadCampo actividadCampo = em.find(ActividadCampo.class, id);
			em.remove(actividadCampo);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo borrar el ActividadCampo");
		}

	}

	/**
	 * 
	 * @return List<ActividadCampo>
	 */
	public List<ActividadCampo> obtenerTodos() {

		TypedQuery<ActividadCampo> query = em.createQuery("SELECT e FROM ActividadCampo e WHERE activoSN = 'S' ",
				ActividadCampo.class);

		return query.getResultList();
	}

	/**
	 *
	 * @return List<ActividadCampo>
	 */
	public List<ActividadCampo> all() {
		TypedQuery<ActividadCampo> query = em.createQuery("SELECT e FROM ActividadCampo e", ActividadCampo.class);
		return query.getResultList();
	}

	/**
	 * 
	 * @param filtro
	 * @return List<ActividadCampo>
	 */
	public List<ActividadCampo> obtenerTodos(String filtro) {

		TypedQuery<ActividadCampo> query = em
				.createQuery("SELECT e FROM ActividadCampo e WHERE e.nombre LIKE :nombre ", ActividadCampo.class)
				.setParameter("nombre", filtro);

		return query.getResultList();

	}

	public ActividadCampo getActividadById(Long id) {
		ActividadCampo actividadCampo = em.find(ActividadCampo.class, id);
		return actividadCampo;
	}

	public void desactivar(Long id) {
		ActividadCampo actividadCampo = em.find(ActividadCampo.class, id);
		actividadCampo.setActivoSN("N");
		em.merge(actividadCampo);
		em.flush();

	}

	public List<ActividadCampo> obtenerTodosRangoDeFechas(Date fechaInicio, Date fechaFin) {

		TypedQuery<ActividadCampo> query = em
				.createQuery("SELECT e FROM ActividadCampo e WHERE e.fechaInicio between :fechaInicio and :fechaFin",
						ActividadCampo.class)
				.setParameter("fechaInicio", fechaInicio).setParameter("fechaFin", fechaFin);

		return query.getResultList();

	}

}
