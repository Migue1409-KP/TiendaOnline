package co.edu.tiendaonline.crosscutting.util;

import java.sql.Date;
import java.util.Objects;

public class UtilFecha {
    public static final Date FECHADEFECTO = Date.valueOf("2500-12-31");

    private UtilFecha() {
        super();
    }

    public static final <O> boolean esNulo(final O objeto) {
        return Objects.isNull(objeto);
    }

    public static final Date obtenerValorDefecto(final Date fecha, final Date valorDefecto) {
        return esNulo(fecha) ? valorDefecto : fecha;
    }
}

