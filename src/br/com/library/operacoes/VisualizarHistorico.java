package br.com.library.operacoes;

import java.util.List;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.library.domain2.HistoricoEmprestimos;
import br.com.library.util.JPAUtil;

@ManagedBean(name="visualizarHistorico")
public class VisualizarHistorico {
	
	private List<HistoricoEmprestimos> historico = new ArrayList<>();
	
	public List<HistoricoEmprestimos> getHistorico() {
		return historico;
	}

	public void setHistorico(List<HistoricoEmprestimos> historico) {
		this.historico = historico;
	}

	@SuppressWarnings("unchecked")
	public VisualizarHistorico() {
		super();		
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
	
		String jpql = "select h from HistoricoEmprestimos h";
		Query query = em.createQuery(jpql);
	
		historico = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
	}
	

}
