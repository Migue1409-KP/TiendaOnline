package co.edu.tiendaonline.crosscutting.util;

import java.sql.Date;

public class UtilDate {
	public static final Date FECHADEFECTO = Date.valueOf("2500-12-31");
	
	private UtilDate() {
		super();	
	}
	
	public static final <O> boolean esNulo(final O objeto) {
		return objeto == null;
	}
	
	public static final Date obtenerValorDefecto(final Date fecha, final Date valorDefecto) {		
		return esNulo(fecha) ? Date.valueOf("2500-12-31"): fecha;
	}
}
