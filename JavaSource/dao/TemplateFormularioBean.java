package dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import models.TemplateFormulario;
import models.Usuario;

@Stateless
@LocalBean
public class TemplateFormularioBean {

	@PersistenceContext
	EntityManager em;

	public TemplateFormulario crear(TemplateFormulario formulario) throws Exception {

		try {

			em.persist(formulario);
			em.flush();
			return formulario;

		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new Exception("No se pudo crear el formulario");
		}
	}

	public void actualizar(TemplateFormulario usuario) throws Exception {
		try {

			em.merge(usuario);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo actualizar el formulario");
		}

	}

	public void borrar(Long id) throws Exception {

		try {

			TemplateFormulario formulario = em.find(TemplateFormulario.class, id);
			em.remove(formulario);
			em.flush();

		} catch (PersistenceException e) {
			throw new Exception("No se pudo borrar el TemplateFormulario");
		}

	}

	public List<TemplateFormulario> obtenerTodos() {

		TypedQuery<TemplateFormulario> query = em.createQuery("SELECT u FROM TemplateFormulario u",
				TemplateFormulario.class);

		return query.getResultList();
	}

	public TemplateFormulario getFormulario(String titulo) {
		try {
			TypedQuery<TemplateFormulario> query = em
					.createQuery("SELECT u from TemplateFormulario u WHERE u.titulo = :titulo ",
							TemplateFormulario.class)
					.setParameter("titulo", titulo);
			return query.getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public TemplateFormulario getFormularioById(Long id) {

		TemplateFormulario formulario = em.find(TemplateFormulario.class, id);

		return formulario;
	}

	public void desactivar(Long id) throws Exception {

		TemplateFormulario formulario = em.find(TemplateFormulario.class, id);
		formulario.setActivoSN("N");
		em.merge(formulario);
		em.flush();

	}

	public List<TemplateFormulario> filtrado(String cadena) {

		TypedQuery<TemplateFormulario> query = em.createQuery(
				"Select u From TemplateFormulario u where u.titulo like '%" + cadena + "%'",
				(TemplateFormulario.class));

		List<TemplateFormulario> lst = query.getResultList();
		return lst;

	}
}
