package models;

import java.io.Serializable;

import javax.persistence.*;

import enums.TipoMultimedia;

@Entity
public class Multimedia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 150)
	private String url;

	@Enumerated
	private TipoMultimedia tipo;

	@ManyToOne
	private ActividadCampo actividadCampo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TipoMultimedia getTipo() {
		return tipo;
	}

	public void setTipo(TipoMultimedia tipo) {
		this.tipo = tipo;
	}

	public ActividadCampo getReporte() {
		return actividadCampo;
	}

	public void setReporte(ActividadCampo actividadCampo) {
		this.actividadCampo = actividadCampo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((actividadCampo == null) ? 0 : actividadCampo.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Multimedia other = (Multimedia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (actividadCampo == null) {
			if (other.actividadCampo != null)
				return false;
		} else if (!actividadCampo.equals(other.actividadCampo))
			return false;
		if (tipo != other.tipo)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}
