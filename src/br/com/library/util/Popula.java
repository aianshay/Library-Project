package br.com.library.util;


import javax.persistence.EntityManager;
import br.com.library.domain2.LivroNew;
import br.com.library.domain2.Users;

public class Popula {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
//		Users admin = new Users();
//		admin.setLogin("admin");
//		admin.setPassword("admin");
//		
//		Livro livro1 = new Livro();
//		livro1.setAutor("Charles Duhiggss");
//		livro1.setTitulo("O Poder do Hábitoss");
//		livro1.setCapa("https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=4238667&qld=90&l=430&a=-1ss");
//		livro1.setQuantidade(3);
//		
//		HistoricoEmprestimos historico = new HistoricoEmprestimos();
//		historico.setAutor("teste");
//		historico.setCapa("teste1");
//		historico.setTitulo("teste2");
		
		Users user1 = new Users();
		user1.setId(1);
//		user1.setLogin("flavio");
//		user1.setPassword("133");
		
		Users user2 = new Users();
		user2.setId(9);
//		user2.setLogin("aian");
//		user2.setPassword("771225");
		
		LivroNew livro = new LivroNew();

		em.getTransaction().begin();

		livro = em.find(LivroNew.class, 4);
		livro.addUser(user2);
		
//		em.persist(user1);
//		em.persist(user2);
		em.persist(livro);
		
		em.getTransaction().commit();
		em.close();
		
	}

	

}
