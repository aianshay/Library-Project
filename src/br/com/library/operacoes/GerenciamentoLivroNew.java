package br.com.library.operacoes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

//import br.com.library.domain2.HistoricoEmprestimos;
import br.com.library.domain2.Livro;
import br.com.library.domain2.Users;
import br.com.library.util.JPAUtil;

@ManagedBean(name="gerenciamentoLivroNew")
@ViewScoped
public class GerenciamentoLivroNew {
	
	private Livro livro = new Livro();

	private List<Livro> livros = new ArrayList<>();
	private List<Livro> meusLivros = new ArrayList<>();

	private Livro livroSelecionado = new Livro();
	public static Users userSelecionado = new Users();	
	
	public List<Livro> getLivros() {
		return livros;
	}

	public List<Livro> getMeusLivros() {
		return meusLivros;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}

	public void setLivroSelecionado(Livro livroSelecionado) {
		this.livroSelecionado = livroSelecionado;
	}
	
	@SuppressWarnings("unchecked")
	public GerenciamentoLivroNew() {
		super();
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		String jpql = "select l from Livro l";
		Query query = em.createQuery(jpql);
		
		String jpql2 = "select l from Livro l where l.user.id = :pUserSelecionadoID";
		Query query2 = em.createQuery(jpql2);
		query2.setParameter("pUserSelecionadoID", userSelecionado.getId());
		
		livros = query.getResultList();
		meusLivros = query2.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	public String logout() {
		userSelecionado = null;
		return "index.jsf?faces-redirect=true";
	}
	
	public void add() {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
	
		em.persist(livro);
		
		em.getTransaction().commit();
		em.close();
		
		successBook();
		
		livro = new Livro();
	}
	
	public void Emprestar() {
		
		if(livroSelecionado.getUser() != null)
			error();
		else if (userSelecionado != null) {
		
			EntityManager em = new JPAUtil().getEntityManager();
			
			em.getTransaction().begin();
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 15);
			
			livroSelecionado.setDataEmprestimo(Calendar.getInstance());
			livroSelecionado.setDataDevolucao(cal);
			livroSelecionado.setUser(userSelecionado);
			
			//final HistoricoEmprestimos livroHistorico = new HistoricoEmprestimos(livroSelecionado);
			
			em.merge(livroSelecionado);
			//em.persist(livroHistorico);
		
			em.getTransaction().commit();
			em.close();	
			
			success();
		}
	}
	
	public String Devolver() {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
	
		livroSelecionado.setDataDevolucao(null);
		livroSelecionado.setDataEmprestimo(null);
		livroSelecionado.setUser(null);
		
		em.merge(livroSelecionado);
	
		em.getTransaction().commit();
		em.close();	

		return "mybooks.jsf?faces-redirect=true";
	}
	
	public void success() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Livro emprestado com sucesso.",null));
    }
	
	public void successBook() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Livro adicionado com sucesso.",null));
    }
	
	public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Livro já emprestado.",null));
    }
	
}
