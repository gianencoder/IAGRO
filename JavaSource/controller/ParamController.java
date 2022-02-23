package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import dao.TemplateFormularioBean;
import dao.ParametroBean;
import dao.TipoParametroBean;
import models.ActividadCampo;
import models.Parametro;
import models.TemplateFormulario;
import models.TipoParametro;
import models.Usuario;

@Named(value = "paramBean")
@SessionScoped
public class ParamController implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Parametro> listaParametro;
	private String nombre;
	private List<TipoParametro> datos;
	private String titulo;
	private boolean obligatorio;
	private String tipoId;

	@Inject
	ParametroBean pBean;

	@Inject
	TemplateFormularioBean fBean;

	@Inject
	TipoParametroBean tpBean;

	public void crear(TemplateFormulario formulario) {

		Parametro p = new Parametro();
		TipoParametro param = new TipoParametro();
		param.setId(Long.parseLong(tipoId));
		p.setTipo(param);

		try {
			p.setFormulario(formulario);
			p.setNombre(nombre);
			p.setObligatorio(obligatorio);

			if (pBean.crear(p) != null) {
				Parametro p1 = new Parametro();
				Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
				sessionMap.put("usuario", p1);

			} else {

				String msg = "Parámetro no creado";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
				FacesContext ctx = FacesContext.getCurrentInstance();
				ctx.addMessage(null, message);
				Parametro p1 = new Parametro();
				Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
				sessionMap.put("usuario", p1);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

	public String verParametros(Long id) {

		TemplateFormulario f = new TemplateFormulario();
		f = fBean.getFormularioById(id);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("formulario", f);
		return "verParametros?faces-redirect=true";
	}

	public List<Parametro> porFormulario(Long id) {

		try {
			listaParametro = pBean.porFormulario(id);

			if (!listaParametro.isEmpty()) {
				return listaParametro;
			} else {
				return null;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public List<TipoParametro> datos() {

		try {
			return datos = tpBean.obtenerTodos();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public List<Parametro> todos() {

		listaParametro = new ArrayList<Parametro>();
		listaParametro = pBean.obtenerTodos();
		try {

			return listaParametro;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public void setListaParametro(List<Parametro> listaParametro) {
		this.listaParametro = listaParametro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<TipoParametro> getDatos() {
		return datos;
	}

	public void setDatos(List<TipoParametro> datos) {
		this.datos = datos;
	}

	public boolean isObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(boolean obligatorio) {
		this.obligatorio = obligatorio;
	}

	public String getTipoId() {
		return tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
