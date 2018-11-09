package br.com.library.domain2;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String login;
	private String password;
	@Column(name = "enable", columnDefinition = "BOOLEAN")
	private boolean enable;
	
    @ManyToMany
    @JoinTable(name = "USERS_AUTH", joinColumns = @JoinColumn(name = "USERS_Login"), inverseJoinColumns = @JoinColumn(name = "AUTH_authority"))
    private List<Authority> authorities;
	
	@OneToMany(mappedBy = "user")
	private List<LivroUsers> livros;
	
	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<LivroUsers> getLivros() {
		return livros;
	}

	public void setLivros(List<LivroUsers> livros) {
		this.livros = livros;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {                                      //toString é um formatador de objeto
		String out = " Usuário: " + login; 		//ensina o Java a imprimir o que ta num endereço de memoria
		return out;
	}

}
