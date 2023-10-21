package co.edu.tiendaonline.service.domain.tipoidentificacion.rules;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilTexto;
import co.edu.tiendaonline.service.domain.ValidationRule;

public final class CodigoTipoIdentificacionValidationRule implements ValidationRule<String> {

	private static final ValidationRule<String> instancia = new CodigoTipoIdentificacionValidationRule();
	
	private CodigoTipoIdentificacionValidationRule() {
		super();
	}
	
	public static final void ejecutarValidacion(final String dato) {
		instancia.validar(dato);
	}
	
	@Override
	public void validar(String dato) {
		validarLongitud(dato);
		validarObligatoriedad(dato);
		validarFormato(dato);
	}

	private final void validarLongitud(final String dato) {
		if(!UtilTexto.longitudMaximaValida(dato, 50)) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000117);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
	
	private final void validarObligatoriedad(final String dato) {
		if(UtilTexto.estaVacio(dato)) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000118);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
	
	private final void validarFormato(final String dato) {
		if(!UtilTexto.contieneSoloLetras(dato)) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000118);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
}
