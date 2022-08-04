package api.v1;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.ActividadCampoController;
import controller.FormularioController;
import controller.ReporteController;
import models.ActividadCampo;

@Path("v1/reportes")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class ActividadCampoAPI {

	@Inject
	ReporteController rc;

	@Inject
	ActividadCampoController ac;

	@Inject
	FormularioController fc;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@GET
	@Path("/")
	public Response listar() {
		try {
			return Response.ok(rc.todas(), MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server error").build();
		}
	}

	@GET
	@Path("estacion/{id}")
	public Response listarPorEstacion(@PathParam("id") String id) {

		try {
			return Response.ok(ac.porEstacion(Long.parseLong(id)), MediaType.APPLICATION_JSON).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server error").build();
		}
	}

	@GET
	@Path("metodo/{id}")
	public Response listarPorMetodo(@PathParam("id") String id) {

		try {
			return Response.ok(ac.porMetodo(Long.parseLong(id)), MediaType.APPLICATION_JSON).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server error").build();
		}
	}

	@GET
	@Path("rolUsuario/{experto}")
	public Response listarPorUsuario() {

		try {

			return Response.ok(ac.porUsuario(), MediaType.APPLICATION_JSON).build();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server error").build();
		}

	}

	@POST
	@Path("crear")
	public Response crear(@HeaderParam("nombre") String nombre, @HeaderParam("descripcion") String descripcion,
			@HeaderParam("usuario") Long idUsuario, @HeaderParam("metodo") Long idMetodo,
			@HeaderParam("estacion") Long idEstacion, @HeaderParam("formulario") Long idForm,
			@HeaderParam("localidad") Long idLocalidad, @HeaderParam("region") Long idRegion,
			@HeaderParam("equipamiento") Long idEquipamiento, @HeaderParam("departamento") Long idDepartamento,
			@HeaderParam("activo") String activoSN, @HeaderParam("tipoMuestra") Long idTipoMuestra) {

		try {
			ActividadCampo actividad = ac.crear(nombre, descripcion, idUsuario, idMetodo, idEstacion, idForm,
					idLocalidad, idRegion, idEquipamiento, idDepartamento, activoSN, idTipoMuestra);
			if (actividad != null) {
				return Response.ok(actividad).build();
			}
			return Response.status(Response.Status.BAD_REQUEST).entity("Datos no correctos").build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server error").build();
		}

	}

}
