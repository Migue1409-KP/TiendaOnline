package co.edu.tiendaonline.service.domain;

import java.util.UUID;

import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.crosscutting.util.UtilTexto;
import co.edu.tiendaonline.crosscutting.util.UtilUUID;

public final class TipoIdentificacionDomain {
	private UUID id;
	private String codigo;
	private String nombre;
	private boolean estado;
	
	public TipoIdentificacionDomain() {
		setId(UtilUUID.UUIDDEFECTO);
		setCodigo(UtilTexto.VACIO);
		setNombre(UtilTexto.VACIO);
		setEstado(false);
	}
	
	public TipoIdentificacionDomain(final UUID id, final String codigo, final String nombre, final boolean estado) {
		setId(id);
		setCodigo(codigo);
		setNombre(nombre);
		setEstado(estado);
	}
	
	public static final TipoIdentificacionDomain crear(final UUID id, final String codigo, final String nombre, final boolean estado) {
		return new TipoIdentificacionDomain(id,codigo,nombre,estado);
	}

	private final TipoIdentificacionDomain setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id, UtilUUID.obtenerValorDefecto(id, UtilUUID.UUIDDEFECTO));
		return this;
	}

	private final TipoIdentificacionDomain setCodigo(final String codigo) {
		this.codigo = UtilTexto.obtenerValorDefecto(UtilTexto.aplicarTrim(codigo), UtilTexto.VACIO);
		return this;
	}

	private final TipoIdentificacionDomain setNombre(final String nombre) {
		this.nombre = UtilTexto.obtenerValorDefecto(UtilTexto.aplicarTrim(nombre), UtilTexto.VACIO);
		return this;
	}

	private final TipoIdentificacionDomain setEstado(final boolean estado) {
		this.estado = UtilObjeto.obtenerValorDefecto(estado, false);
		return this;
	}

	public final UUID getId() {
		return id;
	}

	public final String getCodigo() {
		return codigo;
	}

	public final String getNombre() {
		return nombre;
	}

	public final boolean isEstado() {
		return estado;
	}
	
	
}
