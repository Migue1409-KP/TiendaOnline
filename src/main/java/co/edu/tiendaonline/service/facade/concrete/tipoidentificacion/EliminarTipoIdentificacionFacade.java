package co.edu.tiendaonline.service.facade.concrete.tipoidentificacion;

import co.edu.tiendaonline.crosscutting.exception.TiendaOnlineException;
import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.data.dao.daofactory.DAOFactory;
import co.edu.tiendaonline.data.dao.daofactory.enumerator.TipoDAOFactory;
import co.edu.tiendaonline.service.bussineslogic.concrete.tipoidentificacion.EliminarTipoIdentifacionUseCase;
import co.edu.tiendaonline.service.bussineslogic.validator.concrete.tipoidentificacion.EliminarTipoIdentificacionValidator;
import co.edu.tiendaonline.service.domain.tipoidentificacion.TipoIdentificacionDomain;
import co.edu.tiendaonline.service.dto.TipoIdentificacionDTO;
import co.edu.tiendaonline.service.facade.Facade;
import co.edu.tiendaonline.service.mapper.dto.concrete.TipoIdentificacionDTOMapper;

public final class EliminarTipoIdentificacionFacade implements Facade<TipoIdentificacionDTO>{

	@Override
	public void execute(final TipoIdentificacionDTO dto) {
		final TipoIdentificacionDomain domain = TipoIdentificacionDTOMapper.convertToDomain(dto);
		EliminarTipoIdentificacionValidator.ejecutar(domain);
		
		DAOFactory daofactory = DAOFactory.obtenerDAOFactory(TipoDAOFactory.SQLSERVER);
		
		try {
			daofactory.iniciarTransaccion();
			var useCase = new EliminarTipoIdentifacionUseCase(daofactory);
			useCase.execute(domain);
			daofactory.confirmarTransaccion();
		} catch (TiendaOnlineException e) {
			daofactory.cancelarTransaccion();
			throw e;
		} catch (Exception e) {
			daofactory.cancelarTransaccion();
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000125);
			final var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000126);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		} finally {
			daofactory.cerrarConexion();
		}
	}

}
