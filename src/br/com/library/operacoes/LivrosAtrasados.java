package br.com.library.operacoes;

import br.com.library.domain2.Livro;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.library.util.JPAUtil;

@ManagedBean(name="livrosAtrasados")
public class LivrosAtrasados {

	List<Livro> livrosAtrasados = new ArrayList<>();
	
	public List<Livro> getLivrosAtrasados() {
		return livrosAtrasados;
	}

	@SuppressWarnings("unchecked")
	public LivrosAtrasados() {
		super();
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		String jpql = "select l from Livro l where l.data < :pData";
		Query query = em.createQuery(jpql);
		query.setParameter("pData", Calendar.getInstance());

		livrosAtrasados = query.getResultList();

		em.getTransaction().commit();
		em.close();
	}
	
}
