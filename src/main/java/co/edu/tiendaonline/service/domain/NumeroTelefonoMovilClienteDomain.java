package co.edu.tiendaonline.service.domain;

import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.crosscutting.util.UtilTexto;

public class NumeroTelefonoMovilClienteDomain {
	private String numeroTelefonoMovil;
	private boolean numeroTelefonoMovilConfirmado;
	
	public NumeroTelefonoMovilClienteDomain() {
		setNumeroTelefonoMovil(UtilTexto.VACIO);
		setNumeroTelefonoMovilConfirmado(false);
	}
	
	public NumeroTelefonoMovilClienteDomain(final String numeroTelefonoMovil, final boolean numeroTelefonoMovilConfirmado) {
		setNumeroTelefonoMovil(numeroTelefonoMovil);
		setNumeroTelefonoMovilConfirmado(numeroTelefonoMovilConfirmado);
	}

	public static final NumeroTelefonoMovilClienteDomain crear() {
		return new NumeroTelefonoMovilClienteDomain();
	}

	private final NumeroTelefonoMovilClienteDomain setNumeroTelefonoMovil(final String numeroTelefonoMovil) {
		this.numeroTelefonoMovil = UtilTexto.obtenerValorDefecto(numeroTelefonoMovil, UtilTexto.VACIO);
		return this;
	}

	private final NumeroTelefonoMovilClienteDomain setNumeroTelefonoMovilConfirmado(final boolean numeroTelefonoMovilConfirmado) {
		this.numeroTelefonoMovilConfirmado = UtilObjeto.obtenerValorDefecto(numeroTelefonoMovilConfirmado, false);
		return this;
	}

	public final String getNumeroTelefonoMovil() {
		return numeroTelefonoMovil;
	}

	public final boolean isNumeroTelefonoMovilConfirmado() {
		return numeroTelefonoMovilConfirmado;
	}
}