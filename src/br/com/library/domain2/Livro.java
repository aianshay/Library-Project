package br.com.library.domain2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@SuppressWarnings("unused")
@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String autor;
	private String capa;
	private int quantidade;
	
	//@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	//private List<Users> user;

	@OneToMany(mappedBy = "livro")
	private List<LivroUsers> users;
	
//	public void addUser(Users usuario) {
//		user.add(usuario);
//	}
//	
//	public void removeUser(Users usuario) {
//		user.remove(usuario);
//	}
//
//	public List<Users> getUser() {
//		return user;
//	}
//
//	public void setUser(List<Users> user) {
//		this.user = user;
//	}
	
	public List<LivroUsers> getUsers() {
		return users;
	}

	public void setUsers(List<LivroUsers> users) {
		this.users = users;
	}

	public LivroUsers emprestar(Users user) {
		
		LivroUsers relacionamento = new LivroUsers();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 15);
		
		relacionamento.setUser(user);
		relacionamento.setLivro(this);
		relacionamento.setUserId(user.getId());
		relacionamento.setLivroId(this.getId());
		relacionamento.setDataEmprestimo(Calendar.getInstance());
		relacionamento.setDataDevolucao(cal);
		
		this.users.add(relacionamento);
		 
		return relacionamento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public String toString() {                                      //toString é um formatador de objeto
		String out = "livro: " + titulo + " \nautor: " + autor; 		//ensina o Java a imprimir o que ta num endereço de memoria
		return out;
	}
	

}
