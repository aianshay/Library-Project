package br.com.library.util;


import javax.persistence.EntityManager;
import br.com.library.domain2.Livro;
import br.com.library.domain2.Users;

public class Popula {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		Users admin = new Users();
		admin.setLogin("admin");
		admin.setPassword("admin");
		
		Livro livro1 = new Livro();
		livro1.setAutor("Charles Duhiggss");
		livro1.setTitulo("O Poder do H�bitoss");
		livro1.setCapa("https://images.livrariasaraiva.com.br/imagemnet/imagem.aspx/?pro_id=4238667&qld=90&l=430&a=-1ss");
		livro1.setQuantidade(3);
//		
//		HistoricoEmprestimos historico = new HistoricoEmprestimos();
//		historico.setAutor("teste");
//		historico.setCapa("teste1");
//		historico.setTitulo("teste2");

		em.getTransaction().begin();

//		livro = em.find(LivroNew.class, 1);
//		livro.addUser(user1);
		
		em.persist(admin);
//		em.persist(user2);
		em.persist(livro1);
		
		em.getTransaction().commit();
		em.close();
		
	}

	

}
