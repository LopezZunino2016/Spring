package es.altair.inventario.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import es.altair.inventario.bean.Articulo;
import es.altair.inventario.bean.Usuario;

public class ArticuloDAOImpl implements ArticuloDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	@Override
	public boolean comprobarNombre(String nombre) {
		boolean correcto = false; 
		
		Session sesion=sessionFactory.getCurrentSession();
		
		if (((Articulo)sesion.createQuery("From Articulo Where nombre=:n")
					.setParameter("n", nombre)
					.uniqueResult()) != null)
			correcto = false;
		
		return correcto;
	}

	@Transactional
	@Override
	public int anadirArticulo(Articulo art) {
		int filas = 0; 
		System.out.println(art.getNombre());
		Session sesion=sessionFactory.getCurrentSession();
		
		filas = sesion.createSQLQuery("INSERT INTO articulo (nombre, descripcion, codigo, cantidad) values (:n, :d, :c, :ca)")
				.setParameter("n", art.getNombre())
				.setParameter("d", art.getDescripcion())
				.setParameter("c", art.getCodigo())
				.setParameter("ca", art.getCantidad())
				.executeUpdate();
		
		return filas;
	}

	@Transactional
	@Override
	public List<Articulo> listar() {
		Session sesion=sessionFactory.getCurrentSession();
		List<Articulo> lista = sesion.createQuery("FROM Articulo a ").list(); 
		System.out.println(lista.size());
		return lista;
	}
	@Transactional
	@Override
	public Articulo obtenerArticuloPorId(int id) {
		Session sesion=sessionFactory.getCurrentSession();
		
		return (Articulo)sesion.get(Articulo.class, id);
	}
	@Transactional
	@Override
	public void borrar(int idArticulo) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.createQuery("DELETE FROM Articulo WHERE id=:i")
							.setParameter("i", idArticulo)
							.executeUpdate();
		
	}

	@Transactional
	@Override
	public void ActualizarArticulo(Articulo art) {
		System.out.println("Entramos en el DAO");
		Session sesion=sessionFactory.getCurrentSession();
		System.out.println(art.getNombre());
		System.out.println(art.getIdArticulos());
/*		sesion.update(art);
*/		sesion.createSQLQuery("UPDATE articulo SET nombre=:n,   codigo=:co, cantidad=:ca , descripcion=:d WHERE idArticulos=:i ")
		.setParameter("n", art.getNombre())
		.setParameter("co", art.getCodigo())
		.setParameter("ca", art.getCantidad())
		.setParameter("d", art.getDescripcion())
		.setParameter("i", art.getIdArticulos())
		.executeUpdate();
	}
}
