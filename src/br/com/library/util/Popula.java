package br.com.library.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.library.domain2.Authority;
import br.com.library.domain2.Livro;
import br.com.library.domain2.Users;

public class Popula {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		Users user = new Users();
		
		Authority autor = new Authority();
		autor.setName("ROLE_USER");
		
		em.getTransaction().begin();
		
		user = em.find(Users.class, 8);
		
		user.getAuthorities().add(autor);
		
		em.persist(user);
		
		em.getTransaction().commit();
		em.close();
		
	}

	

}
