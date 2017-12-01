package br.com.sisinfra.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.sisinfra.dao.OperacaoDao;
import br.com.sisinfra.model.Operacao;
import br.com.sisinfra.util.Transacional;


public class CadastroOperacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private OperacaoDao operacoes;
	
	@Transacional
	public void salvar(Operacao operacao) throws NegocioException {
		if (StringUtils.isEmpty(operacao.getNome())) { 
			throw new NegocioException("O nome da operação é obrigatório");
		}
		
		this.operacoes.guardar(operacao);
	}
	

	
	

}
