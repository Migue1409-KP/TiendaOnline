package co.edu.tiendaonline.service.mapper.entity.concrete;

import java.util.ArrayList;
import java.util.List;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.data.entity.support.BooleanEntity;
import co.edu.tiendaonline.service.domain.support.BooleanDomain;
import co.edu.tiendaonline.service.mapper.entity.EntityMapper;

public class BooleanEntityMapper implements EntityMapper<BooleanEntity, BooleanDomain> {

	private static final EntityMapper<BooleanEntity, BooleanDomain> instancia = new BooleanEntityMapper();
	
	private BooleanEntityMapper() {
		super();
	}
	
	@Override
	public final BooleanDomain toDomain(final BooleanEntity entity) {
		if(UtilObjeto.esNulo(entity)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000134);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		 
		return BooleanDomain.crear(entity.isValor(), entity.isValorDefecto());
	}

	@Override
	public final BooleanEntity toEntity(final BooleanDomain domain) {
		if(UtilObjeto.esNulo(domain)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000135);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		return BooleanEntity.crear(domain.isValor(), domain.isValorDefecto());
	}

	public static final BooleanDomain convertToDomain(final BooleanEntity entity) {		 
		return instancia.toDomain(entity);
	}
	
	public static final BooleanEntity convertToEntity(final BooleanDomain domain) {
		return instancia.toEntity(domain);
	}
	
	public static final List<BooleanDomain>  convertToListDomain(final List<BooleanEntity> entity){
		List<BooleanDomain> resultados = new ArrayList<>();
		for (int i = 0; i < entity.size(); i++) {
			resultados.add(convertToDomain(entity.get(i)));
		}
		return resultados;
	}
}
