package br.com.library.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.library.domain2.Livro;
import br.com.library.domain2.Users;

public class TesteConsultaTabelaRelacionamento {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		Users user = new Users();
		user.setId(10);
		user.setLogin("aian	");
		user.setPassword("771225");
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
//		String jpql2 = "select l from Livro l join fetch l.user u where u.id = :pUserSelecionadoID";
		String jpql2 = "select l from Livro l join fetch l.user u where u <> NULL";
		Query query2 = em.createQuery(jpql2);
		//query2.setParameter("pUserSelecionadoID", user.getId());
		
		List <Livro> livros = query2.getResultList();
		
		for (Livro livro : livros) {
			System.out.println("id: " + livro.getId());
			System.out.println("autor: " + livro.getAutor());
			//System.out.println(livro.getUser());
		}
		
		em.getTransaction().commit();
		em.close();
		
	}

}
