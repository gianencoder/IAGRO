package controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import javax.inject.Named;

@Named(value = "logout")
@SessionScoped
public class LogoutBean implements Serializable {

	private static final long serialVersionUID = 1L;

	FacesContext context = FacesContext.getCurrentInstance();

	public void cerrarSesion() throws IOException {

		try {
			context.getExternalContext().invalidateSession();
			context.getExternalContext().redirect("login.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		}
	}
}
