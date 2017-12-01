package br.com.sisinfra.controller.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisinfra.dao.ServicosDao;
import br.com.sisinfra.model.Servico;
import br.com.sisinfra.util.CDIServiceLocator;


@FacesConverter(forClass = Servico.class)
public class ServicoConverter implements Converter {

	//@Inject
	private ServicosDao servicos;
	
	public ServicoConverter() {
		servicos = CDIServiceLocator.getBean(ServicosDao.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Servico retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = servicos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
	
		if (value != null) {
			Servico servico = (Servico) value;
			return servico.getId() == null ? null : servico.getId().toString();
		}
		
		return "";
	}

}

