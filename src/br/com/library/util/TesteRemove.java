package br.com.library.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.library.domain2.Users;

public class TesteRemove {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		Users user = new Users();
		
		user.setId(8);
		
		em.getTransaction().begin();
		
		String jpql = "delete from Users u where u.id = :pId";
		Query query = em.createQuery(jpql);
		query.setParameter("pId",user.getId());
		
		query.executeUpdate();
		
		em.getTransaction().commit();
		em.close();
		
		
	}
	
}
