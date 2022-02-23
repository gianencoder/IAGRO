package controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.LocalidadBean;
import models.Localidad;

@Named(value = "localidadBean")
@SessionScoped
public class LocalidadController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	LocalidadBean lBean;

	/**
	 * 
	 * @return List<Localidad>
	 */
	public List<Localidad> obtenerTodos() {

		return lBean.obtenerTodos();
	}
}
