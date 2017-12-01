package br.com.sisinfra.controller.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisinfra.dao.OperacaoDao;
import br.com.sisinfra.dao.ServicosDao;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.util.CDIServiceLocator;

	

@FacesConverter("servicosConverter")
public class ServicosCadastroConverter implements Converter {

	
	private ServicosDao servicoDAO;
	
	public ServicosCadastroConverter() {
		this.servicoDAO = CDIServiceLocator.getBean(ServicosDao.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Servico retorno = null;
		
		if (value != null) {
			retorno = this.servicoDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Servico) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
//		if (value != null) {
//			Servidor servico = (Servidor) value;
//			return servico.getId() == null ? null : servico.getId().toString();
//		}
		
		return "";
	}
}
