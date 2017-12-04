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
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.sisinfra.dao.filter.ServicoFilter;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.model.Servidor;
import br.com.sisinfra.service.NegocioException;
import br.com.sisinfra.util.Transacional;



public class ServidorDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private EntityManager manager; // essa propriedade vai ser utilizada para realizar todas as operações com o banco de dados

	public ServidorDao() {

	}

	public ServidorDao(EntityManager manager) {
		this.manager = manager;
	}

	public Servidor porId(Long id) {
		return manager.find(Servidor.class, id); // busca por identificador
	}
	

	public Servidor guardar(Servidor servidor) {
		return manager.merge(servidor); // atualiza o banco de dados, se estiver passando um usuario que não existe ele insere e se já existe ele atualiza
	}

	@Transacional
	public void excluir(Servidor servidor) throws NegocioException {
		try {
			Servidor servidorTemp = this.buscarPeloCodigo(servidor.getId());
		
			manager.remove(servidorTemp);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Servidor não pode ser excluído.");
		}
		
	}
	
	public Servidor buscarPeloCodigo(Long codigo) {
		return manager.find(Servidor.class, codigo);
	}
	
	public List<Servidor> buscarTodos() {
		return manager.createQuery("from Servidor", Servidor.class).getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Servidor> filtrados(ServicoFilter filtro) {
		Session session = manager.unwrap(Session.class);
//		Session session = (Session) manager.getDelegate();
		
		Criteria criteria = session.createCriteria(Servidor.class)	;
		

		if (StringUtils.isNotBlank(filtro.getPesquisa())) {
			
			Criterion nome = Restrictions.ilike("nome", filtro.getPesquisa(), MatchMode.ANYWHERE);
			Criterion ipServidor = Restrictions.ilike("ipServidor", filtro.getPesquisa(), MatchMode.ANYWHERE);
			Criterion memoriaRam = Restrictions.ilike("memoriaRam", filtro.getPesquisa(), MatchMode.ANYWHERE);
			Criterion observacao = Restrictions.ilike("observacao", filtro.getPesquisa(), MatchMode.ANYWHERE);
			Criterion tipo = Restrictions.ilike("tipo", filtro.getPesquisa(), MatchMode.ANYWHERE);


			Disjunction orExp = Restrictions.or(nome, ipServidor,memoriaRam ,observacao,tipo);
			criteria.add( orExp );


		}
		
		System.out.println(criteria.list());
		return criteria.list();
	}

	

}