package br.com.sisinfra.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import br.com.sisinfra.model.Servico;
import br.com.sisinfra.model.Servidor;
import br.com.sisinfra.model.Usuario;
import br.com.sisinfra.service.NegocioException;
import br.com.sisinfra.util.Transacional;

public class UsuariosDao implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}
	
	public List<Usuario> vendedores() {
		// TODO filtrar apenas vendedores (por um grupo específico)
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

	@Transacional
	public void excluir(Usuario usuario) throws NegocioException {
		try {
			Usuario servidorTemp = this.buscarPeloCodigo(usuario.getId());
		
			manager.remove(servidorTemp);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Usuario não pode ser excluído.");
		}
		
	}
	
	
	public Usuario porEmail(String email) {
		Usuario usuario = null;
		
		try {
			usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
				.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}
		
		return usuario;
	}
	
	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario); 
	}

	
	public Usuario buscarPeloCodigo(Long codigo) {
		return manager.find(Usuario.class, codigo);
	}
	
	public List<Usuario> buscarTodos() {
		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}
	

}