package br.com.library.operacoes;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.library.domain2.LivroUsers;
import br.com.library.util.JPAUtil;

@ManagedBean(name="livrosEmprestados")

public class LivrosEmprestados {

	private List <LivroUsers> livrosEmprestados = new ArrayList<>();

	public List<LivroUsers> getLivrosEmprestados() {
		return livrosEmprestados;
	}

	public void setLivrosEmprestados(List<LivroUsers> livrosEmprestados) {
		this.livrosEmprestados = livrosEmprestados;
	}

	@SuppressWarnings("unchecked")
	public LivrosEmprestados() {
		super();
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
	
		String jpql = "select c from LivroUsers c";
		Query query = em.createQuery(jpql);
		
		livrosEmprestados = query.getResultList();

		em.getTransaction().commit();
		em.close();
		
	}
}
