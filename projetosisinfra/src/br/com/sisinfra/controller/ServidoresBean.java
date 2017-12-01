package br.com.sisinfra.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisinfra.dao.ServicosDao;
import br.com.sisinfra.dao.SistemaOperacionalDao;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.model.Servidor;
import br.com.sisinfra.model.SistemaOperacional;
import br.com.sisinfra.service.CadastroServidor;
import br.com.sisinfra.service.NegocioException;
import br.com.sisinfra.util.FacesMessages;


@Named 
@ViewScoped
public class ServidoresBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Servidor servidor;
	
	@Inject
	private FacesMessages messages;
	
	private Servico servico;
	
	private SistemaOperacional sistemaOperacional;
	
	private List<Servico> servicos;
	
	private List<SistemaOperacional> sistemaOperacionais;
	
	@Inject
	private ServicosDao servicoDao;
	
	@Inject
	private SistemaOperacionalDao sistemaOperacionalDao;
	
	@Inject
	private CadastroServidor cadastroServidor;
	

	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.servicos = servicoDao.buscarTodos();
		this.sistemaOperacionais = sistemaOperacionalDao.buscarTodos();
	}
	
	public void limpar() {
		this.servidor = new Servidor();
	}

	
	public void salvar() throws NegocioException{
		cadastroServidor.salvar(servidor);
		
		if(this.servidor.getId() != null) {
		messages.info("Servidor atualizado com sucesso!");
		} else {
		messages.info("Servidor cadastrado com sucesso!");	
		}
	}




	public Servidor getServidor() {
		return servidor;
	}




	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}


	public boolean isEditando() {
		return this.servidor.getId() != null;
	}




	public Servico getServico() {
		return servico;
	}




	public void setServico(Servico servico) {
		this.servico = servico;
	}




	public List<Servico> getServicos() {
		return servicos;
	}




	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<SistemaOperacional> getSistemaOperacionais() {
		return sistemaOperacionais;
	}

	public void setSistemaOperacionais(List<SistemaOperacional> sistemaOperacionais) {
		this.sistemaOperacionais = sistemaOperacionais;
	}

	public SistemaOperacional getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(SistemaOperacional sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}



	







	
	

}

