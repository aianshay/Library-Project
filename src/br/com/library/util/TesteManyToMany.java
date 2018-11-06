package br.com.library.util;

import javax.persistence.EntityManager;
import br.com.library.domain2.Livro;
import br.com.library.domain2.LivroUsers;
import br.com.library.domain2.Users;

public class TesteManyToMany {
	
	public static void main(String[] args) {
		
		Livro livro = new Livro();
		
		livro.setId(4);
		
		Users user = new Users();
		
		user.setId(4);
	
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		livro = em.find(Livro.class, livro.getId());
		livro.setQuantidade(livro.getQuantidade() - 1);
		
		LivroUsers relacionamento = new LivroUsers();
		relacionamento = livro.emprestar(user);
		
		em.persist(livro);
		em.persist(relacionamento);
		
		em.getTransaction().commit();
		em.close();	
		
		
		
	}

}
