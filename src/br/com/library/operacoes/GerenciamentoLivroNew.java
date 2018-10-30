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
				
		String jpql2 = "select l from Livro l join l.user u where u.id = :pUserSelecionadoID";
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
	
	public void Emprestar() {
		
		if(livroSelecionado.getUser() != null || livroSelecionado.getQuantidade() == 0)
			error();
		else if (userSelecionado != null) {
		
			EntityManager em = new JPAUtil().getEntityManager();
			
			em.getTransaction().begin();
			
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 15);
			
			livroSelecionado.setDataEmprestimo(Calendar.getInstance());
			livroSelecionado.setDataDevolucao(cal);
		//	livroSelecionado.setUser(userSelecionado);
			livroSelecionado.setQuantidade(livroSelecionado.getQuantidade() - 1);
			
			//SalvarNoHistorico(livroSelecionado);
			
			em.merge(livroSelecionado);
		
			em.getTransaction().commit();
			em.close();	
			
			success();
		}
	}
	
	public void EmprestarNew() {
		
		if(livroSelecionado.getQuantidade() > 0) {
			
			Livro livroAux = new Livro();
			Users userAux = new Users();
			
			EntityManager em = new JPAUtil().getEntityManager();
			
			em.getTransaction().begin();
			
			userAux = em.find(Users.class, userSelecionado.getId());
			
			livroAux = em.find(Livro.class, livroSelecionado.getId());
			livroAux.addUser(userAux);
			livroAux.setQuantidade(livroSelecionado.getQuantidade() - 1);
			
			SalvarNoHistorico(livroAux, userAux);
		
			em.persist(livroAux);
			
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
		
		Livro livro1 = new Livro();
		
		em.getTransaction().begin();
		
		livro1 = em.find(Livro.class, livroSelecionado.getId());
		
		for (Users usuario : livro1.getUser()) {
			if(usuario.getId() == userSelecionado.getId()) {
				livro1.getUser().remove(usuario);
				livro1.setQuantidade(livroSelecionado.getQuantidade() + 1);
				break;
			}
		}
		
		em.persist(livro1);
		
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Livro j� emprestado.",null));
    }
	
}
