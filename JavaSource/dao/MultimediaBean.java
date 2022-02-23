package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import models.Multimedia;
import models.ActividadCampo;

/**
 * Session Bean implementation class MultimediaBean
 */
@Stateless
@LocalBean
public class MultimediaBean {

	@PersistenceContext
	EntityManager em;

	public MultimediaBean() {

	}

	public void crear(Multimedia multimedia) throws Exception {

		try {
			Multimedia m = new Multimedia();

			em.persist(m);

			em.flush();

		} catch (PersistenceException e) {

			System.out.println("No se pudo crear la multimedia");
		}

	}

	public void actualizar(Multimedia multimedia) throws Exception {

		try {

			em.merge(multimedia);

			em.flush();

		} catch (PersistenceException e) {

			System.out.println("No se pudo actualizar el area multimedia");
		}

	}

	public void borrar(Long id) throws Exception {

		try {

			Multimedia multimedia = em.find(Multimedia.class, id);

			em.remove(multimedia);

			em.flush();

		} catch (PersistenceException e) {

			System.out.println("No se pudo eliminar la Multimedia");
		}

	}

	public List<Multimedia> obtenerTodos() {

		TypedQuery<Multimedia> query = em.createQuery("SELECT m FROM Multimedia m", Multimedia.class);
		return query.getResultList();

	}

	public List<Multimedia> obtenerTodos(String filtro) {

		TypedQuery<Multimedia> query = em.createQuery("SELECT m FROM Salon m WHERE m.url LIKE :url", Multimedia.class)
				.setParameter("url", filtro);
		return query.getResultList();
	}

	public void asignarReporte(Long idMultimedia, Long idReporte) throws Exception {

		try {
			Multimedia multimedia = em.find(Multimedia.class, idMultimedia);

			multimedia.setReporte(em.find(ActividadCampo.class, idReporte));
			;

			em.flush();

		} catch (Exception e) {

			System.out.println("No se pudo asignar el Reporte a la Multimedia");
		}

	}

}
