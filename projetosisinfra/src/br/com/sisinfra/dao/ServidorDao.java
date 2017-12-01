package br.com.sisinfra.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

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

	

}