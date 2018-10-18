package br.com.library.util;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.library.domain2.Livro;

public class TesteMeusLivros {

	@SuppressWarnings({"unchecked"})
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		String jpql = "select l from Livro l where l.user.id = :pUserID";
		Query query = em.createQuery(jpql);
		query.setParameter("pUserID", 1);
		
		List<Livro> MeusLivros = query.getResultList();
		
		for (Livro livro : MeusLivros) {
			System.out.println("Nome:" + livro.getTitulo());

		}
	
		em.getTransaction().commit();
		em.close();
	
	}
	
}
