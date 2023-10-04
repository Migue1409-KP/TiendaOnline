package co.edu.tiendaonline.data.dao.daofactory;

import co.edu.tiendaonline.data.dao.ClienteDAO;
import co.edu.tiendaonline.data.dao.TipoIdentificacionDAO;
import co.edu.tiendaonline.data.dao.daofactory.concrete.SQLServerDAOFactory;

public abstract class DAOFactory {
	
	public static final DAOFactory obtenerDAOFactory(final TipoDAOFactory factoria) {
		switch (factoria) {
		case SQLSERVER: {
			return new SQLServerDAOFactory();
		}
		case POSTGRESSQL: {
			//TODO improve customized exceptions
			throw new RuntimeException("POSTGRESSQL no soportada");
		}
		case MYSQL: {
			//TODO improve customized exceptions
			throw new RuntimeException("MYSQL no soportada");
		}
		case ORACLE: {
			//TODO improve customized exceptions
			throw new RuntimeException("ORACLE no soportada");
		}
		default:
			throw new RuntimeException(factoria + " no soportada");
		}
	}
	
	protected abstract void abrirConexion();
	
	public abstract void cerarConexion();
	
	public abstract void iniciarTransaccion();
	
	public abstract void confirmarTransaccion();
	
	public abstract void cancelarTransaccion();
	
	public abstract ClienteDAO obtenerClienteDAO();
	
	public abstract TipoIdentificacionDAO obtenerTipoIdentificacionDAO();
}
