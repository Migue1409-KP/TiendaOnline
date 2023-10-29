package co.edu.tiendaonline.service.bussineslogic.validator.concrete.cliente;

import co.edu.tiendaonline.crosscutting.util.UtilFecha;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.crosscutting.util.UtilTexto;
import co.edu.tiendaonline.crosscutting.util.UtilUUID;
import co.edu.tiendaonline.service.bussineslogic.validator.Validator;
import co.edu.tiendaonline.service.domain.cliente.ClienteDomain;
import co.edu.tiendaonline.service.domain.cliente.rules.FechaNacimientoClienteValidationRule;
import co.edu.tiendaonline.service.domain.cliente.rules.IdClienteValidationRule;
import co.edu.tiendaonline.service.domain.cliente.rules.IdentificacionClienteValidationRule;
import co.edu.tiendaonline.service.domain.correoelectronicocliente.rules.CorreoElectronicoCorreoElectronicoClienteValidationRule;
import co.edu.tiendaonline.service.domain.nombrecompletocliente.rules.PrimerNombreNombreCompletoClienteValidationRule;
import co.edu.tiendaonline.service.domain.numerotelefonomovilcliente.rules.NumeroTelefonoMovilNumeroTelefonoMovilClienteValidationRule;
import co.edu.tiendaonline.service.domain.tipoidentificacion.rules.IdTipoIdentificacionValidationRule;

public class ConsultarClienteValidator implements Validator<ClienteDomain> {
	private static final Validator<ClienteDomain> instancia = new ConsultarClienteValidator();
	
	private ConsultarClienteValidator() {
		super();
	}
	
	public static final void ejecutar(final ClienteDomain data) {
		instancia.execute(data);
	}

	@Override
	public void execute(ClienteDomain data) {
		if(!UtilObjeto.esNulo(data)) {
			if(!UtilUUID.esNulo(data.getId())) {
				IdClienteValidationRule.ejecutarValidacion(data.getId());
			}
			
			if(!UtilObjeto.esNulo(data.getTipoIdentificacion()) && !UtilUUID.esNulo(data.getTipoIdentificacion().getId())) {
				IdTipoIdentificacionValidationRule.ejecutarValidacion(data.getId());
			}
			
			if(!UtilTexto.estaVacio(data.getIdentificacion())) {
				IdentificacionClienteValidationRule.ejecutarValidacion(data.getIdentificacion());
			}
			
			if(!UtilObjeto.esNulo(data.getNombreCompleto())) {
				if(!UtilTexto.estaVacio(data.getNombreCompleto().getPrimerNombre())) {
					PrimerNombreNombreCompletoClienteValidationRule.ejecutarValidacion(data.getNombreCompleto().getPrimerNombre());
				}
				
				if(!UtilTexto.estaVacio(data.getNombreCompleto().getSegundoNombre())) {
					PrimerNombreNombreCompletoClienteValidationRule.ejecutarValidacion(data.getNombreCompleto().getSegundoNombre());
				}
				
				if(!UtilTexto.estaVacio(data.getNombreCompleto().getPrimerApellido())) {
					PrimerNombreNombreCompletoClienteValidationRule.ejecutarValidacion(data.getNombreCompleto().getPrimerApellido());
				}
				
				if(!UtilTexto.estaVacio(data.getNombreCompleto().getSegundoApellido())) {
					PrimerNombreNombreCompletoClienteValidationRule.ejecutarValidacion(data.getNombreCompleto().getSegundoApellido());
				}
			}
			
			if(!UtilObjeto.esNulo(data.getCorreoElectronico()) && !UtilTexto.estaVacio(data.getCorreoElectronico().getCorreoElectronico())) {
				CorreoElectronicoCorreoElectronicoClienteValidationRule.ejecutarValidacion(data.getCorreoElectronico().getCorreoElectronico());
			}
			
			if(!UtilObjeto.esNulo(data.getNumeroTelefonoMovil()) && !UtilTexto.estaVacio(data.getNumeroTelefonoMovil().getNumeroTelefonoMovil())) {
				NumeroTelefonoMovilNumeroTelefonoMovilClienteValidationRule.ejecutarValidacion(data.getNumeroTelefonoMovil().getNumeroTelefonoMovil());
			}
			
			if(!UtilFecha.esNulo(data.getFechaNacimiento())) {
				FechaNacimientoClienteValidationRule.ejecutarValidacion(data.getFechaNacimiento());
			}
			
		}
	}
}
