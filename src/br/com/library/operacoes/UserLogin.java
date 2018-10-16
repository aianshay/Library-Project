package br.com.library.operacoes;
 
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.library.domain2.Users;
import br.com.library.util.JPAUtil;
 
@ManagedBean(name="userLogin")

public class UserLogin {
     
    private String username;
     
    private String password;
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro! Usuário ou senha não existem.",""));
    }
    
    
   @SuppressWarnings({ "unchecked" })
    public String login() {
    	
    	EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		String jpql = "select u from Users u where u.login = :pLogin and u.password = :pPassword";
		Query query = em.createQuery(jpql);
		query.setParameter("pLogin", username);
		query.setParameter("pPassword", password);
		
		List<Users> result = query.getResultList();
		
		String loginResult = null;
		String passwordResult = null;
		
		Users userResult = new Users();
		
		for (Users users : result) {
			loginResult = users.getLogin();
			passwordResult = users.getPassword();
			userResult = users;
		}
		
		em.getTransaction().commit();
		em.close();
		
		GerenciamentoLivroNew.userSelecionado = userResult;
		
		if(username != null && username.equals("admin") && username.equals(loginResult) && password != null && password.equals(passwordResult))
			return "admin.jsf?faces-redirect=true";
		else if(username != null && username.equals(loginResult) && password != null && password.equals(passwordResult)) { 
			return "user.jsf?faces-redirect=true";  
		} else 
        	error();
		return null;
		
	}   
}
