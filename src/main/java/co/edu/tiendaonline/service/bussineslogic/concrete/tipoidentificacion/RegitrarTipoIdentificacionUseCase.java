package co.edu.tiendaonline.service.bussineslogic.concrete.tipoidentificacion;

import java.util.Optional;
import java.util.UUID;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.crosscutting.util.UtilUUID;
import co.edu.tiendaonline.data.dao.TipoIdentificacionDAO;
import co.edu.tiendaonline.data.dao.daofactory.DAOFactory;
import co.edu.tiendaonline.data.entity.TipoIdentificacionEntity;
import co.edu.tiendaonline.service.bussineslogic.UseCase;
import co.edu.tiendaonline.service.domain.TipoIdentificacionDomain;
import co.edu.tiendaonline.service.mapper.entity.concrete.TipoIdentificacionEntityMapper;

public final class RegitrarTipoIdentificacionUseCase implements UseCase<TipoIdentificacionDomain>{

	private DAOFactory factoria;
	
	public RegitrarTipoIdentificacionUseCase(DAOFactory factoria) {
		setFactoria(factoria);
	}
	
	@Override
	public void execute(TipoIdentificacionDomain domain) {
		//TODO: Validate fields(data type, length, mandatory, restriction
		validarNoExistenciaMismoCodigo(domain.getCodigo());
		validarNoExistenciaMismoNombre(domain.getNombre());
		domain = obtenerIdentificadorTipoIdentificacion(domain);
		registrar(domain);
	}
	
	private final TipoIdentificacionDomain obtenerIdentificadorTipoIdentificacion(TipoIdentificacionDomain domain) {
		Optional<TipoIdentificacionEntity> optional;
		UUID uuid;
		
		do {
			uuid = UtilUUID.generarRandomUUID();
			optional = getTipoIdentificacionDAO().consultarPorId(uuid);
		} while (optional.isPresent());
		
		return TipoIdentificacionDomain.crear(uuid, domain.getCodigo(), domain.getNombre(), domain.isEstado());
	}
	
	private final void validarNoExistenciaMismoCodigo(final String codigo) {
		//TODO: improve method validations
		var domain = TipoIdentificacionDomain.crear(null, codigo, null, false);
		var entity = TipoIdentificacionEntityMapper.convertEntity(domain);
		var resultados = getTipoIdentificacionDAO().consultar(entity);
		
		if(!resultados.isEmpty()) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000079);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
	
	private final void validarNoExistenciaMismoNombre(final String nombre) {
		//TODO: improve method validations
		var domain = TipoIdentificacionDomain.crear(null, null, nombre, false);
		var entity = TipoIdentificacionEntityMapper.convertEntity(domain);
		var resultados = getTipoIdentificacionDAO().consultar(entity);
		
		if(!resultados.isEmpty()) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000078);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
	
	private void registrar(final TipoIdentificacionDomain domain) {
		getTipoIdentificacionDAO().crear(TipoIdentificacionEntityMapper.convertEntity(domain));
	}

	private final DAOFactory getFactoria() {
		return factoria;
	}
	
	private final void setFactoria(final DAOFactory factoria) {
		if(UtilObjeto.esNulo(factoria)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000076);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000077);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		this.factoria = factoria;
	}

	private final TipoIdentificacionDAO getTipoIdentificacionDAO() {
		return getFactoria().obtenerTipoIdentificacionDAO();
	}
}