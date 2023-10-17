package co.edu.tiendaonline.service.domain;

import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.crosscutting.util.UtilTexto;

public class CorreoElectronicoClienteDomain {
	private String correoElectronico;
	private boolean correoElectronicoConfirmado;
	
	public CorreoElectronicoClienteDomain() {
		setCorreoElectronico(UtilTexto.VACIO);
		setCorreoElectronicoConfirmado(false);
	}
	
	public CorreoElectronicoClienteDomain(final String correoElectronico, final boolean correoElectronicoConfirmado) {
		setCorreoElectronico(correoElectronico);
		setCorreoElectronicoConfirmado(correoElectronicoConfirmado);
	}
	
	public static final CorreoElectronicoClienteDomain crear() {
		return new CorreoElectronicoClienteDomain();
	}

	private final CorreoElectronicoClienteDomain setCorreoElectronico(final String correoElectronico) {
		this.correoElectronico = UtilTexto.obtenerValorDefecto(UtilTexto.aplicarTrim(correoElectronico), "");
		return this;
	}

	private final CorreoElectronicoClienteDomain setCorreoElectronicoConfirmado(final boolean correoElectronicoConfirmado) {
		this.correoElectronicoConfirmado = UtilObjeto.obtenerValorDefecto(correoElectronicoConfirmado, false);
		return this;
	}

	public final String getCorreoElectronico() {
		return correoElectronico;
	}

	public final boolean isCorreoElectronicoConfirmado() {
		return correoElectronicoConfirmado;
	}
}