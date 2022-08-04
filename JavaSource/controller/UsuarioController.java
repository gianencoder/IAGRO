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
import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.codec.digest.DigestUtils;
import com.utils.ExceptionsTools;
import dao.RolBean;
import dao.UsuarioBean;
import models.Rol;
import models.Usuario;

@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Min(value = 1, message = "Seleccione un Rol válido")
	@Max(value = 3, message = "Seleccione un Rol válido")
	@Pattern(regexp = "[0-3]", message = "Seleccione un Rol válido")
	private String rolId;
	List<Rol> roles;
	Rol selectRol;
	String cadena = "";
	List<Usuario> listaUsuarios;

	@Column(length = 35)
	@NotNull(message = "*Campo obligatorio")
	@Size(min = 8, message = "Su contraseña debe tener al menos 8 caracteres")

	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Debe contener al menos una mayúscula, una minúscula y un numero, con un minimo de 8 digitos")
	private String pass;

	private String pass2;

	public UsuarioController() {

	}

	@Inject
	UsuarioBean uBean;

	@Inject
	RolBean rBean;

	
	public String crear() {

		Usuario u = new Usuario();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("usuario", u);
		return "alta?faces-redirect=true";
	}

	public String guardar(Usuario usuario) {

		Rol r = new Rol();
		r.setId(Long.parseLong(rolId));
		usuario.setRol(r);

		String clave = DigestUtils.md5Hex(pass);
		usuario.setContrasena(clave);
		Usuario user = uBean.obtenerUsername(usuario.getUsername());
		Usuario uMail = uBean.obtenerMail(usuario.getMail());
		Usuario uCi = uBean.obtenerCI(usuario.getCedula());

		if (user != null) { 

			String msg = "El usuario '" + usuario.getUsername() + "' no está disponible";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, message);

			Usuario u = new Usuario();
			Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			sessionMap.put("usuario", u);
			return "";
		}

		if (uMail != null) { 

			String msg = "El mail '" + usuario.getMail() + "' no está disponible";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, message);

			Usuario u = new Usuario();
			Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			sessionMap.put("usuario", u);
			return "";
		}

		if (uCi != null) { 
			String msg = "La CI '" + usuario.getCedula() + "' no está disponible";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, message);

			Usuario u = new Usuario();
			Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			sessionMap.put("usuario", u);
			return "";

		}

		try {
			
		
			if (usuario.getRol().getId() == 1) {
				usuario.setProfesion(null);
			}
			if (usuario.getRol().getId() == 3) {
				usuario.setInstituto(null);
			}
			if (usuario.getRol().getId() == 2) {
				usuario.setInstituto(null);
				usuario.setProfesion(null);
				usuario.setCedula(null);

			}

			if (pass.equals(pass2)) {

				if (uBean.crear(usuario) != null) {

					return "index?faces-redirect=true";
				}
				Usuario u = new Usuario();
				Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
				sessionMap.put("usuario", u);
				return "";

			}

			String msg = "Las contraseñas deben coincidir";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, message);
			return "";

		} catch (

		Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			String msg = "Ups! Algo salió mal";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, message);

			Usuario u = new Usuario();
			Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
			sessionMap.put("usuario", u);
			return "";

		}
	}


	public String actualizar(Usuario usuario) {

		Rol r = new Rol();
		r.setId(Long.parseLong(rolId));
		usuario.setRol(r);
		usuario.setContrasena("53d44fdf4d84ce068984590d7340e4ad");

		try {

			Usuario user = uBean.editarUsuario(usuario);
			if (usuario.getRol().getId() == 1) {
				user.setProfesion(null);
			} else if (usuario.getRol().getId() == 3) {
				user.setInstituto(null);
			}
			if (usuario.getRol().getId() == 2) {
				user.setInstituto(null);
				user.setProfesion(null);
				user.setCedula(null);
			}

			user.setRol(r);
			user.setContrasena(usuario.getContrasena());

			uBean.editarUsuario(user);
			return "index?faces-redirect=true";
		} catch (

		Exception e) {
			// TODO: handle exception
			Throwable rootException = ExceptionsTools.getCause(e);
			String msg1 = e.getMessage();
			String msg2 = ExceptionsTools.formatedMsg(rootException);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg1, msg2);
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			String msg = "¡Ups! Ocurrio algun problema";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, message);
			e.printStackTrace();
			return "";
		}
	}

	public List<Rol> obtenerRoles() {

		roles = rBean.obtenerTodos();
		return roles;
	}

	public List<Usuario> filtro() {

		try {
			listaUsuarios = uBean.filtrado(cadena);
			if (!listaUsuarios.isEmpty()) {
				return listaUsuarios;
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public List<Usuario> filtro1() {

		try {
			listaUsuarios = uBean.filtrado1(cadena);
			if (!listaUsuarios.isEmpty()) {
				return listaUsuarios;
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public List<Usuario> obtenerTodos() {

		listaUsuarios = uBean.buscarTodos();
		return listaUsuarios;
	}

	public String activar(Long id) {

		uBean.activarUsuario(id);
		getInactivos();
		return "";
	}

	public List<Usuario> getUsuario() {

		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		listaUsuarios = uBean.buscarTodos();
		return listaUsuarios;
	}

	public Usuario porUsuario(String username) {

		try {

			Usuario user = new Usuario();
			user = uBean.obtenerUsername(username);
			return user;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	public List<Usuario> getInactivos() {
		List<Usuario> listaInactivos = new ArrayList<Usuario>();
		listaInactivos = uBean.buscarInactivos();
		return listaInactivos;
	}
	

	public String editar(Long id) {

		Usuario u = new Usuario();

		u = uBean.buscarUsuario(id);
		selectRol = u.getRol();

		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("usuario", u);
		return "modificar?faces-redirect=true";
	}

	public String detalle(Long id) {
		Usuario u = new Usuario();
		u = uBean.buscarUsuario(id);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("usuario", u);
		return "detalle?faces-redirect=true";
	}

	public String eliminar(Long id) {
		uBean.eliminarUsuario(id);
		getUsuario();
		return "";
	}


	public String eliminarlo(Long id) {
		uBean.eliminarUsuario(id);
		return "index?faces-redirect=true";
	}

	public String cancelar() {
		return "index?faces-redirect=true";
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Rol getSelectRol() {
		return selectRol;
	}

	public void setSelectRol(Rol selectRol) {
		this.selectRol = selectRol;
	}

	public String getRolId() {
		return rolId;
	}

	public void setRolId(String rolId) {
		this.rolId = rolId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

}
