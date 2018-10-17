package br.com.library.domain2;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.OneToMany;

@ManagedBean 

public class LivrosEmprestados {

	@OneToMany
	List <Livro> livros;

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	
	
}
