package co.edu.tiendaonline.data.entity;

import java.util.UUID;

import co.edu.tiendaonline.data.entity.support.BooleanEntity;

public final class TipoIdentificacionEntity {
	private UUID id;
	private String codigo;
	private String nombre;
	private BooleanEntity estado;
	
	private TipoIdentificacionEntity(final UUID id, final String codigo, final String nombre, final BooleanEntity estado) {
		setId(id);
		setCodigo(codigo);
		setNombre(nombre);
		setEstado(estado);
	}
	
	public static final TipoIdentificacionEntity crear(final UUID id, final String codigo, final String nombre, final BooleanEntity estado) {
		return new TipoIdentificacionEntity(id,codigo,nombre,estado);
	}

	private final void setId(final UUID id) {
		this.id = id;
	}

	private final void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	private final void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	private final void setEstado(final BooleanEntity estado) {
		this.estado = estado;
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

	public final BooleanEntity isEstado() {
		return estado;
	}
	
	
}
