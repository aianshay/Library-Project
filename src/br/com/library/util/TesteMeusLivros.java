package br.com.library.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.library.domain2.Livro;

public class TesteMeusLivros {

	@SuppressWarnings({"unchecked"})
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		
		em.getTransaction().begin();
		
		String jpql = "select l from Livro l where l.user.id = :pUserID";
		Query query = em.createQuery(jpql);
		query.setParameter("pUserID", 1);
		
		List<Livro> MeusLivros = query.getResultList();
		
		for (Livro livro : MeusLivros) {
			String dataFormatada = df.format(livro.getData());
			System.out.println("Nome:" + livro.getTitulo());
			System.out.println("Data de devolucao: " + dataFormatada);
		}
	
		em.getTransaction().commit();
		em.close();
	
	}
	
}
