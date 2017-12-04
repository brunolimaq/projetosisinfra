package br.com.sisinfra.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisinfra.dao.GrupoDao;
import br.com.sisinfra.dao.OperacaoDao;
import br.com.sisinfra.model.Grupo;
import br.com.sisinfra.model.Operacao;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.model.Usuario;
import br.com.sisinfra.service.CadastroUsuario;
import br.com.sisinfra.util.FacesMessages;


@Named 
@RequestScoped 
public class UsuariosBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Usuario usuario;
	
	@Inject
	private FacesMessages messages;
	
	
	@Inject
	private CadastroUsuario cadastroUsuario;
	
	private Grupo grupo;
	
	@Inject
	private GrupoDao grupoDao;


	private List<Grupo> grupos;


	@PostConstruct
	public void inicializar() {
		this.limpar();
		this.setGrupos(grupoDao.buscarTodos());
	}
	
	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

	
	public void limpar() {
		this.setGrupo(new Grupo());
	}

	public void salvar(){
		
		System.out.println("Grupos: " + usuario.getGrupos());
		
		cadastroUsuario.salvar(getUsuario());
		this.limpar();

		messages.info("Usuário cadastrado com sucesso!");
	}




	public Usuario getUsuario() {
		return usuario;
	}




	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}


	

}

