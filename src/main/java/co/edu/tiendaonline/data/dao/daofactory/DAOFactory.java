package co.edu.tiendaonline.data.dao.daofactory;

import co.edu.tiendaonline.data.dao.ClienteDAO;
import co.edu.tiendaonline.data.dao.TipoIdentificacionDAO;

public abstract class DAOFactory {
	
	protected abstract void abrirConexion();
	
	public abstract void cerarConexion();
	
	public abstract void iniciarTransaccion();
	
	public abstract void confirmarTransaccion();
	
	public abstract void cancelarTransaccion();
	
	public abstract ClienteDAO obtenerClienteDAO();
	
	public abstract TipoIdentificacionDAO obtenerTipoIdentificacionDAO();
}
