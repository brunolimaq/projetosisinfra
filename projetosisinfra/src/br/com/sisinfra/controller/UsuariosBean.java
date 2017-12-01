package br.com.sisinfra.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sisinfra.model.Grupo;
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
	
	public void salvar(){
		
		System.out.println("Grupos: " + usuario.getGrupos());
		
		cadastroUsuario.salvar(getUsuario());
	
		messages.info("Usuário cadastrado com sucesso!");
	}




	public Usuario getUsuario() {
		return usuario;
	}




	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}




	public Grupo getGrupo() {
		return grupo;
	}




	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}







	
	

}

