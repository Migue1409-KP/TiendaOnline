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
	
	private TipoIdentificacionDomain(final UUID id, final String codigo, final String nombre, final boolean estado) {
		setId(id);
		setCodigo(codigo);
		setNombre(nombre);
		setEstado(estado);
	}
	
	public static final TipoIdentificacionDomain crear(final UUID id, final String codigo, final String nombre, final boolean estado) {
		return new TipoIdentificacionDomain(id,codigo,nombre,estado);
	}

	private final void setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id, UtilUUID.obtenerValorDefecto(id, id));
	}

	private final void setCodigo(final String codigo) {
		this.codigo = UtilTexto.obtenerValorDefecto(UtilTexto.aplicarTrim(codigo), UtilTexto.VACIO);
	}

	private final void setNombre(final String nombre) {
		this.nombre = UtilTexto.obtenerValorDefecto(UtilTexto.aplicarTrim(nombre), UtilTexto.VACIO);
	}

	private final void setEstado(final boolean estado) {
		this.estado = UtilObjeto.obtenerValorDefecto(estado, null);
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
