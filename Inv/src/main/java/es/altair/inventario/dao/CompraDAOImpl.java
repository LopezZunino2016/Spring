package es.altair.inventario.dao;

import java.util.List;

import javax.transaction.Transactional;


import es.altair.inventario.bean.Compra;
import es.altair.inventario.bean.Usuario;

import org.hibernate.SessionFactory;
import org.hibernate.Session;


public class CompraDAOImpl implements CompraDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	@Override
	public List<Compra> listar(Usuario u) {
		Session sesion = sessionFactory.getCurrentSession();
		
		List<Compra> lista = sesion.createQuery("FROM Compra c WHERE usuario=:usu")
				.setParameter("usu", u).list();
		return lista;
	}
	
	@Transactional
	@Override
	public int anadirCompra(Compra c) {
		int filas = 0;
		Session sesion=sessionFactory.getCurrentSession();
		
		filas = sesion.createSQLQuery("INSERT INTO compra (fecha, cantidad, idUsuario, idArticulos) values (:f, :c , :iU , :iA)")
									.setParameter("f", c.getFecha())
									.setParameter("c", c.getCantidad())
									.setParameter("iU", c.getUsuario().getIdUsuario())
									.setParameter("iA", c.getArticulo().getIdArticulos())
									.executeUpdate(); 
		
		return filas;
	}

	@Transactional
	@Override
	public void borrar(int id) {
		Session sesion=sessionFactory.getCurrentSession();
		sesion.createQuery("DELETE FROM Compra WHERE id=:i")
		.setParameter("i", id)
		.executeUpdate();
	}

}
