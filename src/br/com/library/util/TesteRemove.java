package br.com.library.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.library.domain2.Livro;
import br.com.library.domain2.Users;

public class TesteRemove {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		Users user = new Users();
		user.setId(2);
		
		Livro livro = new Livro();
		livro.setId(1);
		
		Livro livro1 = new Livro();
		
		em.getTransaction().begin();
		
		//String jpql = "delete from Users u where u.id = :pId";
	
		livro1 = em.find(Livro.class, livro.getId());
		//user1 = em.find(Users.class, user.getId());
		
		for (Users usuario : livro1.getUser()) {
			if(usuario.getId() == user.getId()) {
				livro1.getUser().remove(usuario);
				break;
			}
		}
		
		em.persist(livro1);
		
		em.getTransaction().commit();
		em.close();
		
		
	}
	
}
