package controller;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import dao.ConexionAD;
import dao.UsuarioBean;

import models.Usuario;

@Named(value = "login")
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "*Campo obligatorio")
	private String username;
	@NotEmpty(message = "*Campo obligatorio")
	private String password;
	FacesContext context = FacesContext.getCurrentInstance();

	@Inject
	UsuarioBean dao;

	@Inject
	ConexionAD ad;

	public void verificarSesion() throws IOException {

		try {

			Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("logueado");

			if (us == null) {
				context.getExternalContext().redirect("login.jsf");
			}
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}
	}

	public String conexion() {

		try {

			if (ad.authenticateComun(username, password) != null || ad.authenticateAdmin(username, password) != null
					|| ad.authenticateExperto(username, password) != null) {

				Usuario user = dao.obtenerUsuarioUsername(username);

				if (user != null) {
					String clave = new String(Hex.encodeHex(DigestUtils.md5(password)));

					if (user.getContrasena().equals(clave) && user.getUsername().equals(username)) {

						FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("logueado", user);
						return "bienvenido?faces-redirect=true";

					}
				}
			}
			String msg = "Credenciales incorrectas";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error ", msg);
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, message);
			return null;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
