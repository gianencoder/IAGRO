package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import models.ActividadCampo;

@Stateless
@LocalBean
public class ReporteBean {

	@PersistenceContext
	EntityManager em;

	public ReporteBean() {
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return List<ActividadCampo>
	 */
	public List<ActividadCampo> porUsuario(Long id) {

		List<ActividadCampo> lista = new ArrayList<ActividadCampo>();

		try {
			TypedQuery<ActividadCampo> query = em.createQuery("SELECT a FROM ActividadCampo a", ActividadCampo.class);

			List<ActividadCampo> actividades = query.getResultList();
			for (ActividadCampo actividadCampo : actividades) {
				if (actividadCampo.getUsuario().getRol().getId() == id) {
					lista.add(actividadCampo);
				}
			}

			return lista;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return List<ActividadCampo>
	 */
	public List<ActividadCampo> porEstacion(Long id) {

		List<ActividadCampo> lista = new ArrayList<ActividadCampo>();

		try {
			TypedQuery<ActividadCampo> query = em.createQuery("SELECT a FROM ActividadCampo a", ActividadCampo.class);

			List<ActividadCampo> actividades = query.getResultList();
			for (ActividadCampo actividadCampo : actividades) {
				if (actividadCampo.getEstacion().getId() == id) {
					lista.add(actividadCampo);
				}
			}

			return lista;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 *
	 * 
	 * @param id
	 * @return List<ActividadCampo>
	 */
	public List<ActividadCampo> porMetodo(Long id) {

		List<ActividadCampo> lista = new ArrayList<ActividadCampo>();

		try {
			TypedQuery<ActividadCampo> query = em.createQuery("SELECT a FROM ActividadCampo a", ActividadCampo.class);

			List<ActividadCampo> actividades = query.getResultList();
			for (ActividadCampo actividadCampo : actividades) {
				if (actividadCampo.getMetodo().getId() == id) {
					lista.add(actividadCampo);
				}
			}

			return lista;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return List<ActividadCampo>
	 */
	@SuppressWarnings("deprecation")
	public List<ActividadCampo> porFecha(String inicio, String fin) {
		try {

			Date dInicio = new SimpleDateFormat("dd/MM/yyyy").parse(inicio);
			Date dFinal = new SimpleDateFormat("dd/MM/yyyy").parse(fin);
			dInicio.setHours(0);
			dInicio.setMinutes(0);
			dInicio.setSeconds(0);

			dFinal.setHours(23);
			dFinal.setMinutes(59);
			dFinal.setSeconds(59);

			TypedQuery<ActividadCampo> query = em.createQuery(
					"SELECT a FROM ActividadCampo a WHERE a.fechaInicio > :inicio AND a.fechaInicio < :fin ",
					ActividadCampo.class).setParameter("inicio", dInicio).setParameter("fin", dFinal);
			List<ActividadCampo> actividades = query.getResultList();
			return actividades;
		} catch (Exception e) {
			return null;
		}
	}

	public List<ActividadCampo> todos() {
		try {
			TypedQuery<ActividadCampo> query = em.createQuery("SELECT a FROM ActividadCampo a", ActividadCampo.class);
			return query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
