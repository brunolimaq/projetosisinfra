package br.com.sisinfra.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.sisinfra.model.Usuario;
import br.com.sisinfra.service.NegocioException;
import br.com.sisinfra.util.Transacional;

public class UsuariosDao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private EntityManager manager; // essa propriedade vai ser utilizada para realizar todas as operações com o banco de dados

	public UsuariosDao() {

	}

	public UsuariosDao(EntityManager manager) {
		this.manager = manager;
	}

	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id); // busca por identificador
	}
	

	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario); // atualiza o banco de dados, se estiver passando um usuario que não existe ele insere e se já existe ele atualiza
	}

	
	@Transacional
	public void excluir(Usuario usuario) throws NegocioException {
		try {
			Usuario usuarioTemp = this.buscarPeloCodigo(usuario.getId());
		
			manager.remove(usuarioTemp);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Usuario não pode ser excluído.");
		}
		
	}
	
	public Usuario buscarPeloCodigo(Long usuario) {
		return manager.find(Usuario.class, usuario);
	}
	
	public List<Usuario> buscarTodos() {
		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}		
	

}