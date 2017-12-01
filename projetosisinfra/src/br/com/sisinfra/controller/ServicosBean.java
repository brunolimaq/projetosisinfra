package br.com.sisinfra.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisinfra.controller.conversor.BaseEntity;
import br.com.sisinfra.dao.OperacaoDao;
import br.com.sisinfra.dao.ServicosDao;
import br.com.sisinfra.model.Operacao;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.service.CadastroServico;
import br.com.sisinfra.util.FacesMessages;


@Named 
@ViewScoped
public class ServicosBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Servico servico;
	
	@Inject
	private FacesMessages messages;
	
	
	@Inject
	private CadastroServico cadastroServico;
	
	private Operacao operacao;
	
	@Inject
	private OperacaoDao operacaoDao;


	private List<Operacao> operacoes;


	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.operacoes = operacaoDao.buscarTodos();
	}
	
	public void limpar() {
		this.servico = new Servico();
	}
	
	public void salvar(){
		cadastroServico.salvar(servico);
	
		messages.info("Serviço cadastrado com sucesso!");
	}



	public Servico getServico() {
		return servico;
	}




	public void setServico(Servico servico) {
		this.servico = servico;
	}



	public Operacao getOperacao() {
		return operacao;
	}



	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}

	public List<Operacao> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(List<Operacao> operacoes) {
		this.operacoes = operacoes;
	}


	

}

