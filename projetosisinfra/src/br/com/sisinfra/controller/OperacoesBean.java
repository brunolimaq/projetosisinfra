package br.com.sisinfra.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisinfra.dao.ServicosDao;
import br.com.sisinfra.model.Operacao;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.service.CadastroOperacao;
import br.com.sisinfra.service.NegocioException;
import br.com.sisinfra.util.FacesMessages;


@Named 
@ViewScoped
public class OperacoesBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Operacao operacao;
	
	@Inject
	private FacesMessages messages;
	
	
	@Inject
	private CadastroOperacao cadastroOperacao;
	

	@PostConstruct
	public void inicializar() {
		this.limpar();
	}
	
	public void limpar() {
		this.operacao = new Operacao();
	}
	public void salvar() throws NegocioException {
		cadastroOperacao.salvar(operacao);
	
		messages.info("Operação cadastrado com sucesso!");
	}


	public Operacao getOperacao() {
		return operacao;
	}



	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}


}

