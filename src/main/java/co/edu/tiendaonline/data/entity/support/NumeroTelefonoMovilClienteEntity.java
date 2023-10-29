package co.edu.tiendaonline.data.entity.support;

public class NumeroTelefonoMovilClienteEntity {
	private String numeroTelefonoMovil;
	private BooleanEntity numeroTelefonoMovilConfirmado;
	
	private NumeroTelefonoMovilClienteEntity(final String numeroTelefonoMovil, final BooleanEntity numeroTelefonoMovilConfirmado) {
		setNumeroTelefonoMovil(numeroTelefonoMovil);
		setNumeroTelefonoMovilConfirmado(numeroTelefonoMovilConfirmado);
	}

	public static final NumeroTelefonoMovilClienteEntity crear(final String numeroTelefonoMovil, final BooleanEntity numeroTelefonoMovilConfirmado) {
		return new NumeroTelefonoMovilClienteEntity(numeroTelefonoMovil, numeroTelefonoMovilConfirmado);
	}

	private final void setNumeroTelefonoMovil(final String numeroTelefonoMovil) {
		this.numeroTelefonoMovil = numeroTelefonoMovil;
	}

	private final void setNumeroTelefonoMovilConfirmado(final BooleanEntity numeroTelefonoMovilConfirmado) {
		this.numeroTelefonoMovilConfirmado = numeroTelefonoMovilConfirmado;
	}

	public final String getNumeroTelefonoMovil() {
		return numeroTelefonoMovil;
	}

	public final BooleanEntity isNumeroTelefonoMovilConfirmado() {
		return numeroTelefonoMovilConfirmado;
	}
}