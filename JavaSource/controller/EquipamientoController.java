package controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import dao.EquipamientoBean;
import models.Equipamiento;
import models.Localidad;

@Named(value = "equipBean")
@SessionScoped
public class EquipamientoController implements Serializable {

	public static final long serialVersionUID = 1L;

	@Inject
	EquipamientoBean eBean;

	/**
	 * 
	 * @return List<Equipamiento>
	 */
	public List<Equipamiento> obtenerTodos() {

		return eBean.obtenerTodos();
	}

}
