package br.com.sisinfra.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisinfra.dao.OperacaoDao;
import br.com.sisinfra.model.Operacao;
import br.com.sisinfra.service.NegocioException;
import br.com.sisinfra.util.FacesMessages;

@Named
@ViewScoped
public class PesquisaOperacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OperacaoDao operacaoDAO;
	
	private List<Operacao> operacoes;
	
	
	private Operacao operacaoSelecionado;
	
	@Inject
	private FacesMessages facesMessages;
	
	
	
	public void excluir() {
		try {
			operacaoDAO.excluir(getOperacaoSelecionado());
			this.getOperacoes().remove(getOperacaoSelecionado());
			facesMessages.info("Operação" + getOperacaoSelecionado().getNome() + " excluída com sucesso.");
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		}
	}


	public void inicializar() {
		setOperacoes(operacaoDAO.buscarTodos());
	}




	public List<Operacao> getOperacoes() {
		return operacoes;
	}


	public void setOperacoes(List<Operacao> operacoes) {
		this.operacoes = operacoes;
	}


	public Operacao getOperacaoSelecionado() {
		return operacaoSelecionado;
	}


	public void setOperacaoSelecionado(Operacao operacaoSelecionado) {
		this.operacaoSelecionado = operacaoSelecionado;
	}

	

}
