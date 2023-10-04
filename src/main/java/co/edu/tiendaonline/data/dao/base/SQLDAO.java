package co.edu.tiendaonline.data.dao.base;

import java.sql.Connection;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.SQLException;

public class SQLDAO {
	private Connection conexion;
	private static final Logger logger = Logger.getLogger(SQLDAO.class.getName());
	
	protected SQLDAO (final Connection conexion) {
		setConexion(conexion);
	}

	private final void setConexion(final Connection conexion) {
        try {
        	if (conexion == null) {
        		throw new IllegalArgumentException("La conexión no puede ser nula.");
        	}
            if (conexion.isClosed()) {
                throw new IllegalArgumentException("La conexión está cerrada.");
            }

            if (conexion.getAutoCommit()) {
                throw new IllegalArgumentException("La conexión ha confirmado la transacción.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al verificar la conexión", e);
            throw new RuntimeException("Error al verificar la conexión", e);
        }
        
		this.conexion = conexion;
	}
	
	protected final Connection getConexion() {
		return conexion;
	}

}
