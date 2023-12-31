package co.edu.tiendaonline.service.bussineslogic.concrete.tipoidentificacion;

import java.util.UUID;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.data.dao.TipoIdentificacionDAO;
import co.edu.tiendaonline.data.dao.daofactory.DAOFactory;
import co.edu.tiendaonline.service.bussineslogic.UseCase;
import co.edu.tiendaonline.service.domain.support.BooleanDomain;
import co.edu.tiendaonline.service.domain.tipoidentificacion.TipoIdentificacionDomain;
import co.edu.tiendaonline.service.mapper.entity.concrete.TipoIdentificacionEntityMapper;

public class ModificarTipoIdentificacionUseCase implements UseCase<TipoIdentificacionDomain> {

	private DAOFactory factoria;
	
	public ModificarTipoIdentificacionUseCase(DAOFactory factoria) {
		setFactoria(factoria);
	}

	@Override
	public void execute(TipoIdentificacionDomain domain) {
		validarExistenciaRegistro(domain.getId());
		validarNoExistenciaMismoCodigo(domain.getId(), domain.getCodigo());
		validarNoExistenciaMismoNombre(domain.getId(), domain.getNombre());
		actualizar(domain);
	}
	
	private final void validarExistenciaRegistro(final UUID id) {
		final var resultados = getTipoIdentificacionDAO().consultarPorId(id);
		
		if(resultados.isEmpty()) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000092);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
	
	private final void validarNoExistenciaMismoCodigo(final UUID id, final String codigo) {
		final var domain = TipoIdentificacionDomain.crear(null, codigo, null, BooleanDomain.crear(false, true));
		final var entity = TipoIdentificacionEntityMapper.convertToEntity(domain);
		final var resultados = getTipoIdentificacionDAO().consultar(entity);
		
		if(!resultados.isEmpty()) {
			for (int i = 0; i < resultados.size(); i++) {
				if(!resultados.get(i).getId().equals(id)) {
					final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000093);
					throw ServiceTiendaOnlineException.crear(mensajeUsuario);		
				}
			}
		}
	}
	
	private final void validarNoExistenciaMismoNombre(final UUID id, final String nombre) {
		final var domain = TipoIdentificacionDomain.crear(null, null, nombre, BooleanDomain.crear(false, true));
		final var entity = TipoIdentificacionEntityMapper.convertToEntity(domain);
		final var resultados = getTipoIdentificacionDAO().consultar(entity);
		
		if(!resultados.isEmpty()) {
			for (int i = 0; i < resultados.size(); i++) {
				if(!resultados.get(i).getId().equals(id)) {
					final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000094);
					throw ServiceTiendaOnlineException.crear(mensajeUsuario);		
				}
			}
		}
	}
	
	private void actualizar(final TipoIdentificacionDomain domain) {
		getTipoIdentificacionDAO().modificar(TipoIdentificacionEntityMapper.convertToEntity(domain));
	}
	
	private final DAOFactory getFactoria() {
		return factoria;
	}
	
	private final void setFactoria(final DAOFactory factoria) {
		if(UtilObjeto.esNulo(factoria)) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000090);
			final var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000091);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		this.factoria = factoria;
	}
	
	private final TipoIdentificacionDAO getTipoIdentificacionDAO() {
		return getFactoria().obtenerTipoIdentificacionDAO();
	}

}
