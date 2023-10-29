package co.edu.tiendaonline.service.bussineslogic.concrete.tipoidentificacion;

import java.util.UUID;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.data.dao.ClienteDAO;
import co.edu.tiendaonline.data.dao.TipoIdentificacionDAO;
import co.edu.tiendaonline.data.dao.daofactory.DAOFactory;
import co.edu.tiendaonline.service.bussineslogic.UseCase;
import co.edu.tiendaonline.service.domain.support.BooleanDomain;
import co.edu.tiendaonline.service.domain.tipoidentificacion.TipoIdentificacionDomain;
import co.edu.tiendaonline.service.dto.ClienteDTO;
import co.edu.tiendaonline.service.mapper.dto.concrete.ClienteDTOMapper;
import co.edu.tiendaonline.service.mapper.dto.concrete.TipoIdentificacionDTOMapper;
import co.edu.tiendaonline.service.mapper.entity.concrete.ClienteEntityMapper;

public class EliminarTipoIdentifacionUseCase implements UseCase<TipoIdentificacionDomain> {

	private DAOFactory factoria;
	
	public EliminarTipoIdentifacionUseCase(DAOFactory factoria) {
		setFactoria(factoria);
	}
	
	@Override
	public void execute(TipoIdentificacionDomain domain) {
		validarExistenciaRegistro(domain.getId());
		validarNoExistenciaRelacion(domain.getId());
		eliminar(domain.getId());
	}
	
	private final void validarExistenciaRegistro(final UUID id) {
		final var resultados = getTipoIdentificacionDAO().consultarPorId(id);
		
		if(resultados.isEmpty()) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000097);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
	
	private final void validarNoExistenciaRelacion(final UUID id) {
		final var tipoIdentificacion = TipoIdentificacionDomain.crear(id, null, null, BooleanDomain.crear(false, true));
		final var cliente = ClienteDTOMapper.convertToDomain(
				ClienteDTO.crear().setTipoIdentificacion(TipoIdentificacionDTOMapper.convertToDTO(tipoIdentificacion)));
		
		final var resultados = getClienteDAO().consultar(ClienteEntityMapper.convertToEntity(cliente));
		
		if(!resultados.isEmpty()) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000098);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
	
	private void eliminar(final UUID id) {
		getTipoIdentificacionDAO().eliminar(id);
	}
	
	private final DAOFactory getFactoria() {
		return factoria;
	}
	
	private final void setFactoria(final DAOFactory factoria) {
		if(UtilObjeto.esNulo(factoria)) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000076);
			final var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000077);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		this.factoria = factoria;
	}
	
	private final TipoIdentificacionDAO getTipoIdentificacionDAO() {
		return getFactoria().obtenerTipoIdentificacionDAO();
	}
	
	private final ClienteDAO getClienteDAO() {
		return getFactoria().obtenerClienteDAO();
	}
}
