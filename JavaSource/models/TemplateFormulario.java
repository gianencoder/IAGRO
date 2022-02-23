package models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import enums.Visibilidad;

/**
 * Entity implementation class for Entity: TemplateFormulario
 *
 */
@Entity
public class TemplateFormulario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String usuario;

	@Column(length = 100)
	@Length(min = 1, message = "* Titulo obligatorio")
	@Length(max = 15, message = "Su titulo debe contener hasta 15 caracteres")
	private String titulo;

	@Column(length = 250)
	private String descripcion;

	@Enumerated(value = EnumType.STRING)
	private Visibilidad visibilidad;

	@ManyToOne
	private TipoMuestra tipoMuestra;

	private String equipamiento;

	private String metodo;

	private String region;

	private String departamento;

	private String localidad;

	private String estacion;

	@OneToMany
	private List<Parametro> parametros;

	@Column()
	private String activoSN = "Inactivo";

	public TemplateFormulario() {
	}

	public TemplateFormulario(Long id, String usuario, String titulo, String descripcion, Visibilidad visibilidad,
			TipoMuestra tipoMuestra, String equipamiento, String metodo, String region, String departamento,
			String localidad, String estacion, String activoSN) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.visibilidad = visibilidad;
		this.tipoMuestra = tipoMuestra;
		this.equipamiento = equipamiento;
		this.metodo = metodo;
		this.region = region;
		this.departamento = departamento;
		this.localidad = localidad;
		this.estacion = estacion;
		this.activoSN = activoSN;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Visibilidad getVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(Visibilidad visibilidad) {
		this.visibilidad = visibilidad;
	}

	public TipoMuestra getTipoMuestra() {
		return tipoMuestra;
	}

	public void setTipoMuestra(TipoMuestra tipoMuestra) {
		this.tipoMuestra = tipoMuestra;
	}

	public String getEquipamiento() {
		return equipamiento;
	}

	public void setEquipamiento(String equipamiento) {
		this.equipamiento = equipamiento;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getEstacion() {
		return estacion;
	}

	public void setEstacion(String estacion) {
		this.estacion = estacion;
	}

	public String getActivoSN() {
		return activoSN;
	}

	public void setActivoSN(String activoSN) {
		this.activoSN = activoSN;
	}

}
