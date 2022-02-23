package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class ActividadCampo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Usuario usuario;

	@Column(length = 45)
	private String nombre;

	@Column(length = 500)
	private String descripcion;

	private Date fechaInicio;

	@Column(length = 8)
	private String activoSN = "Activo";

	@ManyToOne
	private Estacion estacion;

	@ManyToOne
	private Metodo metodo;

	@ManyToOne
	private Region region;

	@ManyToOne
	private Equipamiento equipamiento;

	@ManyToOne
	private TemplateFormulario formulario;

	@ManyToOne
	private Localidad localidad;

	@ManyToOne
	private Departamento departamento;

	public ActividadCampo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActividadCampo(Long id, Usuario usuario, String nombre, String descripcion, Date fechaInicio,
			String activoSN, Estacion estacion, Metodo metodo, Region region, Equipamiento equipamiento,
			TemplateFormulario formulario, Localidad localidad, Departamento departamento) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.activoSN = activoSN;
		this.estacion = estacion;
		this.metodo = metodo;
		this.region = region;
		this.equipamiento = equipamiento;
		this.formulario = formulario;
		this.localidad = localidad;
		this.departamento = departamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getActivoSN() {
		return activoSN;
	}

	public void setActivoSN(String activoSN) {
		this.activoSN = activoSN;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	public Metodo getMetodo() {
		return metodo;
	}

	public void setMetodo(Metodo metodo) {
		this.metodo = metodo;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Equipamiento getEquipamiento() {
		return equipamiento;
	}

	public void setEquipamiento(Equipamiento equipamiento) {
		this.equipamiento = equipamiento;
	}

	public TemplateFormulario getFormulario() {
		return formulario;
	}

	public void setFormulario(TemplateFormulario formulario) {
		this.formulario = formulario;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((formulario == null) ? 0 : formulario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActividadCampo other = (ActividadCampo) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (formulario == null) {
			if (other.formulario != null)
				return false;
		} else if (!formulario.equals(other.formulario))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
