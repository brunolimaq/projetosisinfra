package br.com.sisinfra.controller.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sisinfra.dao.UsuariosDao;
import br.com.sisinfra.model.Usuario;
import br.com.sisinfra.util.CDIServiceLocator;

@FacesConverter(forClass=Usuario.class)
public class UsuarioConverter implements Converter {

	//@Inject
	private UsuariosDao usuarios;
	
	public UsuarioConverter() {
		this.usuarios = (UsuariosDao) CDIServiceLocator.getBean(UsuariosDao.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;

		if (value != null) {
			retorno = this.usuarios.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Usuario) value).getId().toString();
		}
		return "";
	}

}
