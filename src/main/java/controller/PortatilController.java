package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Portatil;

public class PortatilController {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("protatiles");

	public static void update(Portatil m) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(m);
		System.out.println("He realizado la modificacion");
		em.getTransaction().commit();
		em.close();
	}
	
	public static void remove(Portatil m) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(m));
		System.out.println("He realizado la modificacion");
		em.getTransaction().commit();
		em.close();
	}
	
	public static void insert(Portatil m) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(m);
		System.out.println("He realizado una insercion");
		em.getTransaction().commit();
		em.close();
	}

	public static Portatil findFirst() {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("select * from portatil  where id = (select min(id) from portatil)",
				Portatil.class);
		Portatil lista = (Portatil) q.getSingleResult();
		em.close();
		return lista;
	}

	public static Portatil findLast() {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("select * from portatil  where id = (select max(id) from portatil)",
				Portatil.class);
		Portatil lista = (Portatil) q.getSingleResult();
		em.close();
		return lista;
	}

	public static Portatil findNext(int i) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("SELECT * FROM portatil where id > ? limit 1;", Portatil.class);
		q.setParameter(1, i);
		Portatil lista = (Portatil) q.getSingleResult();
		em.close();
		return lista;
	}

	public static Portatil findPrevius(int i) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("SELECT * FROM portatil where id < ? order by id desc limit 1 ", Portatil.class);
		q.setParameter(1, i);
		Portatil lista = (Portatil) q.getSingleResult();
		em.close();
		return lista;
	}

	public static List<Portatil> findAll() {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("select * from portatil", Portatil.class);
		List<Portatil> lista = (List<Portatil>) q.getResultList();
		em.close();
		return lista;
	}

	public static int count() {
		int lista = 0;
		for (Portatil p : findAll()) {
			lista++;
		}
		return lista;
	}
}
