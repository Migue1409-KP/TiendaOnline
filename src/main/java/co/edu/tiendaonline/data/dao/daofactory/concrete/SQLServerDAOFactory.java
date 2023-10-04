package co.edu.tiendaonline.data.dao.daofactory.concrete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.tiendaonline.data.dao.daofactory.concrete.SQLServerDAOFactory;
import co.edu.tiendaonline.data.dao.ClienteDAO;
import co.edu.tiendaonline.data.dao.TipoIdentificacionDAO;
import co.edu.tiendaonline.data.dao.concrete.sqlserver.ClientesSQLServerDAO;
import co.edu.tiendaonline.data.dao.concrete.sqlserver.TipoIdentificacionSQLServerDAO;
import co.edu.tiendaonline.data.dao.daofactory.DAOFactory;

public final class SQLServerDAOFactory extends DAOFactory {

	private Connection conexion;
	private static final Logger logger = Logger.getLogger(SQLServerDAOFactory.class.getName());
	
	public SQLServerDAOFactory() {
		abrirConexion();
	}
	
	@Override
	protected final void abrirConexion() {
        try {
            String url = "jdbc:sqlserver://<server>:<port>;databaseName=<database>";
            String user = "<username>";
            String password = "<password>";
            conexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al abrir la conexión", e);
            //TODO Customized exception
            throw new RuntimeException("Error al abrir la conexión", e);
        }
	}

	@Override
	public final void cerarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al cerrar la conexión", e);
            //TODO Customized exception
            throw new RuntimeException("Error al cerrar la conexión", e);
        }
	}

	@Override
	public final void iniciarTransaccion() {
        try {
            if (conexion != null && !conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al iniciar la transacción", e);
            //TODO Customized exception
            throw new RuntimeException("Error al iniciar la transacción", e);
        }
	}

	@Override
	public final void confirmarTransaccion() {
        try {
            if (conexion != null && !conexion.getAutoCommit()) {
                conexion.commit();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al confirmar la transacción", e);
            //TODO Customized exception
            throw new RuntimeException("Error al confirmar la transacción", e);
        }
	}

	@Override
	public final void cancelarTransaccion() {
        try {
            if (conexion != null && !conexion.getAutoCommit()) {
                conexion.rollback();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al cancelar la transacción", e);
            //TODO Customized exception
            throw new RuntimeException("Error al cancelar la transacción", e);
        }
	}

	@Override
	public ClienteDAO obtenerClienteDAO() {
		return new ClientesSQLServerDAO(conexion);
	}

	@Override
	public TipoIdentificacionDAO obtenerTipoIdentificacionDAO() {
		return new TipoIdentificacionSQLServerDAO(conexion);
	}

}
