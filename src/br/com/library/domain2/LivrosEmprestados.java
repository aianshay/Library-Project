package br.com.library.domain2;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
		
		for (Livro livro : livrosEmprestados) {
			System.out.println("nome: " + livro.getId());
		}

		em.getTransaction().commit();
		em.close();
	}
	
	public List<Livro> visualizarLivrosEmprestados(){
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
	
		String jpql = "select l from Livro l where user <> NULL";
		Query query = em.createQuery(jpql);
	
		@SuppressWarnings("unchecked")
		List<Livro> livrosEmprestados = query.getResultList();
		
		for (Livro livro : livrosEmprestados) {
			System.out.println("nome: " + livro.getId());
		}

		em.getTransaction().commit();
		em.close();
		
		return livrosEmprestados;
	}
	
	
}
