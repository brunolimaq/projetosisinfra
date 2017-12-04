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
import org.hibernate.criterion.Restrictions;

import br.com.sisinfra.dao.filter.ServicoFilter;
import br.com.sisinfra.model.Operacao;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.service.NegocioException;
import br.com.sisinfra.util.Transacional;



public class OperacaoDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private EntityManager manager; // essa propriedade vai ser utilizada para realizar todas as operações com o banco de dados

	public OperacaoDao() {

	}

	public OperacaoDao(EntityManager manager) {
		this.manager = manager;
	}

	public Operacao porId(Long id) {
		return manager.find(Operacao.class, id); // busca por identificador
	}
	

	public Operacao guardar(Operacao operacao) {
		return manager.merge(operacao); // atualiza o banco de dados, se estiver passando um usuario que não existe ele insere e se já existe ele atualiza
	}

	@Transacional
	public void excluir(Operacao operacao) throws NegocioException {
		try {
			System.out.println("Operacao" + operacao.getId());
			Operacao operacaoTemp = this.buscarPeloCodigo(operacao.getId());
			
			manager.remove(operacaoTemp);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Operação não pode ser excluído, existem serviços associados a está operação.");
		}
		
	}
	
	public Operacao buscarPeloCodigo(Long codigo) {
		return manager.find(Operacao.class, codigo);
	}
	
	public List<Operacao> buscarTodos() {
		return manager.createQuery("from Operacao", Operacao.class).getResultList();
	}

	public List<Operacao> buscarOperacoes() {
		return manager.createQuery("from servico_operacao", Operacao.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Operacao> filtrados(ServicoFilter filtro) {
		Session session = manager.unwrap(Session.class);
//		Session session = (Session) manager.getDelegate();
		
		Criteria criteria = session.createCriteria(Operacao.class)	;
	

			if (StringUtils.isNotBlank(filtro.getPesquisa())) {
				System.out.println("pesquisa operacao: " + filtro.getPesquisa());
				criteria.add(Restrictions.ilike("nome", filtro.getPesquisa(), MatchMode.ANYWHERE));
			}

	
		
		System.out.println("resultado operacao: " + criteria.list());
		return criteria.list();
	}


}