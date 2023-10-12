package co.edu.tiendaonline.data.dao.concrete.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import co.edu.tiendaonline.crosscutting.exception.concrete.DataTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatologoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.data.dao.TipoIdentificacionDAO;
import co.edu.tiendaonline.data.dao.base.SQLDAO;
import co.edu.tiendaonline.data.entity.TipoIdentificacionEntity;

public final class TipoIdentificacionSQLServerDAO extends SQLDAO implements TipoIdentificacionDAO {

	public TipoIdentificacionSQLServerDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public final void crear(final TipoIdentificacionEntity tipoIdentificacion) {
		final var sentencia = new StringBuilder();

		sentencia.append("INSERT INTO TipoIdentificacion (id, codigo, nombre, estado) ");
		sentencia.append("VALUES (?, ?, ?, ?) ");

		try (final var sentenciaPreparada = getConexion().prepareStatement(sentencia.toString())) {
			sentenciaPreparada.setObject(1, tipoIdentificacion.getId());
			sentenciaPreparada.setString(2, tipoIdentificacion.getCodigo());
			sentenciaPreparada.setString(3, tipoIdentificacion.getNombre());
			sentenciaPreparada.setBoolean(4, tipoIdentificacion.isEstado());

			sentenciaPreparada.executeUpdate();

		} catch (final SQLException e) {
			var mensajeUsuario = CatologoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatologoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000033);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		} catch (final Exception e) {
			var mensajeUsuario = CatologoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000004);
			var mensajeTecnico = CatologoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000034);
			throw DataTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}
	}

	@Override
	public final void modificar(final TipoIdentificacionEntity tipoIdentificacion) {
		final var sentencia = new StringBuilder();

		sentencia.append("UPDATE TipoIdentificacion SET (id, codigo, nombre, estado) WHERE id = (id) ");
	}

	@Override
	public final void eliminar(final UUID id) {
		// TODO Auto-generated method stub

	}

	@Override
	public final Optional<TipoIdentificacionEntity> consultarPorId(final UUID id) {

		final var sentencia = new StringBuilder();
		sentencia.append("SELECT id, codigo, nombre, estado ");
		sentencia.append("FROM  TipoIdentificacion ");
		sentencia.append("WHERE  id = ? ");

		Optional<TipoIdentificacionEntity> resultado = Optional.empty();

		try (final var sentenciaPreparada = getConexion().prepareStatement(sentencia.toString())) {

			sentenciaPreparada.setObject(1, id);

			try (final var resultados = sentenciaPreparada.executeQuery()) {

				if (resultados.next()) {
					var tipoIdentificacionEntity = TipoIdentificacionEntity.crear(
							UUID.fromString(resultados.getObject("id").toString()), resultados.getString("codigo"),
							resultados.getString("nombre"), resultados.getBoolean("estado"));
					resultado = Optional.of(tipoIdentificacionEntity);
				}
			} catch (SQLException e) {
				var mensajeUsuario = CatologoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000037);
				var mensajeTecnico = CatologoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000038);
				throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
			}

		} catch (DataTiendaOnlineException e) {
			throw e;
		} catch (SQLException e) {
			var mensajeUsuario = CatologoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000037);
			var mensajeTecnico = CatologoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000035);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		} catch (Exception e) {
			var mensajeUsuario = CatologoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000037);
			var mensajeTecnico = CatologoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000036);
			throw DataTiendaOnlineException.crear(mensajeUsuario, mensajeTecnico);
		}

		return resultado;
	}

	@Override
	public final List<TipoIdentificacionEntity> consultar(final TipoIdentificacionEntity tipoIdentificacion) {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

}
