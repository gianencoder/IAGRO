package controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.EstacionBean;
import models.Estacion;

@Named(value = "estacionBean")
@SessionScoped
public class EstacionController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EstacionBean eBean;

	/**
	 * 
	 * @return List<Estacion>
	 */
	public List<Estacion> obtenerTodos() {
		return eBean.obtenerTodos();
	}
}
