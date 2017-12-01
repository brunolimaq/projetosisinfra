package br.com.sisinfra.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.sisinfra.dao.ServidorDao;
import br.com.sisinfra.model.Servidor;
import br.com.sisinfra.util.Transacional;


public class CadastroServidor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private ServidorDao servidores;
	
	@Transacional
	public void salvar(Servidor servidor) throws NegocioException {
		if (StringUtils.isEmpty(servidor.getNome())) { 
			throw new NegocioException("O nome do servidor é obrigatório");
		}
		
		this.servidores.guardar(servidor);
;
	}
	

	
	

}
