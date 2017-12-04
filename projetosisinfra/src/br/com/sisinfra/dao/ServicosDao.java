package br.com.sisinfra.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.sisinfra.dao.filter.ServicoFilter;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.service.NegocioException;
import br.com.sisinfra.util.Transacional;

public class ServicosDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private EntityManager manager; // essa propriedade vai ser utilizada para realizar todas as operações com o banco de dados

	public ServicosDao() {

	}

	public ServicosDao(EntityManager manager) {
		this.manager = manager;
	}

	public Servico porId(Long id) {
		return manager.find(Servico.class, id); // busca por identificador
	}
	

	public Servico guardar(Servico servico) {
		return manager.merge(servico); 
	}


	@Transacional
	public void excluir(Long codigo) throws NegocioException {
		try {
			
			System.out.println("código para excluir" + codigo);
			Servico servicoTemp = this.buscarPeloCodigo(codigo);
		
			manager.remove(servicoTemp);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Serviço não pode ser excluído.");
		}
		
	}
	
	public Servico buscarPeloCodigo(Long codigo) {
		return manager.find(Servico.class, codigo);
	}
	
	public List<Servico> buscarTodos() {
		return manager.createQuery("from Servico", Servico.class).getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Servico> filtrados(ServicoFilter filtro) {
		Session session = manager.unwrap(Session.class);
//		Session session = (Session) manager.getDelegate();
		
		Criteria criteria = session.createCriteria(Servico.class)	;
		

		if (StringUtils.isNotBlank(filtro.getPesquisa())) {
			
			Criterion nome = Restrictions.ilike("nome", filtro.getPesquisa(), MatchMode.ANYWHERE);
			Criterion descricao = Restrictions.ilike("descricao", filtro.getPesquisa(), MatchMode.ANYWHERE);


			LogicalExpression orExp = Restrictions.or(nome, descricao);
			criteria.add( orExp );


		}
		
		System.out.println(criteria.list());
		return criteria.list();
	}
	

}