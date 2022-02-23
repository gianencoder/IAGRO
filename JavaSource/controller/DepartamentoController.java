package controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.DepartamentoBean;
import models.Departamento;

@Named(value="departamentoBean")
@SessionScoped
public class DepartamentoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	DepartamentoBean dBean;

	/**
	 * 
	 * @return List<Departamento>
	 */
	public List<Departamento> obtenerTodos() {
		return dBean.obtenerTodos();
	}
}
