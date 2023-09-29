package co.edu.tiendaonline.data.dao.daofactory.concrete;

import java.sql.Connection;

import co.edu.tiendaonline.data.dao.ClienteDAO;
import co.edu.tiendaonline.data.dao.TipoIdentificacionDAO;
import co.edu.tiendaonline.data.dao.concrete.sqlserver.ClientesSQLServerDAO;
import co.edu.tiendaonline.data.dao.concrete.sqlserver.TipoIdentificacionSQLServerDAO;
import co.edu.tiendaonline.data.dao.daofactory.DAOFactory;

public final class SQLServerDAOFactory extends DAOFactory {

	private Connection conexion;
	
	public SQLServerDAOFactory() {
		abrirConexion();
	}
	
	@Override
	protected final void abrirConexion() {
		// TODO assign connection 
		conexion = null;
	}

	@Override
	public final void cerarConexion() {
		// TODO close connection
		
	}

	@Override
	public final void iniciarTransaccion() {
		// TODO ionizer transaction
		
	}

	@Override
	public final void confirmarTransaccion() {
		// TODO commit transaction
		
	}

	@Override
	public final void cancelarTransaccion() {
		// TODO rollback transaction
		
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
