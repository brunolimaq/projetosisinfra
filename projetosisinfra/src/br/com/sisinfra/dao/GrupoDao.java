package br.com.sisinfra.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.sisinfra.model.Grupo;
import br.com.sisinfra.model.Operacao;

public class GrupoDao {

	@Inject
	private EntityManager manager;
	
	public Grupo porId(Long id) {
		return manager.find(Grupo.class, id);
	}
	
	public List<Grupo> raizes() {
		return manager.createQuery("from Grupo", Grupo.class).getResultList();
	}
	public List<Grupo> buscarTodos() {
		return manager.createQuery("from Grupo", Grupo.class).getResultList();
	}
}
