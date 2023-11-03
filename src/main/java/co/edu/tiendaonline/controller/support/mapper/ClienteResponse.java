package co.edu.tiendaonline.controller.support.mapper;

import java.util.ArrayList;
import java.util.List;

import co.edu.tiendaonline.controller.support.request.SolicitarCliente;
import co.edu.tiendaonline.controller.support.request.SolicitarTipoIdentificacion;
import co.edu.tiendaonline.service.dto.ClienteDTO;

public class ClienteResponse {
	private ClienteResponse() {
		super();
	}
	
	public static final SolicitarCliente convertToResponse(ClienteDTO dto) {
		return new SolicitarCliente(dto.getId(),
				new SolicitarTipoIdentificacion(dto.getTipoIdentificacion().getId(),
						dto.getTipoIdentificacion().getCodigo(), dto.getTipoIdentificacion().getNombre(),
						dto.getTipoIdentificacion().isEstado().isValor()),
				dto.getIdentificacion(), dto.getNombreCompleto().getPrimerNombre(),
				dto.getNombreCompleto().getSegundoNombre(), dto.getNombreCompleto().getPrimerApellido(),
				dto.getNombreCompleto().getSegundoApellido(), dto.getCorreoElectronico().getCorreoElectronico(),
				dto.getCorreoElectronico().isCorreoElectronicoConfirmado().isValor(),
				dto.getNumeroTelefonoMovil().getNumeroTelefonoMovil(),
				dto.getNumeroTelefonoMovil().isNumeroTelefonoMovilConfirmado().isValor(), dto.getFechaNacimiento());
	}
	
	public static final List<SolicitarCliente> convertListToResponse(List<ClienteDTO> dto){
		List<SolicitarCliente> resultados = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			resultados.add(convertToResponse(dto.get(i)));
		}
		
		return resultados;
	}
}
