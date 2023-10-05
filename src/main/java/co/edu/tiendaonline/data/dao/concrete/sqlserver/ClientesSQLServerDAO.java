package co.edu.tiendaonline.data.dao.concrete.sqlserver;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import co.edu.tiendaonline.data.dao.ClienteDAO;
import co.edu.tiendaonline.data.dao.base.SQLDAO;
import co.edu.tiendaonline.data.entity.ClienteEntity;

public class ClientesSQLServerDAO extends SQLDAO implements ClienteDAO  {

	public ClientesSQLServerDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public final void crear(final ClienteEntity cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final void modificar(final ClienteEntity cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final void eliminar(final UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final Optional<ClienteEntity> consultarPorId(final UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public final List<ClienteEntity> consultar(final ClienteEntity cliente) {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

}
