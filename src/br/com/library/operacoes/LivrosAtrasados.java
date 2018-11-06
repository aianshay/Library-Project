package br.com.library.operacoes;

import br.com.library.domain2.LivroUsers;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.library.util.JPAUtil;

@ManagedBean(name="livrosAtrasados")
public class LivrosAtrasados {

	List<LivroUsers> livrosAtrasados = new ArrayList<>();
	
	public List<LivroUsers> getLivrosAtrasados() {
		return livrosAtrasados;
	}

	@SuppressWarnings("unchecked")
	public LivrosAtrasados() {
		super();
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		String jpql = "select c from LivroUsers c where c.dataDevolucao < :pData";
		Query query = em.createQuery(jpql);
		query.setParameter("pData", Calendar.getInstance());
		
		livrosAtrasados = query.getResultList();

		em.getTransaction().commit();
		em.close();
	}
	
}
