package br.com.library.operacoes;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.library.domain2.Livro;
import br.com.library.util.JPAUtil;

@ManagedBean(name="livrosEmprestados")

public class LivrosEmprestados {

	private List <Livro> livrosEmprestados = new ArrayList<>();
	
	public List<Livro> getLivrosEmprestados() {
		return livrosEmprestados;
	}

	public void setLivrosEmprestados(List<Livro> livrosEmprestados) {
		this.livrosEmprestados = livrosEmprestados;
	}
	
	@SuppressWarnings("unchecked")
	public LivrosEmprestados() {
		super();
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
	
		String jpql = "select l from Livro l where user <> NULL";
		Query query = em.createQuery(jpql);
	
		livrosEmprestados = query.getResultList();

		em.getTransaction().commit();
		em.close();
		
	}
}
