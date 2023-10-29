package co.edu.tiendaonline.service.facade.concrete.cliente;

import co.edu.tiendaonline.crosscutting.exception.TiendaOnlineException;
import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.data.dao.daofactory.DAOFactory;
import co.edu.tiendaonline.data.dao.daofactory.enumerator.TipoDAOFactory;
import co.edu.tiendaonline.service.bussineslogic.concrete.cliente.RegistrarClienteUseCase;
import co.edu.tiendaonline.service.bussineslogic.validator.concrete.cliente.RegistrarClienteValidator;
import co.edu.tiendaonline.service.domain.cliente.ClienteDomain;
import co.edu.tiendaonline.service.dto.ClienteDTO;
import co.edu.tiendaonline.service.facade.Facade;
import co.edu.tiendaonline.service.mapper.dto.concrete.ClienteDTOMapper;

public final class RegistrarClienteFacade implements Facade<ClienteDTO>{

	@Override
	public void execute(final ClienteDTO dto) {
		final ClienteDomain domain = ClienteDTOMapper.convertToDomain(dto);
		RegistrarClienteValidator.ejecutar(domain);
		
		DAOFactory daofactory = DAOFactory.obtenerDAOFactory(TipoDAOFactory.SQLSERVER);
		
		try {
			daofactory.iniciarTransaccion();
			var useCase = new RegistrarClienteUseCase(daofactory);
			useCase.execute(domain);
			daofactory.confirmarTransaccion();
		} catch (TiendaOnlineException e) {
			daofactory.cancelarTransaccion();
			throw e;
		} catch (Exception e) {
			daofactory.cancelarTransaccion();
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000180);
			final var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000181);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		} finally {
			daofactory.cerrarConexion();
		}
	}
}
