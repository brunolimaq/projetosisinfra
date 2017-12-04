package br.com.sisinfra.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisinfra.dao.OperacaoDao;
import br.com.sisinfra.dao.filter.ServicoFilter;
import br.com.sisinfra.model.Operacao;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.service.NegocioException;
import br.com.sisinfra.util.FacesMessages;

@Named
@ViewScoped
public class PesquisaOperacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private OperacaoDao operacaoDAO;
	
	private List<Operacao> operacoes;
	
	private Operacao operacao;
	
	private Operacao operacaoSelecionado;
	
	@Inject
	private FacesMessages facesMessages;
	
	private ServicoFilter filtro;
	
	private List<Operacao> operacoesFiltrados;

	
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

	public PesquisaOperacaoBean() {
		setFiltro(new ServicoFilter());
	}

	public void pesquisar() {
		
		setOperacoesFiltrados(operacaoDAO.filtrados(filtro));
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




	public ServicoFilter getFiltro() {
		return filtro;
	}


	public void setFiltro(ServicoFilter filtro) {
		this.filtro = filtro;
	}


	public List<Operacao> getOperacoesFiltrados() {
		return operacoesFiltrados;
	}


	public void setOperacoesFiltrados(List<Operacao> operacoesFiltrados) {
		this.operacoesFiltrados = operacoesFiltrados;
	}


	public Operacao getOperacao() {
		return operacao;
	}


	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}



	

}
