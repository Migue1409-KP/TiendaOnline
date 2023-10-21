package co.edu.tiendaonline.service.bussineslogic.validator.concrete.tipoidentificacion;

import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.crosscutting.util.UtilTexto;
import co.edu.tiendaonline.crosscutting.util.UtilUUID;
import co.edu.tiendaonline.service.bussineslogic.validator.Validator;
import co.edu.tiendaonline.service.domain.tipoidentificacion.TipoIdentificacionDomain;
import co.edu.tiendaonline.service.domain.tipoidentificacion.rules.CodigoTipoIdentificacionValidationRule;
import co.edu.tiendaonline.service.domain.tipoidentificacion.rules.IdTipoIdentificacionValidationRule;
import co.edu.tiendaonline.service.domain.tipoidentificacion.rules.NombreTipoIdentificacionValidationRule;

public final class ConsultarTipoIdentificacionValidator implements Validator<TipoIdentificacionDomain>{
	private static final Validator<TipoIdentificacionDomain> instancia = new ConsultarTipoIdentificacionValidator();
	
	private ConsultarTipoIdentificacionValidator() {
		super();
	}
	
	public static final void ejecutar(final TipoIdentificacionDomain data) {
		instancia.execute(data);
	}
	
	@Override
	public void execute(TipoIdentificacionDomain data) {
		if(!UtilObjeto.esNulo(data)) {
			if(!UtilUUID.esNulo(data.getId())) {				
				IdTipoIdentificacionValidationRule.ejecutarValidacion(data.getId());
			}
			
			if(!UtilTexto.estaVacio(data.getCodigo())) {				
				CodigoTipoIdentificacionValidationRule.ejecutarValidacion(data.getCodigo());
			}
			
			if(!UtilTexto.estaVacio(data.getNombre())) {				
				NombreTipoIdentificacionValidationRule.ejecutarValidacion(data.getNombre());
			}
		}
	}

}
