package br.com.sisinfra.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sisinfra.dao.UsuariosDao;
import br.com.sisinfra.model.Usuario;
import br.com.sisinfra.util.Transacional;

public class CadastroUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuariosDao usuarios;
	
	@Transacional
	public void salvar(Usuario usuario) {
		usuarios.guardar(usuario);
	}
	
	

}
