package co.edu.tiendaonline.service.bussineslogic.validator.concrete.cliente;

import co.edu.tiendaonline.service.bussineslogic.validator.Validator;
import co.edu.tiendaonline.service.domain.cliente.ClienteDomain;
import co.edu.tiendaonline.service.domain.cliente.rules.ClienteValidationRule;
import co.edu.tiendaonline.service.domain.cliente.rules.FechaNacimientoClienteValidationRule;
import co.edu.tiendaonline.service.domain.cliente.rules.IdentificacionClienteValidationRule;
import co.edu.tiendaonline.service.domain.correoelectronicocliente.rules.CorreoElectronicoClienteValidationRule;
import co.edu.tiendaonline.service.domain.correoelectronicocliente.rules.CorreoElectronicoCorreoElectronicoClienteValidationRule;
import co.edu.tiendaonline.service.domain.nombrecompletocliente.rules.NombreCompletoClienteValidationRule;
import co.edu.tiendaonline.service.domain.nombrecompletocliente.rules.PrimerApellidoNombreCompletoClienteValidationRule;
import co.edu.tiendaonline.service.domain.nombrecompletocliente.rules.PrimerNombreNombreCompletoClienteValidationRule;
import co.edu.tiendaonline.service.domain.nombrecompletocliente.rules.SegundoApellidoNombreCompletoClienteValidationRule;
import co.edu.tiendaonline.service.domain.nombrecompletocliente.rules.SegundoNombreNombreCompletoClienteValidationRule;
import co.edu.tiendaonline.service.domain.numerotelefonomovilcliente.rules.NumeroTelefonoMovilClienteValidationRule;
import co.edu.tiendaonline.service.domain.numerotelefonomovilcliente.rules.NumeroTelefonoMovilNumeroTelefonoMovilClienteValidationRule;
import co.edu.tiendaonline.service.domain.tipoidentificacion.rules.IdTipoIdentificacionValidationRule;
import co.edu.tiendaonline.service.domain.tipoidentificacion.rules.TipoIdentificacionValidationRule;

public class RegistrarClienteValidator implements Validator<ClienteDomain> {
	private static final Validator<ClienteDomain> instancia = new RegistrarClienteValidator();
	
	private RegistrarClienteValidator() {
		super();
	}
	
	public static final void ejecutar(final ClienteDomain data) {
		instancia.execute(data);
	}

	@Override
	public void execute(ClienteDomain data) {
		ClienteValidationRule.ejecutarValidacion(data);
		TipoIdentificacionValidationRule.ejecutarValidacion(data.getTipoIdentificacion());
		IdTipoIdentificacionValidationRule.ejecutarValidacion(data.getTipoIdentificacion().getId());
		IdentificacionClienteValidationRule.ejecutarValidacion(data.getIdentificacion());
		NombreCompletoClienteValidationRule.ejecutarValidacion(data.getNombreCompleto());
		PrimerNombreNombreCompletoClienteValidationRule.ejecutarValidacion(data.getNombreCompleto().getPrimerNombre());
		SegundoNombreNombreCompletoClienteValidationRule.ejecutarValidacion(data.getNombreCompleto().getSegundoNombre());
		PrimerApellidoNombreCompletoClienteValidationRule.ejecutarValidacion(data.getNombreCompleto().getPrimerApellido());
		SegundoApellidoNombreCompletoClienteValidationRule.ejecutarValidacion(data.getNombreCompleto().getSegundoApellido());
		CorreoElectronicoClienteValidationRule.ejecutarValidacion(data.getCorreoElectronico());
		CorreoElectronicoCorreoElectronicoClienteValidationRule.ejecutarValidacion(data.getCorreoElectronico().getCorreoElectronico());
		NumeroTelefonoMovilClienteValidationRule.ejecutarValidacion(data.getNumeroTelefonoMovil());
		NumeroTelefonoMovilNumeroTelefonoMovilClienteValidationRule.ejecutarValidacion(data.getNumeroTelefonoMovil().getNumeroTelefonoMovil());
		FechaNacimientoClienteValidationRule.ejecutarValidacion(data.getFechaNacimiento());
	}
}
