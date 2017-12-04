package br.com.sisinfra.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisinfra.dao.ServidorDao;
import br.com.sisinfra.dao.filter.ServicoFilter;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.model.Servidor;
import br.com.sisinfra.service.NegocioException;
import br.com.sisinfra.util.FacesMessages;

@Named
@ViewScoped
public class PesquisaServidorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServidorDao servidorDAO;
	
	private List<Servidor> servidores;
	
	private ServicoFilter filtro;
	
	private List<Servidor> servidorFiltrados;

	
	private Servidor servidorSelecionado;
	
	@Inject
	private FacesMessages facesMessages;
	
	

	public PesquisaServidorBean() {
		setFiltro(new ServicoFilter());
	}
	public void excluir() {
		try {
			servidorDAO.excluir(getServidorSelecionado());
			this.getServidores().remove(getServidorSelecionado());
			facesMessages.info("Servidor " + getServidorSelecionado().getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		}
	}

	public void pesquisar() {
		
		setServidorFiltrados(servidorDAO.filtrados(getFiltro()));
	}

	
	public void inicializar() {
		setServidores(servidorDAO.buscarTodos());
	}

	public List<Servidor> getServidores() {
		return servidores;
	}

	public void setServidores(List<Servidor> servidores) {
		this.servidores = servidores;
	}

	public Servidor getServidorSelecionado() {
		return servidorSelecionado;
	}

	public void setServidorSelecionado(Servidor servidorSelecionado) {
		this.servidorSelecionado = servidorSelecionado;
	}


	public ServicoFilter getFiltro() {
		return filtro;
	}


	public void setFiltro(ServicoFilter filtro) {
		this.filtro = filtro;
	}



	public List<Servidor> getServidorFiltrados() {
		return servidorFiltrados;
	}
	public void setServidorFiltrados(List<Servidor> servidorFiltrados) {
		this.servidorFiltrados = servidorFiltrados;
	}


}
