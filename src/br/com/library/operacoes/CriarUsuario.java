package br.com.library.operacoes;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import br.com.library.domain2.Users;
import br.com.library.util.JPAUtil;


@ManagedBean(name="criar")
@ViewScoped

public class CriarUsuario {

	private Users novoUsuario = new Users();
	
	private String confirmacaoSenha;
	
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public Users getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(Users novoUsuario) {
		this.novoUsuario = novoUsuario;
	}
	
	public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas não coincidem.",null));
    }

	public void success() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário cadastrado com sucesso.",null));
    }
	
	public void add() {
		
		if(novoUsuario.getPassword().equals(confirmacaoSenha)) {
		
			EntityManager em = new JPAUtil().getEntityManager();
			
			em.getTransaction().begin();
		
			em.persist(novoUsuario);
			
			em.getTransaction().commit();
			em.close();
			
			novoUsuario = new Users();
			
			success();
			
		} else
			error();
		
	}
	
	
}
