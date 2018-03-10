package es.altair.inventario.dao;

import java.util.List;

import es.altair.inventario.bean.Usuario;

public interface UsuarioDAO {

	Usuario comprobarUsuario(String login, String password);
	boolean comprobarAlias(Usuario usu);
	int registrar(Usuario usu);
	List<Usuario> listar();
	void borrar(int idUsuBorrar);
	Usuario obtenerUsuarioPorId(int idUsu);
	void actualizarUsuario(Usuario usu);
}
