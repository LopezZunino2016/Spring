package es.altair.inventario.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import es.altair.inventario.bean.Articulo;
import es.altair.inventario.bean.Usuario;


public class UsuarioDAOImpl implements UsuarioDAO {

	private String pass = "Usuario1234$%";
	private SessionFactory sessionFactory;
		
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	@Override
	public Usuario comprobarUsuario(String alias, String password) {
		Session sesion = sessionFactory.getCurrentSession();	
		Usuario usu = null;
		usu = (Usuario) sesion.createQuery("SELECT u FROM Usuario u WHERE alias=:a " 
		        + "AND password=AES_ENCRYPT(:p, :passphrase)")
				.setParameter("a", alias)
				.setParameter("p", password)
				.setParameter("passphrase", pass)
				.uniqueResult();
		
		
		
		return usu;
	}
	
	@Transactional
	@Override
	public boolean comprobarAlias(Usuario usu) {
		boolean correcto = false; 
		
		Session sesion=sessionFactory.getCurrentSession();
		System.out.println(usu.getAlias());
		Usuario u =(Usuario) sesion.createQuery("FROM Usuario WHERE alias=:a")
				.setParameter("a", usu.getAlias())
				.uniqueResult();
		if(u !=  null)
			correcto = true;
		else 
			correcto = false;
		
		System.out.println(correcto);
		return correcto;
	}
	
	@Transactional
	@Override
	public int registrar(Usuario usu) {
		int filas = 0; 
		Session sesion=sessionFactory.getCurrentSession();
		System.out.println(usu);
		usu.setTipo(2);
		filas = sesion.createSQLQuery("INSERT INTO usuarios(nombre, apellidos, alias, password, tipo) values (:n, :a, :al,  AES_ENCRYPT(:p, :passphrase), :t)")
				.setParameter("n", usu.getNombre())
				.setParameter("a", usu.getApellidos())
				.setParameter("al", usu.getAlias())
				.setParameter("p", usu.getPassword())
				.setParameter("passphrase", pass)			
				.setParameter("t", usu.getTipo())
				.executeUpdate();
		
		return filas;
	}

	@Transactional
	@Override
	public List<Usuario> listar() {
		Session sesion=sessionFactory.getCurrentSession();
		List<Usuario> lista = sesion.createQuery("FROM Usuario u ").list(); 
		 
		System.out.println("tamaño de lista: " + lista.size());
		
		return lista;
	}

	@Transactional
	@Override
	public void borrar(int idUsuBorrar) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.createQuery("DELETE FROM Usuario WHERE idUsuario=:i")
							.setParameter("i", idUsuBorrar)
							.executeUpdate();
	}
	
	@Transactional
	@Override
	public Usuario obtenerUsuarioPorId(int idUsu) {
		Session sesion=sessionFactory.getCurrentSession();

		return (Usuario)sesion.get(Usuario.class, idUsu);
	}

	@Transactional
	@Override
	public void actualizarUsuario(Usuario usu) {
		Session sesion=sessionFactory.getCurrentSession();
		
		sesion.createQuery("UPDATE Usuario SET nombre=:n, apellidos=:a, alias=:al WHERE idUsuario=:i")
				.setParameter("n", usu.getNombre())
				.setParameter("a", usu.getApellidos())
				.setParameter("al", usu.getAlias())
				.setParameter("i", usu.getIdUsuario())
				.executeUpdate();
	}
	
	

}
