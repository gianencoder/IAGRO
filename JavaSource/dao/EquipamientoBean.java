package dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import models.Equipamiento;

@Stateless
@LocalBean
public class EquipamientoBean {

	@PersistenceContext
	EntityManager em;

	public EquipamientoBean() {
	}

	public Equipamiento crear(Equipamiento equipamiento) throws Exception {

		try {

			em.persist(equipamiento);
			em.flush();
			return equipamiento;
		} catch (PersistenceException e) {
			throw new Exception("No se pudo crear el equipamiento");
		}
	}

	public void actualizar(Equipamiento equipamiento) throws Exception {
		try {

			em.merge(equipamiento);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo actualizar el equipamiento");
		}

	}

	public void borrar(Long id) throws Exception {

		try {

			Equipamiento equipamiento = em.find(Equipamiento.class, id);
			em.remove(equipamiento);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo borrar el equipamiento");
		}

	}

	public List<Equipamiento> obtenerTodos() {

		TypedQuery<Equipamiento> query = em.createQuery("SELECT e FROM Equipamiento e ORDER BY e.nombre",
				Equipamiento.class);

		return query.getResultList();
	}



	public Equipamiento getEquipamientoById(Long id) {
		Equipamiento equipamiento = em.find(Equipamiento.class, id);
		return equipamiento;
	}

}
