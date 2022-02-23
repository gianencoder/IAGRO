package controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import dao.RegionBean;
import models.Region;

@Named(value = "regionBean")
@SessionScoped
public class RegionController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	RegionBean r;

	/**
	 * 
	 * @return List<Region>
	 */
	public List<Region> obtenerTodos() {

		return r.obtenerTodos();
	}
}
