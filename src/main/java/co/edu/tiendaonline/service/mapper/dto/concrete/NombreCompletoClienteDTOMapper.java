package co.edu.tiendaonline.service.mapper.dto.concrete;

import java.util.ArrayList;
import java.util.List;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.service.domain.nombrecompletocliente.NombreCompletoClienteDomain;
import co.edu.tiendaonline.service.dto.NombreCompletoClienteDTO;
import co.edu.tiendaonline.service.mapper.dto.DTOMapper;

public class NombreCompletoClienteDTOMapper implements DTOMapper<NombreCompletoClienteDTO, NombreCompletoClienteDomain> {
	private static final DTOMapper<NombreCompletoClienteDTO, NombreCompletoClienteDomain> instancia = new NombreCompletoClienteDTOMapper();
	
	private NombreCompletoClienteDTOMapper() {
		super();
	}
	
	@Override
	public NombreCompletoClienteDomain toDomain(NombreCompletoClienteDTO dto) {
		if(UtilObjeto.esNulo(dto)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000140);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		return NombreCompletoClienteDomain.crear(dto.getPrimerNombre(), dto.getSegundoNombre(), dto.getPrimerApellido(), dto.getSegundoApellido());
	}

	@Override
	public NombreCompletoClienteDTO toDTO(NombreCompletoClienteDomain domain) {
		if(UtilObjeto.esNulo(domain)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000141);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		return NombreCompletoClienteDTO.crear()
				.setPrimerNombre(domain.getPrimerNombre())
				.setSegundoNombre(domain.getSegundoNombre())
				.setPrimerApellido(domain.getPrimerApellido())
				.setSegundoApellido(domain.getSegundoApellido());
	}
	
	public static final NombreCompletoClienteDomain convertToDomain(final NombreCompletoClienteDTO dto) {		 
		return instancia.toDomain(dto);
	}
	
	public static final NombreCompletoClienteDTO convertToDTO(final NombreCompletoClienteDomain domain) {
		return instancia.toDTO(domain);
	}
	
	public static final List<NombreCompletoClienteDTO>  convertToListDTO(final List<NombreCompletoClienteDomain> dto){
		List<NombreCompletoClienteDTO> resultados = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			resultados.add(convertToDTO(dto.get(i)));
		}
		
		return resultados;
	}

}
