package br.com.sisinfra.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.sisinfra.dao.ServicosDao;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.util.Transacional;

public class CadastroServico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServicosDao servicos;
	
	@Transacional
	public void salvar(Servico servico) {
		servicos.guardar(servico);
	}
	
	

}
