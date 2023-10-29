package co.edu.tiendaonline.service.domain.nombrecompletocliente.rules;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.service.domain.ValidationRule;
import co.edu.tiendaonline.service.domain.nombrecompletocliente.NombreCompletoClienteDomain;

public class NombreCompletoClienteValidationRule implements ValidationRule<NombreCompletoClienteDomain> {
	private static final ValidationRule<NombreCompletoClienteDomain> instancia = new NombreCompletoClienteValidationRule();
	
	private NombreCompletoClienteValidationRule() {
		super();
	}
	
	public static final void ejecutarValidacion(final NombreCompletoClienteDomain dato) {
		instancia.validar(dato);
	}

	@Override
	public void validar(NombreCompletoClienteDomain dato) {
		if(UtilObjeto.esNulo(dato)) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000158);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
}
