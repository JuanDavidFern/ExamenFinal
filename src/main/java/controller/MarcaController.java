package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Marca;



public class MarcaController {
	
private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("protatiles");
	
	public static List<Marca> findAll(){
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("select * from marca", Marca.class);
		List<Marca> lista = (List<Marca>)q.getResultList();
		em.close();
		return lista;
	}
	
	public static void update(Marca m) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(m);
		System.out.println("He realizado la modificacion");
		em.getTransaction().commit();
		em.close();
	}

}
