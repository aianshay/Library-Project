package br.com.library.domain2;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.Query;

import br.com.library.util.JPAUtil;

@ManagedBean 

public class LivrosEmprestados {

	public void visualizar(){
	
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
	
		String jpql = "select l from Livro l where user <> NULL";
		Query query = em.createQuery(jpql);
	
		@SuppressWarnings("unchecked")
		List<Livro> livrosEmprestados = query.getResultList();
		
		for (Livro livro : livrosEmprestados) {
			System.out.println("nome: " + livro.getTitulo());
			System.out.println("autor: ");
		}

		em.getTransaction().commit();
		em.close();
	
	}
	
	
	@OneToMany
	List <Livro> livros;

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	
	
}
