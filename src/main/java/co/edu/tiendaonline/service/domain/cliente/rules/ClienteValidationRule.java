package co.edu.tiendaonline.service.domain.cliente.rules;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.service.domain.ValidationRule;
import co.edu.tiendaonline.service.domain.cliente.ClienteDomain;

public class ClienteValidationRule implements ValidationRule<ClienteDomain> {
	private static final ValidationRule<ClienteDomain> instancia = new ClienteValidationRule();
	
	private ClienteValidationRule() {
		super();
	}
	
	public static final void ejecutarValidacion(final ClienteDomain dato) {
		instancia.validar(dato);
	}

	@Override
	public void validar(ClienteDomain dato) {
		if(UtilObjeto.esNulo(dato)) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000158);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
}
