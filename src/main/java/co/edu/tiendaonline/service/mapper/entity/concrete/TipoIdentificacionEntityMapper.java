package co.edu.tiendaonline.service.mapper.entity.concrete;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.data.entity.TipoIdentificacionEntity;
import co.edu.tiendaonline.service.domain.TipoIdentificacionDomain;
import co.edu.tiendaonline.service.mapper.entity.EntityMapper;

public class TipoIdentificacionEntityMapper implements EntityMapper<TipoIdentificacionEntity, TipoIdentificacionDomain> {

	private static final EntityMapper<TipoIdentificacionEntity, TipoIdentificacionDomain> instancia = new TipoIdentificacionEntityMapper();
	
	private TipoIdentificacionEntityMapper() {
		super();
	}
	
	@Override
	public final TipoIdentificacionDomain toDomain(final TipoIdentificacionEntity entity) {
		if(UtilObjeto.esNulo(entity)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000074);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		 
		return TipoIdentificacionDomain.crear(entity.getId(), entity.getCodigo(), entity.getNombre(), entity.isEstado());
	}

	@Override
	public final TipoIdentificacionEntity toEntity(final TipoIdentificacionDomain domain) {
		if(UtilObjeto.esNulo(domain)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000075);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		return TipoIdentificacionEntity.crear(domain.getId(), domain.getCodigo(), domain.getNombre(), domain.isEstado());
	}

	public static final TipoIdentificacionDomain convertToDomain(final TipoIdentificacionEntity entity) {		 
		return instancia.toDomain(entity);
	}
	
	public static final TipoIdentificacionEntity convertEntity(final TipoIdentificacionDomain domain) {
		return instancia.toEntity(domain);
	}
}