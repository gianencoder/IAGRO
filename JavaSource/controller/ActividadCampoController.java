package controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.HeaderParam;

import dao.ActividadCampoBean;
import dao.ReporteBean;
import models.ActividadCampo;
import models.Departamento;
import models.Equipamiento;
import models.Estacion;
import models.Localidad;
import models.TemplateFormulario;
import models.Metodo;
import models.Region;
import models.Usuario;

@Named(value = "acBean")
@SessionScoped
public class ActividadCampoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String usuario = "";

	@Inject
	ActividadCampoBean aBean;

	@Inject
	ReporteBean rBean;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<ActividadCampo> porEstacion(Long id) {

		return rBean.porEstacion(id);
	}

	public List<ActividadCampo> porMetodo(Long id) {

		return rBean.porMetodo(id);
	}

	public List<ActividadCampo> porUsuario() {

		return rBean.porUsuario((long) 3);
	}

	public ActividadCampo crear(String nombre, String descripcion, Long idUsuario, Long idMetodo, Long idEstacion,
			Long idForm, Long idLocalidad, Long idRegion, Long idEquipamiento, Long idDepartamento, String activoSN) {
		// TODO Auto-generated method stub

		Estacion e = new Estacion();
		e.setId(idEstacion);

		Localidad l = new Localidad();
		l.setId(idLocalidad);

		Departamento d = new Departamento();
		d.setId(idDepartamento);

		Equipamiento eq = new Equipamiento();
		eq.setId(idEquipamiento);

		TemplateFormulario f = new TemplateFormulario();
		f.setId(idForm);

		Region r = new Region();
		r.setId(idRegion);

		Metodo m = new Metodo();
		m.setId(idMetodo);

		Usuario u = new Usuario();
		u.setId(idUsuario);

		try {

			ActividadCampo ac = new ActividadCampo();
			ac.setNombre(nombre);
			ac.setActivoSN(activoSN);
			ac.setDescripcion(descripcion);
			ac.setEstacion(e);
			ac.setMetodo(m);
			ac.setRegion(r);
			ac.setLocalidad(l);
			ac.setDepartamento(d);
			ac.setEquipamiento(eq);
			ac.setFechaInicio(new Date());
			ac.setFormulario(f);
			ac.setUsuario(u);
			return aBean.crear(ac);

		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;

		}
	}

}
