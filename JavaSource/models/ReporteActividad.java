package models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ReporteActividad
 *
 */
@Entity
public class ReporteActividad implements Serializable {

	private static final long serialVersionUID = 1L;

	public ReporteActividad() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(optional = false)
	private Usuario usuario;
	private Date fechaReporte;
	@ManyToOne
	private Equipamiento equipamiento;
	@ManyToOne
	private Metodo metodo;
	@ManyToOne
	private Region region;
	@ManyToOne
	private Departamento departamento;
	@ManyToOne
	private Estacion estacion;
	@ManyToOne
	private Localidad localidad;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Parametro> parametros = new HashSet<Parametro>();
	// private Set<String> parametroValor;

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

	public Date getFechaReporte() {
		return fechaReporte;
	}

	public void setFechaReporte(Date fechaReporte) {
		this.fechaReporte = fechaReporte;
	}

	public Equipamiento getEquipamiento() {
		return equipamiento;
	}

	public void setEquipamiento(Equipamiento equipamiento) {
		this.equipamiento = equipamiento;
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

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Set<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(Set<Parametro> parametros) {
		this.parametros = parametros;
	}

	@Override
	public String toString() {
		return "ReporteActividad [id=" + id + ", usuario=" + usuario + ", fechaReporte=" + fechaReporte
				+ ", equipamiento=" + equipamiento + ", metodo=" + metodo + ", region=" + region + ", departamento="
				+ departamento + ", estacion=" + estacion + ", localidad=" + localidad + ", parametros=" + parametros
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departamento == null) ? 0 : departamento.hashCode());
		result = prime * result + ((equipamiento == null) ? 0 : equipamiento.hashCode());
		result = prime * result + ((estacion == null) ? 0 : estacion.hashCode());
		result = prime * result + ((fechaReporte == null) ? 0 : fechaReporte.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
		result = prime * result + ((metodo == null) ? 0 : metodo.hashCode());
		result = prime * result + ((parametros == null) ? 0 : parametros.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
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
		ReporteActividad other = (ReporteActividad) obj;
		if (departamento == null) {
			if (other.departamento != null)
				return false;
		} else if (!departamento.equals(other.departamento))
			return false;
		if (equipamiento == null) {
			if (other.equipamiento != null)
				return false;
		} else if (!equipamiento.equals(other.equipamiento))
			return false;
		if (estacion == null) {
			if (other.estacion != null)
				return false;
		} else if (!estacion.equals(other.estacion))
			return false;
		if (fechaReporte == null) {
			if (other.fechaReporte != null)
				return false;
		} else if (!fechaReporte.equals(other.fechaReporte))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (localidad == null) {
			if (other.localidad != null)
				return false;
		} else if (!localidad.equals(other.localidad))
			return false;
		if (metodo == null) {
			if (other.metodo != null)
				return false;
		} else if (!metodo.equals(other.metodo))
			return false;
		if (parametros == null) {
			if (other.parametros != null)
				return false;
		} else if (!parametros.equals(other.parametros))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
