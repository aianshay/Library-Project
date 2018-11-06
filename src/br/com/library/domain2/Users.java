package br.com.library.domain2;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String login;
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<LivroUsers> livros;

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
