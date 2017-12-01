package br.com.sisinfra.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisinfra.dao.UsuariosDao;
import br.com.sisinfra.model.Usuario;
import br.com.sisinfra.service.NegocioException;
import br.com.sisinfra.util.FacesMessages;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuariosDao usuarioDAO;
	
	private List<Usuario> usuarios;
	
	
	private Usuario usuarioSelecionado;
	
	@Inject
	private FacesMessages facesMessages;
	
	
	
//	public void excluir() {
//		try {
//			usuarioDAO.excluir(getUsuarioSelecionado());
//			this.getUsuarios().remove(getUsuarioSelecionado());
//			facesMessages.info("Usuario " + getUsuarioSelecionado().getNome() + " excluído com sucesso.");
//		} catch (NegocioException e) {
//			facesMessages.error(e.getMessage());
//		}
//	}


//	public void inicializar() {
//		setUsuarios(usuarioDAO.buscarTodos());
//	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios= usuarios;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}


}
