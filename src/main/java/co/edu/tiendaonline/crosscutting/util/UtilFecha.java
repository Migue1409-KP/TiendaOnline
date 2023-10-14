package co.edu.tiendaonline.crosscutting.util;

import java.time.LocalDate;
import java.util.Objects;

public class UtilFecha {
    public static final LocalDate FECHADEFECTO = LocalDate.of(2500, 12, 31);

    private UtilFecha() {
        super();
    }

    public static final <O> boolean esNulo(final O objeto) {
        return Objects.isNull(objeto);
    }

    public static final LocalDate obtenerValorDefecto(final LocalDate fecha, final LocalDate valorDefecto) {
        return esNulo(fecha) ? valorDefecto : fecha;
    }
}
