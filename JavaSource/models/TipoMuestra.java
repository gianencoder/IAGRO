package models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoMuestra
 *
 */
@Entity

public class TipoMuestra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nombre;

	public TipoMuestra() {
		super();
	}

	public TipoMuestra(Long id, String nombre, List<TemplateFormulario> formulario) {
		super();
		this.id = id;
		this.nombre = nombre;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}

}
