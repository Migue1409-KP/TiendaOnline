package co.edu.tiendaonline.service.bussineslogic.validator.concrete.tipoidentificacion;

import co.edu.tiendaonline.service.bussineslogic.validator.Validator;
import co.edu.tiendaonline.service.domain.tipoidentificacion.TipoIdentificacionDomain;
import co.edu.tiendaonline.service.domain.tipoidentificacion.rules.CodigoTipoIdentificacionValidationRule;
import co.edu.tiendaonline.service.domain.tipoidentificacion.rules.NombreTipoIdentificacionValidationRule;
import co.edu.tiendaonline.service.domain.tipoidentificacion.rules.TipoIdentificacionValidationRule;

public final class RegistrarTipoIdentificacionValidator implements Validator<TipoIdentificacionDomain>{
	private static final Validator<TipoIdentificacionDomain> instancia = new RegistrarTipoIdentificacionValidator();
	
	private RegistrarTipoIdentificacionValidator() {
		super();
	}
	
	public static final void ejecutar(final TipoIdentificacionDomain data) {
		instancia.execute(data);
	}
	
	@Override
	public void execute(TipoIdentificacionDomain data) {
		TipoIdentificacionValidationRule.ejecutarValidacion(data);
		CodigoTipoIdentificacionValidationRule.ejecutarValidacion(data.getCodigo());
		NombreTipoIdentificacionValidationRule.ejecutarValidacion(data.getNombre());
	}

}
