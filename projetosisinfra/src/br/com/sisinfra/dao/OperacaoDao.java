package br.com.sisinfra.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sisinfra.model.Operacao;
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
	

}