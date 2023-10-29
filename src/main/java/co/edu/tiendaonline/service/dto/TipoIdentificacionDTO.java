package co.edu.tiendaonline.service.dto;

import java.util.UUID;

import co.edu.tiendaonline.crosscutting.util.UtilTexto;
import co.edu.tiendaonline.crosscutting.util.UtilUUID;

public class TipoIdentificacionDTO {

	private UUID id;
	private String codigo;
	private String nombre;
	private BooleanDTO estado;
	
	public TipoIdentificacionDTO() {
		setId(UtilUUID.UUIDDEFECTO);
		setCodigo(UtilTexto.VACIO);
		setNombre(UtilTexto.VACIO);
		setEstado(new BooleanDTO());
	}
	
	public TipoIdentificacionDTO(final UUID id, final String codigo, final String nombre, final boolean estado) {
		setId(id);
		setCodigo(codigo);
		setNombre(nombre);
		setEstado(BooleanDTO.crear().setValor(estado).setValorDefecto(false));
	}
	
	public static final TipoIdentificacionDTO crear() {
		return new TipoIdentificacionDTO();
	}

	public final UUID getId() {
		return id;
	}
	
	public final TipoIdentificacionDTO setId(final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id, UtilUUID.obtenerValorDefecto(id, UtilUUID.UUIDDEFECTO));
		return this;
	}
	
	public final String getCodigo() {
		return codigo;
	}
	
	public final TipoIdentificacionDTO setCodigo(final String codigo) {
		this.codigo = UtilTexto.obtenerValorDefecto(UtilTexto.aplicarTrim(codigo), UtilTexto.VACIO);
		return this;
	}
	
	public final String getNombre() {
		return nombre;
	}
	
	public final TipoIdentificacionDTO setNombre(final String nombre) {
		this.nombre = UtilTexto.obtenerValorDefecto(UtilTexto.aplicarTrim(nombre), UtilTexto.VACIO);
		return this;
	}
	
	public final BooleanDTO isEstado() {
		return estado;
	}
	
	public final TipoIdentificacionDTO setEstado(final BooleanDTO estado) {
		this.estado = estado.isValorDefecto() ? new BooleanDTO(): new BooleanDTO().setValor(estado.isValor()).setValorDefecto(false);
		return this;
	}
}
