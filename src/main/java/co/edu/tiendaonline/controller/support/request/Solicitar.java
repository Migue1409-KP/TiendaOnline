package co.edu.tiendaonline.controller.support.request;

import java.util.UUID;

import co.edu.tiendaonline.crosscutting.util.UtilTexto;
import co.edu.tiendaonline.crosscutting.util.UtilUUID;

public class Solicitar {
	private UUID id;
	private String codigo;
    private String nombre;
    private boolean estado;
    
	public Solicitar() {
		setId(UtilUUID.UUIDDEFECTO);
		setCodigo(UtilTexto.VACIO);
		setNombre(UtilTexto.VACIO);
		setEstado(false);
	}
	
	public Solicitar(final UUID id, final String codigo, final String nombre, final boolean estado) {
		setId(id);
		setCodigo(codigo);
		setNombre(nombre);
		setEstado(estado);
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
	
	public final void setId(UUID id) {
		this.id = id;
	}

	public final void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public final void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public final void setEstado(boolean estado) {
		this.estado = estado;
	}
}
