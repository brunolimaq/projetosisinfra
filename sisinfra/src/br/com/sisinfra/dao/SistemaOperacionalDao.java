package br.com.sisinfra.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sisinfra.model.Operacao;
import br.com.sisinfra.model.SistemaOperacional;
import br.com.sisinfra.service.NegocioException;
import br.com.sisinfra.util.Transacional;



public class SistemaOperacionalDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private EntityManager manager; // essa propriedade vai ser utilizada para realizar todas as operações com o banco de dados

	public SistemaOperacionalDao() {

	}

	public SistemaOperacionalDao(EntityManager manager) {
		this.manager = manager;
	}

	public SistemaOperacional porId(Long id) {
		return manager.find(SistemaOperacional.class, id); // busca por identificador
	}
	

	public SistemaOperacional guardar(SistemaOperacional sistemaOperacional) {
		return manager.merge(sistemaOperacional); // atualiza o banco de dados, se estiver passando um usuario que não existe ele insere e se já existe ele atualiza
	}

	@Transacional
	public void excluir(SistemaOperacional sistemaOperacional) throws NegocioException {
		try {
			SistemaOperacional sistemaOperacionalTemp = this.buscarPeloCodigo(sistemaOperacional.getId());
			
			manager.remove(sistemaOperacionalTemp);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("O Sistema Operacional não pode ser excluído, existem servidores associados a este S.O.");
		}
		
	}
	
	public SistemaOperacional buscarPeloCodigo(Long codigo) {
		return manager.find(SistemaOperacional.class, codigo);
	}
	
	public List<SistemaOperacional> buscarTodos() {
		return manager.createQuery("from SistemaOperacional", SistemaOperacional.class).getResultList();
	}

	

}