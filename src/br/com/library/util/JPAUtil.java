package br.com.library.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
