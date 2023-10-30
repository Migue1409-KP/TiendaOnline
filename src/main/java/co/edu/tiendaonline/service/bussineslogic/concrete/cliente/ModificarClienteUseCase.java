package co.edu.tiendaonline.service.bussineslogic.concrete.cliente;

import java.util.Optional;
import java.util.UUID;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.data.dao.ClienteDAO;
import co.edu.tiendaonline.data.dao.TipoIdentificacionDAO;
import co.edu.tiendaonline.data.dao.daofactory.DAOFactory;
import co.edu.tiendaonline.data.entity.TipoIdentificacionEntity;
import co.edu.tiendaonline.service.bussineslogic.UseCase;
import co.edu.tiendaonline.service.domain.cliente.ClienteDomain;
import co.edu.tiendaonline.service.domain.correoelectronicocliente.CorreoElectronicoClienteDomain;
import co.edu.tiendaonline.service.domain.nombrecompletocliente.NombreCompletoClienteDomain;
import co.edu.tiendaonline.service.domain.numerotelefonomovilcliente.NumeroTelefonoMovilClienteDomain;
import co.edu.tiendaonline.service.dto.CorreoElectronicoClienteDTO;
import co.edu.tiendaonline.service.dto.NombreCompletoClienteDTO;
import co.edu.tiendaonline.service.dto.NumeroTelefonoMovilClienteDTO;
import co.edu.tiendaonline.service.dto.TipoIdentificacionDTO;
import co.edu.tiendaonline.service.mapper.dto.concrete.CorreoElectronicoClienteDTOMapper;
import co.edu.tiendaonline.service.mapper.dto.concrete.NombreCompletoClienteDTOMapper;
import co.edu.tiendaonline.service.mapper.dto.concrete.NumeroTelefonoMovilClienteDTOMapper;
import co.edu.tiendaonline.service.mapper.dto.concrete.TipoIdentificacionDTOMapper;
import co.edu.tiendaonline.service.mapper.entity.concrete.ClienteEntityMapper;

public class ModificarClienteUseCase implements UseCase<ClienteDomain> {

	private DAOFactory factoria;
	
	public ModificarClienteUseCase(DAOFactory factoria) {
		setFactoria(factoria);
	}
	
	@Override
	public void execute(ClienteDomain domain) {
		validarExistenciaRegistro(domain.getId());
		validarNoExistenciaMismoNombre(domain.getId(), domain.getNombreCompleto());
		validarNoExistenciaCorreoElectronico(domain.getId(), domain.getCorreoElectronico());
		validarNoExistenciaNumeroTelefonoMovil(domain.getId(), domain.getNumeroTelefonoMovil());
		validarNoExistenciaIdentificacion(domain);
		validarExistenciaTipoIdentificacion(domain.getTipoIdentificacion().getId());
		modificar(domain);
	}
	
	private final void validarExistenciaRegistro(final UUID id) {
		final var resultados = getClienteDAO().consultarPorId(id);
		
		if(resultados.isEmpty()) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000114);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
	
	private final void validarNoExistenciaMismoNombre(final UUID id, final NombreCompletoClienteDomain nombre) {
		final var domain = ClienteDomain.crear(null,
				TipoIdentificacionDTOMapper.convertToDomain(TipoIdentificacionDTO.crear()), null, nombre,
				CorreoElectronicoClienteDTOMapper.convertToDomain(CorreoElectronicoClienteDTO.crear()),
				NumeroTelefonoMovilClienteDTOMapper.convertToDomain(NumeroTelefonoMovilClienteDTO.crear()), null);
		final var entity = ClienteEntityMapper.convertToEntity(domain);
		final var resultados = getClienteDAO().consultar(entity);
		
		if(!resultados.isEmpty()) {
			for (int i = 0; i < resultados.size(); i++) {
				if(!resultados.get(i).getId().equals(id)) {
					final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000113);
					throw ServiceTiendaOnlineException.crear(mensajeUsuario);	
				}
			}
		}
	}
	
	private final void validarNoExistenciaCorreoElectronico(final UUID id, final CorreoElectronicoClienteDomain correoElectronico) {
		final var domain = ClienteDomain.crear(null,
				TipoIdentificacionDTOMapper.convertToDomain(TipoIdentificacionDTO.crear()), null,
				NombreCompletoClienteDTOMapper.convertToDomain(NombreCompletoClienteDTO.crear()), correoElectronico,
				NumeroTelefonoMovilClienteDTOMapper.convertToDomain(NumeroTelefonoMovilClienteDTO.crear()), null);
		final var entity = ClienteEntityMapper.convertToEntity(domain);
		final var resultados = getClienteDAO().consultar(entity);
		
		if(!resultados.isEmpty()) {
			for (int i = 0; i < resultados.size(); i++) {
				if(!resultados.get(i).getId().equals(id)) {
					final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000112);
					throw ServiceTiendaOnlineException.crear(mensajeUsuario);	
				}
			}
		}
	}
	
	private final void validarNoExistenciaNumeroTelefonoMovil(final UUID id, final NumeroTelefonoMovilClienteDomain numeroTelefono) {
		final var domain = ClienteDomain.crear(null,
				TipoIdentificacionDTOMapper.convertToDomain(TipoIdentificacionDTO.crear()), null,
				NombreCompletoClienteDTOMapper.convertToDomain(NombreCompletoClienteDTO.crear()),
				CorreoElectronicoClienteDTOMapper.convertToDomain(CorreoElectronicoClienteDTO.crear()), numeroTelefono,
				null);
		final var entity = ClienteEntityMapper.convertToEntity(domain);
		final var resultados = getClienteDAO().consultar(entity);
		
		if(!resultados.isEmpty()) {
			for (int i = 0; i < resultados.size(); i++) {
				if(!resultados.get(i).getId().equals(id)) {
					final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000111);
					throw ServiceTiendaOnlineException.crear(mensajeUsuario);
				}
			}
		}
	}
	
	private final void validarNoExistenciaIdentificacion(final ClienteDomain cliente) {
		final var domain = ClienteDomain.crear(null, cliente.getTipoIdentificacion(), cliente.getIdentificacion(),
				NombreCompletoClienteDTOMapper.convertToDomain(NombreCompletoClienteDTO.crear()),
				CorreoElectronicoClienteDTOMapper.convertToDomain(CorreoElectronicoClienteDTO.crear()),
				NumeroTelefonoMovilClienteDTOMapper.convertToDomain(NumeroTelefonoMovilClienteDTO.crear()), null);
		final var entity = ClienteEntityMapper.convertToEntity(domain);
		final var resultados = getClienteDAO().consultar(entity);
		
		if(!resultados.isEmpty()) {
			for (int i = 0; i < resultados.size(); i++) {
				if(!resultados.get(i).getId().equals(cliente.getId())) {
					final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000110);
					throw ServiceTiendaOnlineException.crear(mensajeUsuario);
				}
			}
		}
	}
	
	private final void validarExistenciaTipoIdentificacion(final UUID tipoIdentificacion) {
		Optional<TipoIdentificacionEntity> optional;
		optional = getTipoIdentificacionDAO().consultarPorId(tipoIdentificacion);
		
		if(!optional.isPresent()) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000185);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario);
		}
	}
	
	private void modificar(final ClienteDomain domain) {
		getClienteDAO().modificar(ClienteEntityMapper.convertToEntity(domain));
	}
	
	private final DAOFactory getFactoria() {
		return factoria;
	}
	
	private final void setFactoria(final DAOFactory factoria) {
		if(UtilObjeto.esNulo(factoria)) {
			final var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000108);
			final var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000109);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		this.factoria = factoria;
	}
	
	private final ClienteDAO getClienteDAO() {
		return getFactoria().obtenerClienteDAO();
	}

	private final TipoIdentificacionDAO getTipoIdentificacionDAO() {
		return getFactoria().obtenerTipoIdentificacionDAO();
	}
}
