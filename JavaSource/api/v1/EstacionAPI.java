package api.v1;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.EstacionController;

@Path("v1/estaciones")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class EstacionAPI {

	
	@Inject
	EstacionController ec;

	@GET
	@Path("/")
	public Response listar() {
		try {
			return Response.ok(ec.obtenerTodos(), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server error").build();
		}
	}

}
