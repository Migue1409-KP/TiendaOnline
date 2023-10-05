package co.edu.tiendaonline.crosscutting.util;

public final class UtilTexto {
	
	public static final String VACIO = "";
	
	private UtilTexto() {
		super();
	}
	
	public static final String obtenerValorDefecto(final String valor, final String valorDefecto) {
		return UtilObjeto.obtenerValorDefecto(valor, valorDefecto);
	}
	
	public static final String obtenerValorDefecto(final String valor) {
		return obtenerValorDefecto(valor, VACIO);
	}
	
	public static final String aplicarTrim(final String valor) {
		return obtenerValorDefecto(valor).trim();
	}
	
	public static final boolean igualSinTrim(final String valorUno, final String valorDos) {
		return obtenerValorDefecto(valorUno).equals(obtenerValorDefecto(valorDos));
	}
	
	public static final boolean igualSinTrimIgnoreCase(final String valorUno, final String valorDos) {
		return obtenerValorDefecto(valorUno).equalsIgnoreCase(obtenerValorDefecto(valorDos));
	}
	
	public static final boolean igualConTrim(final String valor1, final String valor2) {
		return aplicarTrim(valor1).equalsIgnoreCase(aplicarTrim(valor2));
	}
	
	public static final boolean igualConTrimIgnoreCase(final String valor1, final String valor2) {
		return aplicarTrim(valor1).equals(aplicarTrim(valor2));
	}
}
