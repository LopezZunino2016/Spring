package es.altair.inventario.dao;

import java.util.List;

import es.altair.inventario.bean.Articulo;

public interface ArticuloDAO {

	boolean comprobarNombre(String nombre);

	int anadirArticulo(Articulo art);

	List<Articulo> listar();

	Articulo obtenerArticuloPorId(int id);

	void borrar(int idArticulo);

	void ActualizarArticulo(Articulo art);
}
