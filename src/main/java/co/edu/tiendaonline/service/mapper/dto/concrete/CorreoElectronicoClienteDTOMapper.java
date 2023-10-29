package co.edu.tiendaonline.service.mapper.dto.concrete;

import java.util.ArrayList;
import java.util.List;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.service.domain.correoelectronicocliente.CorreoElectronicoClienteDomain;
import co.edu.tiendaonline.service.dto.CorreoElectronicoClienteDTO;
import co.edu.tiendaonline.service.mapper.dto.DTOMapper;

public class CorreoElectronicoClienteDTOMapper implements DTOMapper<CorreoElectronicoClienteDTO, CorreoElectronicoClienteDomain> {
	private static final DTOMapper<CorreoElectronicoClienteDTO, CorreoElectronicoClienteDomain> instancia = new CorreoElectronicoClienteDTOMapper();
	
	private CorreoElectronicoClienteDTOMapper() {
		super();
	}
	
	@Override
	public CorreoElectronicoClienteDomain toDomain(CorreoElectronicoClienteDTO dto) {
		if(UtilObjeto.esNulo(dto)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000142);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		return CorreoElectronicoClienteDomain.crear(dto.getCorreoElectronico(), BooleanDTOMapper.convertToDomain(dto.isCorreoElectronicoConfirmado()));
	}

	@Override
	public CorreoElectronicoClienteDTO toDTO(CorreoElectronicoClienteDomain domain) {
		if(UtilObjeto.esNulo(domain)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000143);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		return CorreoElectronicoClienteDTO.crear()
				.setCorreoElectronico(domain.getCorreoElectronico())
				.setCorreoElectronicoConfirmado(BooleanDTOMapper.convertToDTO(domain.isCorreoElectronicoConfirmado()));
	}
	
	public static final CorreoElectronicoClienteDomain convertToDomain(final CorreoElectronicoClienteDTO dto) {		 
		return instancia.toDomain(dto);
	}
	
	public static final CorreoElectronicoClienteDTO convertToDTO(final CorreoElectronicoClienteDomain domain) {
		return instancia.toDTO(domain);
	}
	
	public static final List<CorreoElectronicoClienteDTO>  convertToListDTO(final List<CorreoElectronicoClienteDomain> dto){
		List<CorreoElectronicoClienteDTO> resultados = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			resultados.add(convertToDTO(dto.get(i)));
		}
		
		return resultados;
	}

}
