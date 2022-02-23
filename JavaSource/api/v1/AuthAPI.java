package api.v1;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import controller.LoginBean;
import controller.UsuarioController;
import dao.ConexionAD;
import dao.UsuarioBean;
import models.Usuario;

@Path("v1/login")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class AuthAPI {

	@Inject
	LoginBean lBean;

	@Inject
	UsuarioController uBean;

	@Inject
	ConexionAD ad;

	@POST
	@Path("/")
	public Response login(@HeaderParam("username") String username, @HeaderParam("password") String password) {
		System.out.println(username);
		System.out.println(password);

		try {

			if (ad.authenticateComun(username, password) != null || ad.authenticateAdmin(username, password) != null
					|| ad.authenticateExperto(username, password) != null) {

				Usuario user = uBean.porUsuario(username);

				if (user != null) {
					String clave = new String(Hex.encodeHex(DigestUtils.md5(password)));

					if (user.getContrasena().equals(clave) && user.getUsername().equals(username)) {
						return Response.ok(user).build();

					}
				}
			}
			return Response.status(Response.Status.NOT_FOUND).entity("Credenciales incorrectas").build();

		} catch (Exception e) {

			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server error").build();
		}
	}
}
