package br.com.sisinfra.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped // vai dizer ao CDI que a instância que o CDI gerar dessa classe deverá sobreviver por todo o ciclo de vida da aplicação
public class EntityManagerProducer {

	private EntityManagerFactory factory;
	
	public EntityManagerProducer() {
		this.factory = Persistence.createEntityManagerFactory("SISINFRA");
	}
	
	// fala p/ o CDI que esse método é um produtor de Entities Managers. 
	@Produces
	@RequestScoped // O CDI vai gerenciar esse método dentro do escopo de requisição, ou seja, a cada requisição.
	public EntityManager createEntityManager() {
		return this.factory.createEntityManager();
	}
	
	// sempre que o EntityManager for encerrado, o CDI chamará esse método, p/ fechar o EntityManager
	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}
}