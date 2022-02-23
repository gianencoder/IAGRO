package models;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Parametro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private boolean obligatorio;

	private String nombre;

	private String valor;

	@OneToOne(cascade = CascadeType.MERGE)
	private TipoParametro tipo;

	@ManyToOne(optional = true)
	private TemplateFormulario formulario;

	public Parametro() {
		super();
	}

	public Parametro(boolean obligatorio, TipoParametro tipo, TemplateFormulario formulario, String valor) {
		super();
		this.obligatorio = obligatorio;
		this.tipo = tipo;
		this.formulario = formulario;
		this.valor = valor;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(boolean obligatorio) {
		this.obligatorio = obligatorio;
	}

	public TipoParametro getTipo() {
		return tipo;
	}

	public void setTipo(TipoParametro tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TemplateFormulario getFormulario() {
		return formulario;
	}

	public void setFormulario(TemplateFormulario formulario) {
		this.formulario = formulario;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (obligatorio ? 1231 : 1237);
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Parametro other = (Parametro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (obligatorio != other.obligatorio)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
