package br.com.library.util;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.library.domain2.Livro;
import br.com.library.domain2.LivroUsers;
import br.com.library.domain2.Users;

@SuppressWarnings("unused")
public class TesteRemove {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		Users user = new Users();
		user.setId(4);
		
		Livro livro = new Livro();
		livro.setId(4);
		
		em.getTransaction().begin();
		
		livro = em.find(Livro.class, livro.getId());
		livro.setQuantidade(livro.getQuantidade() + 1);
		
		String jpql = "delete from LivroUsers c where c.livroId = :plivroId and c.userId = :puserId";
		Query query = em.createQuery(jpql);
		query.setParameter("plivroId", livro.getId());
		query.setParameter("puserId", user.getId());
		
		query.executeUpdate();
		
		em.getTransaction().commit();
		em.close();
		
		
	}
	
}
