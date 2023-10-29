package co.edu.tiendaonline.service.domain.correoelectronicocliente.rules;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.service.domain.ValidationRule;
import co.edu.tiendaonline.service.domain.correoelectronicocliente.CorreoElectronicoClienteDomain;

public class CorreoElectronicoClienteValidationRule implements ValidationRule<CorreoElectronicoClienteDomain> {
	private static final ValidationRule<CorreoElectronicoClienteDomain> instancia = new CorreoElectronicoClienteValidationRule();
	
	private CorreoElectronicoClienteValidationRule() {
		super();
	}
	
	public static final void ejecutarValidacion(final CorreoElectronicoClienteDomain dato) {
		instancia.validar(dato);
	}

	@Override
	public void validar(CorreoElectronicoClienteDomain dato) {
		if(UtilObjeto.esNulo(dato)) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000158);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
}
