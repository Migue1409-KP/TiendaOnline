package co.edu.tiendaonline.service.domain.tipoidentificacion.rules;

import java.util.UUID;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilUUID;
import co.edu.tiendaonline.service.domain.ValidationRule;

public final class IdTipoIdentificacionValidationRule implements ValidationRule<UUID> {
	private static final ValidationRule<UUID> instancia = new IdTipoIdentificacionValidationRule();
	
	private IdTipoIdentificacionValidationRule() {
		super();
	}
	
	public static final void ejecutarValidacion(final UUID dato) {
		instancia.validar(dato);
	}
	
	@Override
	public void validar(UUID dato) {
		validarObligatoriedad(dato);
	}
	
	private final void validarObligatoriedad(final UUID dato) {
		if(UtilUUID.esNulo(dato)) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000123);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
}
