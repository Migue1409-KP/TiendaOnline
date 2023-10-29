package co.edu.tiendaonline.controller.support.mapper;

import java.util.ArrayList;
import java.util.List;

import co.edu.tiendaonline.controller.support.request.Solicitar;
import co.edu.tiendaonline.service.dto.TipoIdentificacionDTO;

public class TipoIdentificacionResponse {
	public static final Solicitar convertToResponse(TipoIdentificacionDTO dto) {
		return new Solicitar(dto.getId(), dto.getCodigo(), dto.getNombre(), dto.isEstado().isValor());
	}
	
	public static final List<Solicitar> convertListToResponse(List<TipoIdentificacionDTO> dto){
		List<Solicitar> resultados = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			resultados.add(convertToResponse(dto.get(i)));
		}
		
		return resultados;
	}
}
