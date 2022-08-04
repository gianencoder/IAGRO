package models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 35)
	@Size(min = 1, message = "*Campo obligatorio")
	private String nombre;

	@Column(length = 35)
	@NotNull(message = "*Campo obligatorio")
	@Size(min = 1, message = "*Campo obligatorio")
	private String apellido;

	@Column(length = 35, unique = true)
	@NotNull(message = "*Campo obligatorio")

	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Solo se permiten letras")
	@Size(min = 8, max = 35, message = "Su usuario debe contener al menos 8 caracteres")
	@NotEmpty(message = "*Campo obligatorio")
	private String username;

	@Column(length = 45, unique = true)
	// @Email(message = "Ingrese un mail válido")
	@NotNull(message = "*Campo obligatorio")
	@Size(min = 1, message = "*Campo obligatorio")
	@Pattern(regexp = "^([\\w]*[\\w\\.]*(?!\\. +)@ *(iagro).com)", message = "Su email no pertenece a nuestra organización (@iagro.com)")
	private String mail;

	@Column(length = 35)
	@NotNull(message = "*Campo obligatorio")
	@Size(min = 8, message = "Su contraseña debe tener al menos 8 caracteres")
	private String contrasena;

	@Column(length = 50)
	private String instituto;

	@Column(length = 50)
	private String profesion;

	@Column(length = 1)
	private boolean enabled = true;

	@Column
	@Pattern(regexp = "^$||[1-9]\\.\\d{3}\\.\\d{3}\\-[1-9]$", message = "Debe contener formato de cédula uruguaya; incluido puntos, guiónes y código verificador")
	private String cedula;

	@ManyToOne(optional = false)
	@NotNull(message = "*Campo obligatorio")
	private Rol rol;

	public Usuario() {

	}

	public Usuario(Long id, @Size(min = 1, message = "*Campo obligatorio") String nombre,
			@NotNull(message = "*Campo obligatorio") @Size(min = 1, message = "*Campo obligatorio") String apellido,
			@NotNull(message = "*Campo obligatorio") String username,
			@NotNull(message = "*Campo obligatorio") @Size(min = 1, message = "*Campo obligatorio") @Pattern(regexp = "^([\\w]*[\\w\\.]*(?!\\. +)@ *(iagro).com)", message = "Su email no pertenece a nuestra organización (@iagro.com)") String mail,
			@NotNull(message = "*Campo obligatorio") @Size(min = 8, message = "Su contraseña debe tener al menos 8 caracteres") String contrasena,
			String instituto, String profesion, boolean enabled,
			@Pattern(regexp = "^$||[1-9]\\.?\\d{3}\\.?\\d{3}\\-[1-9]$", message = "Debe contener formato de cédula uruguaya; incluido puntos, guiónes y código verificador") String cedula,
			@NotNull(message = "*Campo obligatorio") Rol rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.username = username;
		this.mail = mail;
		this.contrasena = contrasena;
		this.instituto = instituto;
		this.profesion = profesion;
		this.enabled = enabled;
		this.cedula = cedula;
		this.rol = rol;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getInstituto() {
		return instituto;
	}

	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());

		result = prime * result + ((id == null) ? 0 : id.hashCode());

		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());

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
		Usuario other = (Usuario) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
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
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", usuario=" + "" + ", mail="
				+ ", contrasenia=" + "" + ", rol=" + rol + "]";
	}

}
