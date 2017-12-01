package br.com.sisinfra.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisinfra.dao.ServidorDao;
import br.com.sisinfra.dao.filter.ServicoFilter;
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
	

	
	private Servidor servidorSelecionado;
	
	@Inject
	private FacesMessages facesMessages;
	
	
	
	public void excluir() {
		try {
			servidorDAO.excluir(getServidorSelecionado());
			this.getServidores().remove(getServidorSelecionado());
			facesMessages.info("Servidor " + getServidorSelecionado().getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		}
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


}
