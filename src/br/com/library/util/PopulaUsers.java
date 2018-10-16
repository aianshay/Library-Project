package br.com.library.util;

import javax.persistence.EntityManager;

import br.com.library.domain2.Livro;
import br.com.library.domain2.Users;

public class PopulaUsers {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		Users aian = new Users();
		aian.setLogin("aian");
		aian.setPassword("771225");
		
		Users admin = new Users();
		admin.setLogin("admin");
		admin.setPassword("admin");
		
		Livro livro1 = new Livro();
		livro1.setAutor("Charles Duhigg");
		livro1.setTitulo("O Poder do Hábito");
		livro1.setCapa("https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=4238667&qld=90&l=430&a=-1");
		
		em.getTransaction().begin();
		
		em.persist(aian);
		em.persist(admin);
		em.persist(livro1);
		
		em.getTransaction().commit();
		em.close();
		
	}

	

}
