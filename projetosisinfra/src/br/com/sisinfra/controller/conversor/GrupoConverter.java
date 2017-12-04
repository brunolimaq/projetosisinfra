package br.com.sisinfra.controller.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisinfra.dao.GrupoDao;
import br.com.sisinfra.model.Grupo;
import br.com.sisinfra.model.SistemaOperacional;
import br.com.sisinfra.util.CDIServiceLocator;


@FacesConverter("gruposConverter")
public class GrupoConverter implements Converter {

	//@Inject
	private GrupoDao grupos;
	
	public GrupoConverter() {
		grupos = CDIServiceLocator.getBean(GrupoDao.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = grupos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Grupo) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
//		
//		if (value != null) {
//			return ((Grupo) value).getId().toString();
//		}
//		
		return "";
	}

}

