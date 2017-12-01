package br.com.sisinfra.controller.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisinfra.dao.OperacaoDao;
import br.com.sisinfra.dao.ServicosDao;
import br.com.sisinfra.dao.OperacaoDao;
import br.com.sisinfra.model.Operacao;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.util.CDIServiceLocator;

	

@FacesConverter("operacoesConverter")
public class OperacaoConverter implements Converter {

	
	private OperacaoDao operacaoDAO;
	
	public OperacaoConverter() {
		this.operacaoDAO = CDIServiceLocator.getBean(OperacaoDao.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Operacao retorno = null;
		
		if (value != null) {
			retorno = this.operacaoDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Operacao) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
//		if (value != null) {
//			Operacao operacao= (Operacao) value;
//			return operacao.getId() == null ? null : operacao.getId().toString();
//		}
		
		return "";
	}
}
