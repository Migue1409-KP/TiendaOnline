package co.edu.tiendaonline.controller.support.mapper;

import java.util.ArrayList;
import java.util.List;

import co.edu.tiendaonline.controller.support.request.SolicitarTipoIdentificacion;
import co.edu.tiendaonline.service.dto.TipoIdentificacionDTO;

public class TipoIdentificacionResponse {
	public static final SolicitarTipoIdentificacion convertToResponse(TipoIdentificacionDTO dto) {
		return new SolicitarTipoIdentificacion(dto.getId(), dto.getCodigo(), dto.getNombre(), dto.isEstado().isValor());
	}
	
	public static final List<SolicitarTipoIdentificacion> convertListToResponse(List<TipoIdentificacionDTO> dto){
		List<SolicitarTipoIdentificacion> resultados = new ArrayList<>();
		for (int i = 0; i < dto.size(); i++) {
			resultados.add(convertToResponse(dto.get(i)));
		}
		
		return resultados;
	}
}
