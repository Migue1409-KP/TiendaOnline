package co.edu.tiendaonline.service.domain.cliente.rules;

import java.sql.Date;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilFecha;
import co.edu.tiendaonline.service.domain.ValidationRule;

public class FechaNacimientoClienteValidationRule implements ValidationRule<Date> {
	private static final ValidationRule<Date> instancia = new FechaNacimientoClienteValidationRule();
	
	private FechaNacimientoClienteValidationRule() {
		super();
	}
	
	public static final void ejecutarValidacion(final Date dato) {
		instancia.validar(dato);
	}

	@Override
	public void validar(Date dato) {
		validarObligatoriedad(dato);
	}
	
	private final void validarObligatoriedad(final Date dato) {
		if(UtilFecha.esNulo(dato)) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000169);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
}
