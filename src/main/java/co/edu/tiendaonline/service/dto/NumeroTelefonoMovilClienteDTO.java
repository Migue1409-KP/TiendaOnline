package co.edu.tiendaonline.service.dto;

import co.edu.tiendaonline.crosscutting.util.UtilTexto;

public class NumeroTelefonoMovilClienteDTO {
	private String numeroTelefonoMovil;
	private BooleanDTO numeroTelefonoMovilConfirmado;
	
	public NumeroTelefonoMovilClienteDTO() {
		setNumeroTelefonoMovil(UtilTexto.VACIO);
		setNumeroTelefonoMovilConfirmado(new BooleanDTO());
	}
	
	public NumeroTelefonoMovilClienteDTO(final String numeroTelefonoMovil, final BooleanDTO numeroTelefonoMovilConfirmado) {
		setNumeroTelefonoMovil(numeroTelefonoMovil);
		setNumeroTelefonoMovilConfirmado(numeroTelefonoMovilConfirmado);
	}
	
	public static final NumeroTelefonoMovilClienteDTO crear() {
		return new NumeroTelefonoMovilClienteDTO();
	}

	public final String getNumeroTelefonoMovil() {
		return numeroTelefonoMovil;
	}
	
	public final BooleanDTO isNumeroTelefonoMovilConfirmado() {
		return numeroTelefonoMovilConfirmado;
	}

	public final NumeroTelefonoMovilClienteDTO setNumeroTelefonoMovil(final String numeroTelefonoMovil) {
		this.numeroTelefonoMovil = UtilTexto.obtenerValorDefecto(numeroTelefonoMovil, UtilTexto.VACIO);
		return this;
	}

	public final NumeroTelefonoMovilClienteDTO setNumeroTelefonoMovilConfirmado(final BooleanDTO numeroTelefonoMovilConfirmado) {
		this.numeroTelefonoMovilConfirmado = numeroTelefonoMovilConfirmado.isValorDefecto() ? BooleanDTO.crear()
				: BooleanDTO.crear().setValor(numeroTelefonoMovilConfirmado.isValor()).setValorDefecto(false);
		return this;
	}
}
