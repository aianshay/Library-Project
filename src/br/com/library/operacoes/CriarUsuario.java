package br.com.library.operacoes;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import br.com.library.domain2.Users;
import br.com.library.util.JPAUtil;


@ManagedBean(name="criar")
@ViewScoped

public class CriarUsuario {

	private Users novoUsuario = new Users();
	
	public Users getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(Users novoUsuario) {
		this.novoUsuario = novoUsuario;
	}

	public void add() {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
	
		em.persist(novoUsuario);
		
		em.getTransaction().commit();
		em.close();
		
		novoUsuario = new Users();
	}
	
	
}
