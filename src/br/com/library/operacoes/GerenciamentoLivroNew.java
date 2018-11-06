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
import br.com.library.domain2.HistoricoEmprestimos;
import br.com.library.domain2.Livro;
import br.com.library.domain2.LivroUsers;
import br.com.library.domain2.Users;
import br.com.library.util.JPAUtil;

@ManagedBean(name="gerenciamentoLivroNew")
@ViewScoped
public class GerenciamentoLivroNew {
	
	private Livro livro = new Livro();

	private List<Livro> livros = new ArrayList<>();
	private List<Livro> meusLivros = new ArrayList<>();
	private List<LivroUsers> livroUsers = new ArrayList<>();

	private Livro livroSelecionado = new Livro();
	public static Users userSelecionado = new Users();	
	
	public List<LivroUsers> getLivroUsers() {
		return livroUsers;
	}

	public void setLivroUsers(List<LivroUsers> livroUsers) {
		this.livroUsers = livroUsers;
	}

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
		
		String jpql2 = "select c from LivroUsers c where c.userId = :puserId";
		Query query2 = em.createQuery(jpql2);
		query2.setParameter("puserId", userSelecionado.getId());
		
		livros = query.getResultList();	
		livroUsers = query2.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	public String logout() {
		userSelecionado = null;
		return "index.jsf?faces-redirect=true";
	}
	
	public String add() {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
	
		em.persist(livro);
		
		em.getTransaction().commit();
		em.close();
		
		successBook();
		
		livro = new Livro();
		
		return "admin.jsf?faces-redirect=true";
	}
	
	public void emprestar() {
		
		if(livroSelecionado.getQuantidade() > 0) {
			
			LivroUsers relacionamento = new LivroUsers();
			
			EntityManager em = new JPAUtil().getEntityManager();
			
			em.getTransaction().begin();
			
			userSelecionado = em.find(Users.class, userSelecionado.getId());
			livroSelecionado = em.find(Livro.class, livroSelecionado.getId());
			relacionamento = livroSelecionado.emprestar(userSelecionado);
			
			livroSelecionado.setQuantidade(livroSelecionado.getQuantidade() - 1);
			
			SalvarNoHistorico(livroSelecionado, userSelecionado);
			
			em.persist(relacionamento);
			em.persist(livroSelecionado);
			
			em.getTransaction().commit();
			em.close();
			
			success();
			
		}else
			error();
		
		
	}
	
	public void SalvarNoHistorico(Livro livro, Users user) {
		
		HistoricoEmprestimos salvar = new HistoricoEmprestimos();
		
		salvar.setTitulo(livro.getTitulo());
		salvar.setAutor(livro.getAutor());
		salvar.setCapa(livro.getCapa());
		salvar.setDataEmprestimo(Calendar.getInstance());
		salvar.setUser(user);
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(salvar);
		
		em.getTransaction().commit();
		em.close();
	}
		
	public String Devolver() {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		livroSelecionado = em.find(Livro.class, livroSelecionado.getId());
		livroSelecionado.setQuantidade(livroSelecionado.getQuantidade() + 1);
		
		String jpql = "delete from LivroUsers c where c.livroId = :plivroId and c.userId = :puserId";
		Query query = em.createQuery(jpql);
		query.setParameter("plivroId", livroSelecionado.getId());
		query.setParameter("puserId", userSelecionado.getId());
		
		query.executeUpdate();
		
		em.getTransaction().commit();
		em.close();

		return "mybooks.jsf?faces-redirect=true";
	}
	
	public String Remover() {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		String jpql = "delete from Livro l where l.id = :pId";
		Query query = em.createQuery(jpql);
		query.setParameter("pId",livroSelecionado.getId());
		
		query.executeUpdate();
		
		em.getTransaction().commit();
		em.close();
		
		removeSuccess();
		
		return "admin.jsf?faces-redirect=true";
	}
	
	public void success() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Livro emprestado com sucesso.",null));
    }
	
	public void removeSuccess() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Livro removido com sucesso.",null));
    }
	
	public void successBook() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Livro adicionado com sucesso.",null));
    }
	
	public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Livro indisponível.",null));
    }
	
}
