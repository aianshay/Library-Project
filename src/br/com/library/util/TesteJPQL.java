package br.com.library.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.library.domain2.Users;

public class TesteJPQL {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		String login = "aian";
		String password = "771225";
		
		String jpql = "select u from Users u where u.login = :pLogin and u.password = :pPassword";
		Query query = em.createQuery(jpql);
		query.setParameter("pLogin", login);
		query.setParameter("pPassword", password);
		
		List<Users> result = query.getResultList();
		
		for (Users users : result) {
			System.out.println("Login: " + users.getLogin());
			System.out.println("Senha: " + users.getPassword());
		}
				
		em.getTransaction().commit();
		em.close();
	}
	

}
