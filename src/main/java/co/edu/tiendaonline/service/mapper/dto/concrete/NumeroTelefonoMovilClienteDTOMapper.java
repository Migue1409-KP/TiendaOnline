package co.edu.tiendaonline.service.mapper.dto.concrete;

import java.util.ArrayList;
import java.util.List;

import co.edu.tiendaonline.crosscutting.exception.concrete.ServiceTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.service.domain.numerotelefonomovilcliente.NumeroTelefonoMovilClienteDomain;
import co.edu.tiendaonline.service.dto.NumeroTelefonoMovilClienteDTO;
import co.edu.tiendaonline.service.mapper.dto.DTOMapper;

public class NumeroTelefonoMovilClienteDTOMapper implements DTOMapper<NumeroTelefonoMovilClienteDTO, NumeroTelefonoMovilClienteDomain> {
	private static final DTOMapper<NumeroTelefonoMovilClienteDTO, NumeroTelefonoMovilClienteDomain> instancia = new NumeroTelefonoMovilClienteDTOMapper();
	
	private NumeroTelefonoMovilClienteDTOMapper() {
		super();
	}
	
	@Override
	public NumeroTelefonoMovilClienteDomain toDomain(NumeroTelefonoMovilClienteDTO dto) {
		if(UtilObjeto.esNulo(dto)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000144);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		return NumeroTelefonoMovilClienteDomain.crear(dto.getNumeroTelefonoMovil(), dto.isNumeroTelefonoMovilConfirmado());
	}

	@Override
	public NumeroTelefonoMovilClienteDTO toDTO(NumeroTelefonoMovilClienteDomain domain) {
		if(UtilObjeto.esNulo(domain)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000145);
			throw ServiceTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
		
		return NumeroTelefonoMovilClienteDTO.crear()
				.setNumeroTelefonoMovil(domain.getNumeroTelefonoMovil())
				.setNumeroTelefonoMovilConfirmado(domain.isNumeroTelefonoMovilConfirmado());
	}
	
	public static final NumeroTelefonoMovilClienteDomain convertToDomain(final NumeroTelefonoMovilClienteDTO dto) {		 
		return instancia.toDomain(dto);
	}
	
	public static final NumeroTelefonoMovilClienteDTO convertToDTO(final NumeroTelefonoMovilClienteDomain domain) {
		return instancia.toDTO(domain);
	}
	
	public static final List<NumeroTelefonoMovilClienteDTO>  convertToListDTO(final List<NumeroTelefonoMovilClienteDomain> dto){
		List<NumeroTelefonoMovilClienteDTO> resultados = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			resultados.add(convertToDTO(dto.get(i)));
		}
		
		return resultados;
	}

}
