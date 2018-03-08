package es.altair.inventario.bean;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="articulo")
public class Articulo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idArticulos; 
	private String nombre; 
	private String descripcion; 
	private String codigo;
	private int cantidad; 
	public Articulo() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public Articulo(int idArticulos, String nombre, String descripcion, String codigo, int cantidad) {
		super();
		this.idArticulos = idArticulos;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.cantidad = cantidad;
	}
	public Articulo( String nombre, String descripcion, String codigo, int cantidad) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.cantidad = cantidad;
	}


	public int getIdArticulos() {
		return idArticulos;
	}

	public void setIdArticulos(int idArticulos) {
		this.idArticulos = idArticulos;
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

	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Articulo [idArticulos=" + idArticulos + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ "]";
	} 
}
	