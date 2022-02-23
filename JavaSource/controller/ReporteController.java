package controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.ActividadCampoBean;
import dao.ReporteBean;
import models.ActividadCampo;

@Named(value = "reporteBean")
@SessionScoped
public class ReporteController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ReporteBean rBean;
	@Inject
	ActividadCampoBean acBean;

	private String estacionId = "";
	private String metodoId = "";
	private String fechaInicio = "";
	private String fechaFin = "";

	public String getEstacionId() {
		return estacionId;
	}

	public void setEstacionId(String estacionId) {
		this.estacionId = estacionId;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getMetodoId() {
		return metodoId;
	}

	public void setMetodoId(String metodoId) {
		this.metodoId = metodoId;
	}

	/**
	 *
	 * 
	 * @return String
	 */
	public String expertos() {
		return "reporteUsuario?faces-redirect=true";
	}

	/**
	 * 
	 * 
	 * @return List<ActividadCampo>
	 */
	public List<ActividadCampo> porUsuario(int id) {
		List<ActividadCampo> ac = rBean.porUsuario(Long.valueOf(id));
		return ac;
	}

	/**
	 * 
	 * 
	 * @return String
	 */
	public String estacion() {
		return "reporteEstacion?faces-redirect=true";
	}

	/**
	 * 
	 * 
	 * @return String
	 */
	public String metodo() {
		return "reporteMetodo?faces-redirect=true";
	}

	/**
	 *
	 * 
	 * @return String
	 */
	public String fecha() {
		this.fechaInicio = "";
		this.fechaFin = "";
		return "reporteFecha?faces-redirect=true";
	}

	/**
	 * 
	 * 
	 * @return List<ActividadCampo>
	 */
	public List<ActividadCampo> porEstacion() {
		if (this.estacionId.isEmpty() || this.estacionId.equals("all")) {
			return acBean.all();
		}
		return rBean.porEstacion(Long.parseLong(this.estacionId));
	}

	/**
	 * 
	 * 
	 * @return List<ActividadCampo>
	 */
	public List<ActividadCampo> porMetodo() {
		if (this.metodoId.isEmpty() || this.metodoId.equals("all")) {
			return acBean.all();
		}
		return rBean.porMetodo(Long.parseLong(this.metodoId));
	}

	/**
	 * 
	 * 
	 * @return List<ActividadCampo>
	 */
	public List<ActividadCampo> porFecha() {

		if (this.fechaInicio.isEmpty() || this.fechaInicio.equals("") || this.fechaFin.isEmpty()
				|| this.fechaFin.equals("")) {
			return acBean.all();
		}

		return rBean.porFecha(this.fechaInicio, this.fechaFin);
	}

	public List<ActividadCampo> todas() {

		return acBean.all();
	}
}
