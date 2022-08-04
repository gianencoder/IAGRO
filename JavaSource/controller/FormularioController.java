package controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import dao.DepartamentoBean;
import dao.EquipamientoBean;
import dao.EstacionBean;
import dao.TemplateFormularioBean;
import dao.LocalidadBean;
import dao.MetodoBean;
import dao.ParametroBean;
import dao.RegionBean;
import dao.UsuarioBean;
import enums.Visibilidad;
import models.Departamento;
import models.Equipamiento;
import models.Estacion;
import models.TemplateFormulario;
import models.Localidad;
import models.Metodo;
import models.Parametro;
import models.Region;
import models.TipoParametro;
import models.Usuario;

@Named(value = "formBean")
@SessionScoped
public class FormularioController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String paramName;
	private String paramType;
	private boolean paramObl;
	private TipoParametro tp;
	private String userId;
	private String locId;;
	private String metId;
	private String depId;
	private String regid;
	private String eqpId;
	private String estId;
	private String paramId;
	private String activo = "";
	private String cadena = "";
	private String nombre;

	private String titulo;
	private String visibilidad;
	private String descripcion;
	List<Usuario> listaUsuarios;
	List<Localidad> listaLocalidad;
	List<Departamento> listaDepartamento;
	List<Region> listaRegion;
	List<Metodo> listaMetodo;
	List<Estacion> listaEstacion;
	List<Equipamiento> listaEquipamiento;
	private List<TemplateFormulario> todos;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Inject
	TemplateFormularioBean fBean;
	@Inject
	UsuarioBean uBean;
	@Inject
	LocalidadBean lBean;
	@Inject
	DepartamentoBean dBean;
	@Inject
	RegionBean rBean;
	@Inject
	MetodoBean mBean;
	@Inject
	EstacionBean eBean;
	@Inject
	EquipamientoBean eqBean;
	@Inject
	ParametroBean pBean;

	public String crear() {

		TemplateFormulario f = new TemplateFormulario();
		Parametro p = new Parametro();
		p.setFormulario(f);

		Map<String, Object> sessionMap2 = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap2.put("parametro", p);

		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("formulario", f);

		return "altaForm?faces-redirect=true";

	}

	public String detalle(Long id) {
		TemplateFormulario f = new TemplateFormulario();
		f = fBean.getFormularioById(id);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("formulario", f);
		return "templateView?faces-redirect=true";
	}

	public List<TemplateFormulario> obtenerTodos() {
		try {
			return todos = fBean.obtenerTodos();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public void crearParametro(Parametro parametro) {

		try {
			pBean.crear(parametro);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public String guardarTemplate(TemplateFormulario formulario) {

		TemplateFormulario f = new TemplateFormulario();
		f = fBean.getFormulario(formulario.getTitulo());
		try {

			if (formulario.getDepartamento() == null) {
				formulario.setDepartamento("SI");
			}
			if (formulario.getLocalidad() == null) {
				formulario.setLocalidad("SI");
			}
			if (formulario.getEquipamiento() == null) {
				formulario.setEquipamiento("SI");
			}
			if (formulario.getEstacion() == null) {
				formulario.setEstacion("SI");
			}
			if (formulario.getMetodo() == null) {
				formulario.setMetodo("SI");
			}
			if (formulario.getRegion() == null) {
				formulario.setRegion("SI");
			}

			if (f != null) {

				String msg = "El titulo: '" + f.getTitulo() + "' no esta disponible";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
				FacesContext ctx = FacesContext.getCurrentInstance();
				ctx.addMessage(null, message);
				return null;

			}

			if (visibilidad.equals("Público")) {
				formulario.setVisibilidad(Visibilidad.PUBLICO);
			}
			if (visibilidad.equals("Privado")) {
				formulario.setVisibilidad(Visibilidad.PRIVADO);
			}
			if (visibilidad.equals("Equipo")) {
				formulario.setVisibilidad(Visibilidad.EQUIPO);
			}
			fBean.crear(formulario);
			return "gestionForm?faces-redirect=true";

		} catch (

		Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public List<TemplateFormulario> filtro() {

		try {
			todos = fBean.filtrado(cadena);
			if (!todos.isEmpty()) {
				return todos;
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public TemplateFormulario getById(Long id) {
		try {

			return fBean.getFormularioById(id);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public String volver() {
		return "gestionForm?faces-redirect=true";
	}

	public String crearUsedForm(Long id) {

		TemplateFormulario f = new TemplateFormulario();
		f = fBean.getFormularioById(id);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("formulario", f);
		return "nuevaActividad?faces-redirect=true";
	}

	public List<Usuario> allUser() {

		listaUsuarios = uBean.buscarTodos();

		return listaUsuarios;
	}

	public List<Localidad> getLocalidad() {
		return listaLocalidad = lBean.obtenerTodos();
	}

	public List<Departamento> getDepartamento() {
		return listaDepartamento = dBean.obtenerTodos();
	}

	public List<Region> getRegion() {
		return listaRegion = rBean.obtenerTodos();
	}

	public List<Metodo> getMetodo() {
		return listaMetodo = mBean.obtenerTodos();
	}

	public List<Estacion> getEstacion() {
		return listaEstacion = eBean.obtenerTodos();
	}

	public List<Equipamiento> getEquipamiento() {
		return listaEquipamiento = eqBean.obtenerTodos();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Localidad> getListaLocalidad() {
		return listaLocalidad;
	}

	public void setListaLocalidad(List<Localidad> listaLocalidad) {
		this.listaLocalidad = listaLocalidad;
	}

	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public String getMetId() {
		return metId;
	}

	public void setMetId(String metId) {
		this.metId = metId;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}

	public String getVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(String visibilidad) {
		this.visibilidad = visibilidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEqpId() {
		return eqpId;
	}

	public void setEqpId(String eqpId) {
		this.eqpId = eqpId;
	}

	public String getEstId() {
		return estId;
	}

	public void setEstId(String estId) {
		this.estId = estId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public boolean isParamObl() {
		return paramObl;
	}

	public void setParamObl(boolean paramObl) {
		this.paramObl = paramObl;
	}

	public TipoParametro getTp() {
		return tp;
	}

	public void setTp(TipoParametro tp) {
		this.tp = tp;
	}

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public List<TemplateFormulario> getTodos() {
		return todos;
	}

	public void setTodos(List<TemplateFormulario> todos) {
		this.todos = todos;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
