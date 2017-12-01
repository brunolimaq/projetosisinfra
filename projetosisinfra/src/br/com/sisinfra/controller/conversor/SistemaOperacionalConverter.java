package br.com.sisinfra.controller.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisinfra.dao.SistemaOperacionalDao;
import br.com.sisinfra.model.SistemaOperacional;
import br.com.sisinfra.util.CDIServiceLocator;

	

@FacesConverter(forClass = SistemaOperacional.class)
public class SistemaOperacionalConverter implements Converter {

	
	private SistemaOperacionalDao sistemaOperacionalDAO;
	
	public SistemaOperacionalConverter() {
		this.sistemaOperacionalDAO = CDIServiceLocator.getBean(SistemaOperacionalDao.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		SistemaOperacional retorno = null;
		
		if (value != null) {
			retorno = this.sistemaOperacionalDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((SistemaOperacional) value).getId();
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
