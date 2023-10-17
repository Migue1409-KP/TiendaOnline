package co.edu.tiendaonline.service.domain;

import java.sql.Date;
import java.util.UUID;

import co.edu.tiendaonline.crosscutting.util.UtilFecha;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.crosscutting.util.UtilTexto;
import co.edu.tiendaonline.crosscutting.util.UtilUUID;

public class ClienteDomain {
	private UUID id;
	private TipoIdentificacionDomain tipoIdentificacion;
	private String identificacion;
	private NombreCompletoClienteDomain nombreCompleto;
	private CorreoElectronicoClienteDomain correoElectronico;
	private NumeroTelefonoMovilClienteDomain numeroTelefonoMovil;
	private Date fechaNacimiento;
	
	private ClienteDomain() {
		setId(UtilUUID.UUIDDEFECTO);
		setTipoIdentificacion(new TipoIdentificacionDomain());
		setIdentificacion(UtilTexto.VACIO);
		setNombreCompleto(new NombreCompletoClienteDomain());
		setCorreoElectronico(new CorreoElectronicoClienteDomain());
		setNumeroTelefonoMovil(new NumeroTelefonoMovilClienteDomain());
		setFechaNacimiento(UtilFecha.FECHADEFECTO);
	}
	
	private ClienteDomain(final UUID id, final TipoIdentificacionDomain tipoIdentificacion, final String identificacion,
			final NombreCompletoClienteDomain nombreCompleto, final CorreoElectronicoClienteDomain correoElectronico,
			final NumeroTelefonoMovilClienteDomain numeroTelefonoMovil, final Date fechaNacimiento) {
		setId(id);
		setTipoIdentificacion(tipoIdentificacion);
		setIdentificacion(identificacion);
		setNombreCompleto(nombreCompleto);
		setCorreoElectronico(correoElectronico);
		setNumeroTelefonoMovil(numeroTelefonoMovil);
		setFechaNacimiento(fechaNacimiento);
	}
	
	public static final ClienteDomain crear() {
		return new ClienteDomain();
	}

	private final ClienteDomain setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id, UtilUUID.obtenerValorDefecto(id, UtilUUID.UUIDDEFECTO));
		return this;
	}

	private final ClienteDomain setTipoIdentificacion(final TipoIdentificacionDomain tipoIdentificacion) {
		this.tipoIdentificacion = UtilObjeto.obtenerValorDefecto(tipoIdentificacion, new TipoIdentificacionDomain());
		return this;
	}

	private final ClienteDomain setIdentificacion(final String identificacion) {
		this.identificacion = UtilTexto.obtenerValorDefecto(identificacion, UtilTexto.VACIO);
		return this;
	}

	private final ClienteDomain setNombreCompleto(final NombreCompletoClienteDomain nombreCompleto) {
		this.nombreCompleto = UtilObjeto.obtenerValorDefecto(nombreCompleto, new NombreCompletoClienteDomain());
		return this;
	}

	private final ClienteDomain setCorreoElectronico(final CorreoElectronicoClienteDomain correoElectronico) {
		this.correoElectronico = UtilObjeto.obtenerValorDefecto(correoElectronico, new CorreoElectronicoClienteDomain());
		return this;
	}

	private final ClienteDomain setNumeroTelefonoMovil(final NumeroTelefonoMovilClienteDomain numeroTelefonoMovil) {
		this.numeroTelefonoMovil = UtilObjeto.obtenerValorDefecto(numeroTelefonoMovil, new NumeroTelefonoMovilClienteDomain());
		return this;
	}

	private final ClienteDomain setFechaNacimiento(final Date fechaNacimiento) {
		this.fechaNacimiento = UtilFecha.obtenerValorDefecto(fechaNacimiento, UtilFecha.FECHADEFECTO);
		return this;
	}

	public final UUID getId() {
		return id;
	}

	public final TipoIdentificacionDomain getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public final String getIdentificacion() {
		return identificacion;
	}

	public final NombreCompletoClienteDomain getNombreCompleto() {
		return nombreCompleto;
	}

	public final CorreoElectronicoClienteDomain getCorreoElectronico() {
		return correoElectronico;
	}

	public final NumeroTelefonoMovilClienteDomain getNumeroTelefonoMovil() {
		return numeroTelefonoMovil;
	}

	public final Date getFechaNacimiento() {
		return fechaNacimiento;
	}
}
