package controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.TipoMuestraBean;
import models.TipoMuestra;

@Named(value = "tipoMuestraBean")
@SessionScoped
public class TipoMuestraController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	TipoMuestraBean tmBean;

	/**
	 * 
	 * @return List<TipoMuestra>
	 * @throws Exception
	 */
	public List<TipoMuestra> obtenerTodos() throws Exception {

		return tmBean.getAll();
	}

}
