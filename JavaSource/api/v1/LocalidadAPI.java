package api.v1;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.LocalidadController;

@Path("v1/localidades")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class LocalidadAPI {

	
	@Inject
	LocalidadController l;

	
	@Path("/")
	@GET
	public Response listar() {
		try {
			return Response.ok(l.obtenerTodos(), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server error").build();
		}
	}
}
