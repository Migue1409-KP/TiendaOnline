package co.edu.tiendaonline.service.bussineslogic.validator.concrete.cliente;

import co.edu.tiendaonline.service.bussineslogic.validator.Validator;
import co.edu.tiendaonline.service.domain.cliente.ClienteDomain;
import co.edu.tiendaonline.service.domain.cliente.rules.ClienteValidationRule;
import co.edu.tiendaonline.service.domain.cliente.rules.IdClienteValidationRule;

public class EliminarClienteValidator implements Validator<ClienteDomain> {
	private static final Validator<ClienteDomain> instancia = new EliminarClienteValidator();
	
	private EliminarClienteValidator() {
		super();
	}
	
	public static final void ejecutar(final ClienteDomain data) {
		instancia.execute(data);
	}

	@Override
	public void execute(ClienteDomain data) {
		ClienteValidationRule.ejecutarValidacion(data);
		IdClienteValidationRule.ejecutarValidacion(data.getId());
	}
}
