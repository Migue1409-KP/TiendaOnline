package co.edu.tiendaonline.service.facade.concrete.cliente;

import java.util.ArrayList;
import java.util.List;

import co.edu.tiendaonline.crosscutting.exception.TiendaOnlineException;
import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.data.dao.daofactory.DAOFactory;
import co.edu.tiendaonline.data.dao.daofactory.enumerator.TipoDAOFactory;
import co.edu.tiendaonline.service.bussineslogic.concrete.cliente.ConsultarClienteUseCase;
import co.edu.tiendaonline.service.bussineslogic.validator.concrete.cliente.ConsultarClienteValidator;
import co.edu.tiendaonline.service.domain.cliente.ClienteDomain;
import co.edu.tiendaonline.service.dto.ClienteDTO;
import co.edu.tiendaonline.service.facade.FacadeFind;
import co.edu.tiendaonline.service.mapper.dto.concrete.ClienteDTOMapper;

public final class ConsultarClienteFacade implements FacadeFind<ClienteDTO>{

	@Override
	public List<ClienteDTO> execute(final ClienteDTO dto) {
		final ClienteDomain domain = ClienteDTOMapper.convertToDomain(dto);
		ConsultarClienteValidator.ejecutar(domain);
		List<ClienteDTO> resultados = new ArrayList<>();
		
		DAOFactory daofactory = DAOFactory.obtenerDAOFactory(TipoDAOFactory.SQLSERVER);
		
		try {
			daofactory.iniciarTransaccion();
			var useCase = new ConsultarClienteUseCase(daofactory);
			resultados = ClienteDTOMapper.convertToListDTO(useCase.execute(domain));
			daofactory.confirmarTransaccion();
		} catch (TiendaOnlineException e) {
			daofactory.cancelarTransaccion();
			throw e;
		} catch (Exception e) {
			daofactory.cancelarTransaccion();
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000131);
			final var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000132);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		} finally {
			daofactory.cerrarConexion();
		}
		return resultados;
	}

}
