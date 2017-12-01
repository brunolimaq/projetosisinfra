package br.com.sisinfra.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisinfra.dao.OperacaoDao;
import br.com.sisinfra.dao.ServicosDao;
import br.com.sisinfra.dao.filter.ServicoFilter;
import br.com.sisinfra.model.Operacao;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.service.NegocioException;
import br.com.sisinfra.util.FacesMessages;

@Named
@ViewScoped
public class PesquisaServicoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	
	
	@Inject
	private ServicosDao servicoDAO;
	
	@Inject
	private OperacaoDao operacaoDAO;
	
	private List<Servico> servicos;
	
	private Servico servico;
	
	
	
	private ServicoFilter filtro;
	
	private List<Servico> servicosFiltrados;

	
	private Servico servicoSelecionado;

	private List<Operacao> operacoesAtivas;
	
	@Inject
	private FacesMessages facesMessages;
	


	private List<Operacao> operacoes;

	
	public PesquisaServicoBean() {
		filtro = new ServicoFilter();
	}
	public void excluir() {
		try {
			System.out.println("Serviço selecionado: " + getServicoSelecionado().getId());
			servicoDAO.excluir(this.getServicoSelecionado().getId());
			this.getServicos().remove(getServicoSelecionado());
			facesMessages.info("Serviço " + getServicoSelecionado().getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		}
	}

	public void pesquisar() {
		
		System.out.println(filtro.getNome() + "||" + filtro.getDescricao());
		setServicosFiltrados(servicoDAO.filtrados(filtro));
	}
	
	public void inicializar() {
		setServicos(servicoDAO.buscarTodos());
	}




	public List<Servico> getServicos() {
		return servicos;
	}



	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}



	public Servico getServicoSelecionado() {
		return servicoSelecionado;
	}



	public void setServicoSelecionado(Servico servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}



	public List<Servico> getServicosFiltrados() {
		return servicosFiltrados;
	}

	public void setServicosFiltrados(List<Servico> servicosFiltrados) {
		this.servicosFiltrados = servicosFiltrados;
	}

	public ServicoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ServicoFilter filtro) {
		this.filtro = filtro;
	}
	public List<Operacao> getOperacoesAtivas() {
		operacoesAtivas = operacaoDAO.buscarOperacoes();
		return operacoesAtivas; 
	}
	public void setOperacoesAtivas(List<Operacao> operacoesAtivas) {
		this.operacoesAtivas = operacoesAtivas;
	}
	public List<Operacao> getOperacoes() {
		return operacoes;
	}
	public void setOperacoes(List<Operacao> operacoes) {
		this.operacoes = operacoes;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}

	


}
