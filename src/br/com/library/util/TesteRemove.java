package br.com.library.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TesteRemove {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		String login = "lucas";
//		String password = "123456";
		
		em.getTransaction().begin();
		
		String jpql = "delete from Users u where u.login = :pLogin";
		Query query = em.createQuery(jpql);
		query.setParameter("pLogin",login);
		
		query.executeUpdate();
		
		em.getTransaction().commit();
		em.close();
		
		
	}
	
}
