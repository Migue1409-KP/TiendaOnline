package co.edu.tiendaonline.service.domain.numerotelefonomovilcliente;

import co.edu.tiendaonline.service.domain.support.BooleanDomain;

public class NumeroTelefonoMovilClienteDomain {
	private String numeroTelefonoMovil;
	private BooleanDomain numeroTelefonoMovilConfirmado;
	
	private NumeroTelefonoMovilClienteDomain(final String numeroTelefonoMovil, final BooleanDomain numeroTelefonoMovilConfirmado) {
		setNumeroTelefonoMovil(numeroTelefonoMovil);
		setNumeroTelefonoMovilConfirmado(numeroTelefonoMovilConfirmado);
	}

	public static final NumeroTelefonoMovilClienteDomain crear(final String numeroTelefonoMovil, final BooleanDomain numeroTelefonoMovilConfirmado) {
		return new NumeroTelefonoMovilClienteDomain(numeroTelefonoMovil, numeroTelefonoMovilConfirmado);
	}

	private final void setNumeroTelefonoMovil(final String numeroTelefonoMovil) {
		this.numeroTelefonoMovil = numeroTelefonoMovil;
	}

	private final void setNumeroTelefonoMovilConfirmado(final BooleanDomain numeroTelefonoMovilConfirmado) {
		this.numeroTelefonoMovilConfirmado = numeroTelefonoMovilConfirmado;
	}

	public final String getNumeroTelefonoMovil() {
		return numeroTelefonoMovil;
	}

	public final BooleanDomain isNumeroTelefonoMovilConfirmado() {
		return numeroTelefonoMovilConfirmado;
	}
}