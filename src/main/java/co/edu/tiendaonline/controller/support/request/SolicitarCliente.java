package co.edu.tiendaonline.controller.support.request;

import java.sql.Date;
import java.util.UUID;

import co.edu.tiendaonline.crosscutting.util.UtilFecha;
import co.edu.tiendaonline.crosscutting.util.UtilTexto;
import co.edu.tiendaonline.crosscutting.util.UtilUUID;

public class SolicitarCliente {
    private UUID id;
    private SolicitarTipoIdentificacion tipoIdentificacion;
    private String identificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String correoElectronico;
    private Boolean correoElectronicoConfirmado;
    private String numeroTelefonoMovil;
    private Boolean numeroTelefonoMovilConfirmado;
    private Date fechaNacimiento;
	
	public SolicitarCliente() {
		setId(UtilUUID.UUIDDEFECTO);
		setTipoIdentificacion(new SolicitarTipoIdentificacion());
		setIdentificacion(UtilTexto.VACIO);
		setPrimerNombre(UtilTexto.VACIO);
		setSegundoNombre(UtilTexto.VACIO);
		setPrimerApellido(UtilTexto.VACIO);
		setSegundoApellido(UtilTexto.VACIO);
		setCorreoElectronico(UtilTexto.VACIO);
		setCorreoElectronicoConfirmado(false);
		setNumeroTelefonoMovil(UtilTexto.VACIO);
		setNumeroTelefonoMovilConfirmado(false);
		setFechaNacimiento(UtilFecha.FECHADEFECTO);
	}
    
	public SolicitarCliente(final UUID id, final SolicitarTipoIdentificacion tipoIdentificacion, final String identificacion,
			final String primerNombre, final String segundoNombre, final String primerApellido, final String segundoApellido, final String correoElectronico, final Boolean correoElectronicoConfirmado,
			final String numeroTelefonoMovil, final Boolean numeroTelefonoMovilConfirmado, final Date fechaNacimiento) {
		setId(id);
		setTipoIdentificacion(tipoIdentificacion);
		setIdentificacion(identificacion);
		setPrimerNombre(primerNombre);
		setSegundoNombre(segundoNombre);
		setPrimerApellido(primerApellido);
		setSegundoApellido(segundoApellido);
		setCorreoElectronico(correoElectronico);
		setCorreoElectronicoConfirmado(correoElectronicoConfirmado);
		setNumeroTelefonoMovil(numeroTelefonoMovil);
		setNumeroTelefonoMovilConfirmado(numeroTelefonoMovilConfirmado);
		setFechaNacimiento(fechaNacimiento);
	}

	public final UUID getId() {
		return id;
	}
	
	public final SolicitarTipoIdentificacion getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	
	public final String getIdentificacion() {
		return identificacion;
	}
	
	public final String getPrimerNombre() {
		return primerNombre;
	}

	public final String getSegundoNombre() {
		return segundoNombre;
	}

	public final String getPrimerApellido() {
		return primerApellido;
	}

	public final String getSegundoApellido() {
		return segundoApellido;
	}

	public final Boolean isCorreoElectronicoConfirmado() {
		return correoElectronicoConfirmado;
	}

	public final Boolean isNumeroTelefonoMovilConfirmado() {
		return numeroTelefonoMovilConfirmado;
	}

	public final String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public final String getNumeroTelefonoMovil() {
		return numeroTelefonoMovil;
	}
	
	public final Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public final void setId(final UUID id) {
		this.id = id;
	}

	public final void setTipoIdentificacion(final SolicitarTipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public final void setIdentificacion(final String identificacion) {
		this.identificacion = identificacion;
	}

	public final void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public final void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public final void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public final void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public final void setCorreoElectronico(final String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public final void setCorreoElectronicoConfirmado(final Boolean correoElectronicoConfirmado) {
		this.correoElectronicoConfirmado = correoElectronicoConfirmado;
	}

	public final void setNumeroTelefonoMovil(final String numeroTelefonoMovil) {
		this.numeroTelefonoMovil = numeroTelefonoMovil;
	}

	public final void setNumeroTelefonoMovilConfirmado(final Boolean numeroTelefonoMovilConfirmado) {
		this.numeroTelefonoMovilConfirmado = numeroTelefonoMovilConfirmado;
	}

	public final void setFechaNacimiento(final Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}
