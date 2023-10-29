package co.edu.tiendaonline.service.mapper.dto.concrete;

import java.util.ArrayList;
import java.util.List;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.service.domain.cliente.ClienteDomain;
import co.edu.tiendaonline.service.dto.ClienteDTO;
import co.edu.tiendaonline.service.mapper.dto.DTOMapper;

public class ClienteDTOMapper implements DTOMapper<ClienteDTO, ClienteDomain> {
	private static final DTOMapper<ClienteDTO, ClienteDomain> instancia = new ClienteDTOMapper();
	
	private ClienteDTOMapper() {
		super();
	}
	
	@Override
	public ClienteDomain toDomain(ClienteDTO dto) {
		if(UtilObjeto.esNulo(dto)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000128);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		return ClienteDomain.crear(
				dto.getId(),
				TipoIdentificacionDTOMapper.convertToDomain(dto.getTipoIdentificacion()),
				dto.getIdentificacion(),
				NombreCompletoClienteDTOMapper.convertToDomain(dto.getNombreCompleto()),
				CorreoElectronicoClienteDTOMapper.convertToDomain(dto.getCorreoElectronico()),
				NumeroTelefonoMovilClienteDTOMapper.convertToDomain(dto.getNumeroTelefonoMovil()),
				dto.getFechaNacimiento()
				);
	}

	@Override
	public ClienteDTO toDTO(ClienteDomain domain) {
		if(UtilObjeto.esNulo(domain)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000129);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		return ClienteDTO.crear()
				.setId(domain.getId())
				.setTipoIdentificacion(TipoIdentificacionDTOMapper.convertToDTO(domain.getTipoIdentificacion()))
				.setIdentificacion(domain.getIdentificacion())
				.setNombreCompleto(NombreCompletoClienteDTOMapper.convertToDTO(domain.getNombreCompleto()))
				.setCorreoElectronico(CorreoElectronicoClienteDTOMapper.convertToDTO(domain.getCorreoElectronico()))
				.setNumeroTelefonoMovil(NumeroTelefonoMovilClienteDTOMapper.convertToDTO(domain.getNumeroTelefonoMovil()))
				.setFechaNacimiento(domain.getFechaNacimiento());
	}
	
	public static final ClienteDomain convertToDomain(final ClienteDTO dto) {		 
		return instancia.toDomain(dto);
	}
	
	public static final ClienteDTO convertToDTO(final ClienteDomain domain) {
		return instancia.toDTO(domain);
	}
	
	public static final List<ClienteDTO>  convertToListDTO(final List<ClienteDomain> dto){
		List<ClienteDTO> resultados = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			resultados.add(convertToDTO(dto.get(i)));
		}
		
		return resultados;
	}

}
