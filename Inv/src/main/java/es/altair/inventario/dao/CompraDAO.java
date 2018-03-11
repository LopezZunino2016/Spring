package es.altair.inventario.dao;

import java.util.List;

import es.altair.inventario.bean.Compra;
import es.altair.inventario.bean.Usuario;


public interface CompraDAO {

	List<Compra>listar(Usuario usu); 
	int anadirCompra(Compra c);
	void borrar(int id);

}
