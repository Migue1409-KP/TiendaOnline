package co.edu.tiendaonline.service.dto;

import co.edu.tiendaonline.crosscutting.util.UtilTexto;

public class CorreoElectronicoClienteDTO {
	private String correoElectronico;
	private BooleanDTO correoElectronicoConfirmado;
	
	public CorreoElectronicoClienteDTO() {
		setCorreoElectronico(UtilTexto.VACIO);
		setCorreoElectronicoConfirmado(BooleanDTO.crear());
	}
	
	public CorreoElectronicoClienteDTO(final String correoElectronico, final BooleanDTO correoElectronicoConfirmado) {
		setCorreoElectronico(correoElectronico);
		setCorreoElectronicoConfirmado(BooleanDTO.crear().setValor(correoElectronicoConfirmado.isValor()).setValorDefecto(false));
	}

	public static final CorreoElectronicoClienteDTO crear() {
		return new CorreoElectronicoClienteDTO();
	}
	
	public final String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public final BooleanDTO isCorreoElectronicoConfirmado() {
		return correoElectronicoConfirmado;
	}

	public final CorreoElectronicoClienteDTO setCorreoElectronico(final String correoElectronico) {
		this.correoElectronico = UtilTexto.obtenerValorDefecto(UtilTexto.aplicarTrim(correoElectronico), UtilTexto.VACIO);
		return this;
	}

	public final CorreoElectronicoClienteDTO setCorreoElectronicoConfirmado(final BooleanDTO correoElectronicoConfirmado) {
		this.correoElectronicoConfirmado = correoElectronicoConfirmado.isValorDefecto() ? BooleanDTO.crear()
				: BooleanDTO.crear().setValor(correoElectronicoConfirmado.isValor()).setValorDefecto(false);
		return this;
	}
}
