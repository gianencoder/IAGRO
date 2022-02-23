package controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.MetodoBean;
import models.Metodo;

@Named(value = "metodoBean")
@SessionScoped
public class MetodoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	MetodoBean mBean;

	// @return List<Metodo>

	public List<Metodo> obtenerTodos() {
		return mBean.obtenerTodos();
	}
}
