package br.com.library.util;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.library.domain2.LivroUsers;

public class TesteObterEmprestados {
	
	@SuppressWarnings({ "unused", "unchecked" })
	public static void main(String[] args) {
		
		List <LivroUsers> livroUsers = new ArrayList<>();
		
		EntityManager em = new JPAUtil().getEntityManager();
			
		em.getTransaction().begin();
		
		String jpql = "select c from LivroUsers c";
		Query query = em.createQuery(jpql);
		
		livroUsers = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
	}
	

}
