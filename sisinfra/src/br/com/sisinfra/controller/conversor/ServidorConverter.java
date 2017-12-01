package br.com.sisinfra.controller.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisinfra.dao.ServidorDao;
import br.com.sisinfra.model.Servidor;
import br.com.sisinfra.util.CDIServiceLocator;


@FacesConverter(forClass = Servidor.class)
public class ServidorConverter implements Converter {

	//@Inject
	private ServidorDao servidores;
	
	public ServidorConverter() {
		servidores = CDIServiceLocator.getBean(ServidorDao.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Servidor retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = servidores.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
	
		if (value != null) {
			Servidor servidor = (Servidor) value;
			return servidor.getId() == null ? null : servidor.getId().toString();
		}
		
		return "";
	}

}

