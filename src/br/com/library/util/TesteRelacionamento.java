package br.com.library.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import br.com.library.domain2.Users;
import br.com.library.domain2.Livro;

@SuppressWarnings("unchecked")
public class TesteRelacionamento {
	
	public static void main(String args[]) {
		
	EntityManager em = new JPAUtil().getEntityManager();

	int livroID = 13;
	
	String jpql = "select l from Livro l where l.id = :pLivroID";
	Query query = em.createQuery(jpql);
	query.setParameter("pLivroID", livroID);
	
	List<Livro> resultado = query.getResultList();
	
	Livro livroResult = new Livro();
	
	for (Livro livro : resultado) {
		livroResult = livro;
	}
	
	int userID = 2;
	
	String jpql2 = "select u from Users u where u.id = :pUserID";
	Query query2 = em.createQuery(jpql2);
	query2.setParameter("pUserID", userID);
	
	List<Users> result = query2.getResultList();
	
	
	em.getTransaction().begin();	
	
	em.merge(livroResult);
	
	em.getTransaction().commit();
	em.close();
	}
}
