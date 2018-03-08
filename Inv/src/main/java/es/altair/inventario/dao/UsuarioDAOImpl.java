package es.altair.inventario.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
		
		if ((Usuario) sesion.createQuery("From Usuario Where alias=:a")
					.setParameter("a", usu.getAlias())
					.uniqueResult() != null)
			correcto = false;
		
		return correcto;
	}
	
	@Transactional
	@Override
	public int registrar(Usuario usu) {
		int filas = 0; 
		Session sesion=sessionFactory.getCurrentSession();
		System.out.println(usu);
		usu.setTipo(1);
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
	
	

}
