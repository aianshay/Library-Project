package br.com.library.domain2;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="LivroUserDatas")
@IdClass(LivroUsersId.class)

public class LivroUsers {
	
	@Id
	private int livroId;

	@Id
	private int userId;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Calendar dataEmprestimo;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Calendar dataDevolucao;
	
	@ManyToOne
	@JoinColumn(name = "livroId", updatable = false, insertable = false)
	private Livro livro;
	 
	@ManyToOne
	@JoinColumn(name = "userId", updatable = false, insertable = false)
	private Users user;

	public int getLivroId() {
		return livroId;
	}

	public void setLivroId(int livroId) {
		this.livroId = livroId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Calendar getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Calendar dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Calendar getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Calendar dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

}
