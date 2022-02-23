package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import models.Rol;
import models.Usuario;

@Stateless
@LocalBean
public class UsuarioBean {

	@PersistenceContext
	EntityManager em;

	public UsuarioBean() {
	}

	public Usuario crear(Usuario usuario) throws Exception {

		try {
			em.persist(usuario);
			em.flush();
			return usuario;

		} catch (PersistenceException e) {
			throw new Exception(e.getMessage());
		}
	}

	public Rol seleccionarRol(Long id) {

		try {
			Rol r = em.find(Rol.class, id);
			return r;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public Usuario obtenerUsuarioUsername(String username) {

		try {
			TypedQuery<Usuario> query = em
					.createQuery("SELECT u from Usuario u WHERE u.enabled =:enabled and u.username = :username ",
							Usuario.class)
					.setParameter("username", username).setParameter("enabled", true);
			return query.getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public Usuario obtenerUsername(String username) {

		try {
			TypedQuery<Usuario> query = em
					.createQuery("SELECT u from Usuario u WHERE u.username = :username ", Usuario.class)
					.setParameter("username", username);
			return query.getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public Usuario obtenerMail(String mail) {

		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u from Usuario u WHERE u.mail =:mail", Usuario.class)
					.setParameter("mail", mail);
			return query.getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public Usuario obtenerCI(String cedula) {

		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT u from Usuario u WHERE u.cedula =:cedula", Usuario.class)
					.setParameter("cedula", cedula);
			return query.getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public List<Usuario> allUsers() {

		TypedQuery<Usuario> query = em.createQuery("SELECT u from Usuario u ORDER BY u.nombre", Usuario.class);

		return query.getResultList();

	}

	public List<Usuario> filtrado(String cadena) {

		TypedQuery<Usuario> query = em.createQuery(
				"Select u From Usuario u where u.enabled= 1 and u.username like '%" + cadena + "%'", (Usuario.class));

		List<Usuario> lst = query.getResultList();
		return lst;

	}

	public List<Usuario> filtrado1(String cadena) {

		TypedQuery<Usuario> query = em
				.createQuery("Select u From Usuario u where u.enabled=:enabled  and u.username like '%" + cadena + "%'",
						(Usuario.class))
				.setParameter("enabled", false);

		List<Usuario> lst = query.getResultList();
		return lst;

	}

	public List<Usuario> buscarTodos() throws PersistenceException {
		try {

			String query = "Select u from Usuario u where u.enabled = 1";
			List<Usuario> resultList = (List<Usuario>) em.createQuery(query, Usuario.class).getResultList();
			return resultList;
		} catch (PersistenceException e) {
			throw new PersistenceException("No se pudo hacer la consulta." + e.getMessage(), e);
		}

	}

	public List<Usuario> buscarInactivos() throws PersistenceException {
		try {

			String query = "Select u from Usuario u where u.enabled = 0";
			List<Usuario> resultList = (List<Usuario>) em.createQuery(query, Usuario.class).getResultList();
			return resultList;
		} catch (PersistenceException e) {
			throw new PersistenceException("No se pudo hacer la consulta." + e.getMessage(), e);
		}

	}

	public Usuario eliminarUsuario(Long id) {

		Usuario usuario = em.find(Usuario.class, id);
		usuario.setEnabled(false);

		try {
			em.merge(usuario);
			em.flush();
			return usuario;
		} catch (PersistenceException e) {
			throw new PersistenceException("No se pudo borrar el usuario. Id=");
		}

	}

	public Usuario activarUsuario(Long id) {

		Usuario usuario = em.find(Usuario.class, id);
		usuario.setEnabled(true);

		try {
			em.merge(usuario);
			em.flush();
			return usuario;
		} catch (PersistenceException e) {
			throw new PersistenceException("No se pudo activar el usuario. Id=");
		}

	}

	public Usuario buscarUsuario(Long id) {
		Usuario usuario = new Usuario();
		usuario = em.find(Usuario.class, id);
		em.flush();
		return usuario;
	}

	public Usuario editarUsuario(Usuario usuario) {

		try {
			em.merge(usuario);
			em.flush();
			return usuario;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("No se pudo editar el usuario");
			return null;
		}
	}

}
