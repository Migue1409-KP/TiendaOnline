package co.edu.tiendaonline.data.dao.daofactory.concrete;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import co.edu.tiendaonline.data.dao.daofactory.concrete.SQLServerDAOFactory;
import co.edu.tiendaonline.crosscutting.exception.concrete.DataTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilSQL;
import co.edu.tiendaonline.data.dao.ClienteDAO;
import co.edu.tiendaonline.data.dao.TipoIdentificacionDAO;
import co.edu.tiendaonline.data.dao.concrete.sqlserver.ClienteSQLServerDAO;
import co.edu.tiendaonline.data.dao.concrete.sqlserver.TipoIdentificacionSQLServerDAO;
import co.edu.tiendaonline.data.dao.daofactory.DAOFactory;

public final class SQLServerDAOFactory extends DAOFactory {

	private Connection conexion;
	
	public SQLServerDAOFactory() {
		abrirConexion();
	}
	
	@Override
	protected final void abrirConexion() {
        try {
			Properties prop = new Properties();
			InputStream input = getClass().getResourceAsStream("/application.properties");
			prop.load(input);
			String url = prop.getProperty("db.url");
			String user = prop.getProperty("db.user");
			String password = prop.getProperty("db.password");
            
            if (url == null || url.isEmpty() || user == null || user.isEmpty() || password == null || password.isEmpty()) {
            	var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
    			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000023);
    			throw DataTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
            }
            
            conexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000024);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
        } catch (Exception e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000025);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);        	
        }
	}

	@Override
	public final void cerrarConexion() {
		UtilSQL.cerrarConexion(conexion);
	}

	@Override
	public final void iniciarTransaccion() {
		UtilSQL.iniciarTransaccion(conexion);
	}

	@Override
	public final void confirmarTransaccion() {
		UtilSQL.confirmarTransaccion(conexion);
	}

	@Override
	public final void cancelarTransaccion() {
		UtilSQL.cancelarTransaccion(conexion);
	}

	@Override
	public ClienteDAO obtenerClienteDAO() {
        if(!UtilSQL.conexionAbierta(conexion)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000027);
			throw DataTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);		
        }
		return new ClienteSQLServerDAO(conexion);
	}

	@Override
	public TipoIdentificacionDAO obtenerTipoIdentificacionDAO() {
        if(!UtilSQL.conexionAbierta(conexion)) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000027);
			throw DataTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);		
        }
		return new TipoIdentificacionSQLServerDAO(conexion);
	}

}
