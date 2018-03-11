package es.altair.inventario.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuario;
	private String nombre;
	private String apellidos;
	private String alias;
	private String password;
	private int tipo;
	
	@OneToMany(mappedBy="usuario")
	private Set<Compra> compra = new HashSet<Compra>();
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Usuario(String nombre, String apellidos, String alias, String password, int tipo) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.alias = alias;
		this.password = password;
		this.tipo = tipo;
	}
	

	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	public int getTipo() {
		return tipo;
	}


	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return nombre + " " + apellidos;
	}
	
	
}
