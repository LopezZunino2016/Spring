package es.altair.inventario.dao;

import es.altair.inventario.bean.Usuario;

public interface UsuarioDAO {

	Usuario comprobarUsuario(String login, String password);
	boolean comprobarAlias(Usuario usu);
	int registrar(Usuario usu);
}
