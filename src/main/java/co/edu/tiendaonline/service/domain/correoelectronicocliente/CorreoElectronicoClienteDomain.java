package co.edu.tiendaonline.service.domain.correoelectronicocliente;

import co.edu.tiendaonline.service.domain.support.BooleanDomain;

public class CorreoElectronicoClienteDomain {
	private String correoElectronico;
	private BooleanDomain correoElectronicoConfirmado;
	
	private CorreoElectronicoClienteDomain(final String correoElectronico, final BooleanDomain correoElectronicoConfirmado) {
		setCorreoElectronico(correoElectronico);
		setCorreoElectronicoConfirmado(correoElectronicoConfirmado);
	}
	
	public static final CorreoElectronicoClienteDomain crear(final String correoElectronico, final BooleanDomain correoElectronicoConfirmado) {
		return new CorreoElectronicoClienteDomain(correoElectronico, correoElectronicoConfirmado);
	}

	private final void setCorreoElectronico(final String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	private final void setCorreoElectronicoConfirmado(final BooleanDomain correoElectronicoConfirmado) {
		this.correoElectronicoConfirmado = correoElectronicoConfirmado;
	}

	public final String getCorreoElectronico() {
		return correoElectronico;
	}

	public final BooleanDomain isCorreoElectronicoConfirmado() {
		return correoElectronicoConfirmado;
	}
}