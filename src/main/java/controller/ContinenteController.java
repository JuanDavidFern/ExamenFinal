package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Continente;


public class ContinenteController {
	
private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("protatiles");
	
	public static List<Continente> findAll(){
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("select * from continente", Continente.class);
		List<Continente> lista = (List<Continente>)q.getResultList();
		em.close();
		return lista;
	}

}
