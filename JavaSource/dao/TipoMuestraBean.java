package dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import models.TipoMuestra;

@Stateless
@LocalBean
public class TipoMuestraBean {

	/**
	 * Default constructor.
	 */
	public TipoMuestraBean() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	EntityManager em;

	public List<TipoMuestra> getAll() throws Exception {

		try {
			TypedQuery<TipoMuestra> query = em.createQuery("SELECT t FROM TipoMuestra t", TipoMuestra.class);
			return query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
}
