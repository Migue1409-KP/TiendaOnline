package co.edu.tiendaonline.data.dao.concrete.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import co.edu.tiendaonline.crosscutting.exception.concrete.DataTiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.crosscutting.util.UtilFecha;
import co.edu.tiendaonline.crosscutting.util.UtilObjeto;
import co.edu.tiendaonline.crosscutting.util.UtilTexto;
import co.edu.tiendaonline.data.dao.ClienteDAO;
import co.edu.tiendaonline.data.dao.base.SQLDAO;
import co.edu.tiendaonline.data.entity.ClienteEntity;
import co.edu.tiendaonline.data.entity.TipoIdentificacionEntity;
import co.edu.tiendaonline.data.entity.support.BooleanEntity;
import co.edu.tiendaonline.data.entity.support.CorreoElectronicoClienteEntity;
import co.edu.tiendaonline.data.entity.support.NombreCompletoClienteEntity;
import co.edu.tiendaonline.data.entity.support.NumeroTelefonoMovilClienteEntity;

public class ClienteSQLServerDAO extends SQLDAO implements ClienteDAO  {

	public ClienteSQLServerDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public final void crear(final ClienteEntity cliente) {
		final var sentencia = new StringBuilder();

		sentencia.append("INSERT INTO Cliente (id, tipoIdentificacion, identificacion, primerNombre, segundoNombre, primerApellido, segundoApellido, "
				+ "correoElectronico, correoElectronicoConfirmado, numeroTelefonoMovil, numeroTelefonoMovilConfirmado, fechaNacimiento) ");
		sentencia.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

		try (final var sentenciaPreparada = getConexion().prepareStatement(sentencia.toString())) {
			sentenciaPreparada.setObject(1, cliente.getId());
			sentenciaPreparada.setObject(2, cliente.getTipoIdentificacion().getId());
			sentenciaPreparada.setString(3, cliente.getIdentificacion());
			sentenciaPreparada.setString(4, cliente.getNombreCompleto().getPrimerNombre());
			sentenciaPreparada.setString(5, cliente.getNombreCompleto().getSegundoNombre());
			sentenciaPreparada.setString(6, cliente.getNombreCompleto().getPrimerApellido());
			sentenciaPreparada.setString(7, cliente.getNombreCompleto().getSegundoApellido());
			sentenciaPreparada.setString(8, cliente.getCorreoElectronico().getCorreoElectronico());
			sentenciaPreparada.setBoolean(9, cliente.getCorreoElectronico().isCorreoElectronicoConfirmado());
			sentenciaPreparada.setString(10, cliente.getNumeroTelefonoMovil().getNumeroTelefonoMovil());
			sentenciaPreparada.setBoolean(11, cliente.getNumeroTelefonoMovil().isNumeroTelefonoMovilConfirmado());
			sentenciaPreparada.setDate(12, cliente.getFechaNacimiento());

			sentenciaPreparada.executeUpdate();

		} catch (final SQLException e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000054);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000055);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		} catch (final Exception e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000054);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000056);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		}
	}

	@Override
	public final void modificar(final ClienteEntity cliente) {
		final var sentencia = new StringBuilder();

		sentencia.append("UPDATE Cliente " );
		sentencia.append("SET	tipoIdentificacion = ?, ");
		sentencia.append("		identificacion = ?, ");
		sentencia.append("		primerNombre = ?, ");
		sentencia.append("		segundoNombre = ?, ");
		sentencia.append("		primerApellido = ?, ");
		sentencia.append("		segundoApellido = ?, ");
		sentencia.append("		correoElectronico = ?, ");
		sentencia.append("		correoElectronicoConfirmado = ?, ");
		sentencia.append("		numeroTelefonoMovil = ?, ");
		sentencia.append("		numeroTelefonoMovilConfirmado = ?, ");
		sentencia.append("		fechaNacimiento = ? ");
		sentencia.append("WHERE id = ? ");
		
		try(final var sentenciaPreparada = getConexion().prepareStatement(sentencia.toString())) {
			sentenciaPreparada.setObject(1, cliente.getTipoIdentificacion().getId());
			sentenciaPreparada.setString(2, cliente.getIdentificacion());
			sentenciaPreparada.setString(3, cliente.getNombreCompleto().getPrimerNombre());
			sentenciaPreparada.setString(4, cliente.getNombreCompleto().getSegundoNombre());
			sentenciaPreparada.setString(5, cliente.getNombreCompleto().getPrimerApellido());
			sentenciaPreparada.setString(6, cliente.getNombreCompleto().getSegundoApellido());
			sentenciaPreparada.setString(7, cliente.getCorreoElectronico().getCorreoElectronico());
			sentenciaPreparada.setBoolean(8, cliente.getCorreoElectronico().isCorreoElectronicoConfirmado());
			sentenciaPreparada.setString(9, cliente.getNumeroTelefonoMovil().getNumeroTelefonoMovil());
			sentenciaPreparada.setBoolean(10, cliente.getNumeroTelefonoMovil().isNumeroTelefonoMovilConfirmado());
			sentenciaPreparada.setDate(11, cliente.getFechaNacimiento());
			sentenciaPreparada.setObject(12, cliente.getId());
			
			sentenciaPreparada.executeUpdate();
			
		} catch (SQLException e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000059);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000057);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		} catch (Exception e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000059);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000058);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		}
	}

	@Override
	public final void eliminar(final UUID id) {
		final var sentencia = new StringBuilder();
		sentencia.append("DELETE FROM Cliente ");
		sentencia.append("WHERE id = ? ");

		try(final var sentenciaPreparada = getConexion().prepareStatement(sentencia.toString())) {
			sentenciaPreparada.setObject(1, id);
			
			sentenciaPreparada.executeUpdate();
			
		} catch (SQLException e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000062);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000060);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		} catch (Exception e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000062);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000061);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		}
	}

	@Override
	public final Optional<ClienteEntity> consultarPorId(final UUID id) {

		final var sentencia = new StringBuilder();
		sentencia.append("SELECT cli.id, cli.tipoIdentificacion, ti.codigo, ti.nombre, ti.estado, cli.identificacion, cli.primerNombre, cli.segundoNombre,"
				+ " cli.primerApellido, cli.segundoApellido, cli.correoElectronico, cli.correoElectronicoConfirmado, cli.numeroTelefonoMovil,"
				+ " cli.numeroTelefonoMovilConfirmado, cli.fechaNacimiento ");
		sentencia.append("FROM  Cliente cli ");
		sentencia.append("JOIN  TipoIdentificacion ti ");
		sentencia.append("	ON  cli.tipoidentificacion = ti.id ");
		sentencia.append("WHERE  id = ? ");

		Optional<ClienteEntity> resultado = Optional.empty();

		try (final var sentenciaPreparada = getConexion().prepareStatement(sentencia.toString())) {
			sentenciaPreparada.setObject(1, id);
			resultado = ejecutarConsultaPorId(sentenciaPreparada);
		} catch (DataTiendaOnlineException e) {
			throw e;
		} catch (SQLException e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000065);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000063);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		} catch (Exception e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000065);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000064);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		}

		return resultado;
	}
	
	@Override
	public final List<ClienteEntity> consultar(final ClienteEntity cliente) {
		final var parametros = new ArrayList<Object>();
		final String sentencia = formarSentenciaConsulta(cliente, parametros);
		
		try(final var sentenciaPreparada = getConexion().prepareStatement(sentencia)) {
			colocarParametrosConsulta(sentenciaPreparada, parametros);
			return ejecutarConsulta(sentenciaPreparada);
		} catch (DataTiendaOnlineException e) {
			throw e;
		} catch (SQLException e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000065);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000072);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		}  catch (Exception e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000065);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000073);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		}
	}

	private final Optional<ClienteEntity> ejecutarConsultaPorId(final PreparedStatement sentenciaPreparada){
		
		Optional<ClienteEntity> resultado = Optional.empty();
		
		try (final var resultados = sentenciaPreparada.executeQuery()) {
			
			if (resultados.next()) {
				var clienteEntity = ClienteEntity.crear(
						UUID.fromString(resultados.getObject("cli.id").toString()), 
						TipoIdentificacionEntity.crear(UUID.fromString(resultados.getObject("cli.tipoIdentificacion").toString()), 
								resultados.getString("ti.codigo"), resultados.getString("ti.nombre"), BooleanEntity.crear(resultados.getBoolean("ti.estado"), false)), 
						resultados.getString("cli.identificacion"),
						NombreCompletoClienteEntity.crear(resultados.getString("cli.primerNombre"), resultados.getString("cli.segundoNombre"), 
								resultados.getString("cli.primerApellido"), resultados.getString("cli.segundoApellido")),
						CorreoElectronicoClienteEntity.crear(resultados.getString("cli.correoElectronico"), resultados.getBoolean("cli.correoElectronicoConfirmado")),
						NumeroTelefonoMovilClienteEntity.crear(resultados.getString("cli.numeroTelefonoMovil"), resultados.getBoolean("cli.numeroTelefonoMovilConfirmado")),
						resultados.getDate("cli.fechaNacimiento")
						);
				resultado = Optional.of(clienteEntity);
			}
		} catch (SQLException e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000065);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000066);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		} catch (Exception e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000065);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000067);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);		
		}
		
		return resultado;
	}
	
	private String formarSentenciaConsulta(final ClienteEntity cliente, final List<Object> parametros) {
		final var sentencia = new StringBuilder();
		String operadorCondicional = "WHERE";
		
		sentencia.append("SELECT cli.id, cli.tipoIdentificacion, ti.codigo, ti.nombre, ti.estado, cli.identificacion, cli.primerNombre, cli.segundoNombre,"
				+ " cli.primerApellido, cli.segundoApellido, cli.correoElectronico, cli.correoElectronicoConfirmado, cli.numeroTelefonoMovil,"
				+ " cli.numeroTelefonoMovilConfirmado, cli.fechaNacimiento");
		sentencia.append("FROM  Cliente cli ");
		sentencia.append("JOIN  TipoIdentificacion ti ");
		sentencia.append("	ON  cli.tipoidentificacion = ti.id ");
		
		if(!UtilObjeto.esNulo(cliente)) {
			if(!UtilObjeto.esNulo(cliente.getId())) {
				sentencia.append(operadorCondicional).append(" cli.id = ?");
				operadorCondicional = "AND";
				parametros.add(cliente.getId());
			}
			
			if(!UtilObjeto.esNulo(cliente.getTipoIdentificacion())) {
				sentencia.append(operadorCondicional).append(" ti.tipoIdentificacion = ? ");
				operadorCondicional = "AND";
				parametros.add(cliente.getTipoIdentificacion().getId());
			}
			
			if (!UtilTexto.estaVacio(cliente.getIdentificacion())) {
				sentencia.append(operadorCondicional).append(" cli.identificacion = ? ");
				operadorCondicional = "AND";
				parametros.add(cliente.getIdentificacion());
			}
			
			if(!UtilObjeto.esNulo(cliente.getNombreCompleto())) {
				if(!UtilTexto.estaVacio(cliente.getNombreCompleto().getPrimerNombre())){
					sentencia.append(operadorCondicional).append(" cli.primerNombre = ? ");
					operadorCondicional = "AND";
					parametros.add(cliente.getNombreCompleto().getPrimerNombre());
				}
				
				if(!UtilTexto.estaVacio(cliente.getNombreCompleto().getSegundoNombre())){
					sentencia.append(operadorCondicional).append(" cli.segundoNombre = ? ");
					operadorCondicional = "AND";
					parametros.add(cliente.getNombreCompleto().getSegundoNombre());
				}
				
				if(!UtilTexto.estaVacio(cliente.getNombreCompleto().getPrimerApellido())){
					sentencia.append(operadorCondicional).append(" cli.primerApellido = ? ");
					operadorCondicional = "AND";
					parametros.add(cliente.getNombreCompleto().getPrimerApellido());
				}
				
				if(!UtilTexto.estaVacio(cliente.getNombreCompleto().getSegundoApellido())){
					sentencia.append(operadorCondicional).append(" cli.segundoApellido = ? ");
					operadorCondicional = "AND";
					parametros.add(cliente.getNombreCompleto().getSegundoApellido());
				}
			}
			
			if(!UtilObjeto.esNulo(cliente.getCorreoElectronico())) {
				if(!UtilTexto.estaVacio(cliente.getCorreoElectronico().getCorreoElectronico())){
					sentencia.append(operadorCondicional).append(" cli.correoElectronico = ? ");
					operadorCondicional = "AND";
					parametros.add(cliente.getCorreoElectronico().getCorreoElectronico());
				}
				
				if(!UtilObjeto.esNulo(cliente.getCorreoElectronico().isCorreoElectronicoConfirmado())){
					sentencia.append(operadorCondicional).append(" cli.correoElectronicoConfirmado = ? ");
					operadorCondicional = "AND";
					parametros.add(cliente.getCorreoElectronico().isCorreoElectronicoConfirmado());
				}
			}
			
			if(!UtilObjeto.esNulo(cliente.getNumeroTelefonoMovil())) {
				if(!UtilTexto.estaVacio(cliente.getNumeroTelefonoMovil().getNumeroTelefonoMovil())){
					sentencia.append(operadorCondicional).append(" cli.numeroTelefonoMovil = ? ");
					operadorCondicional = "AND";
					parametros.add(cliente.getNumeroTelefonoMovil().getNumeroTelefonoMovil());
				}
				
				if(!UtilObjeto.esNulo(cliente.getNumeroTelefonoMovil().isNumeroTelefonoMovilConfirmado())){
					sentencia.append(operadorCondicional).append(" cli.numeroTelefonoMovilConfirmado = ? ");
					operadorCondicional = "AND";
					parametros.add(cliente.getNumeroTelefonoMovil().isNumeroTelefonoMovilConfirmado());
				}
			}
			
			if(!UtilFecha.esNulo(cliente.getFechaNacimiento())) {
				sentencia.append(operadorCondicional).append(" cli.fechaNacimiento = ? ");
				parametros.add(cliente.getFechaNacimiento());
			}			
		}
		
		sentencia.append("ORDER BY cli.primerNombre ASC ");			
		return sentencia.toString();
	}

	private final void colocarParametrosConsulta(final PreparedStatement sentenciaPreparada, final List<Object> parametros) {
		
		try {
			for (int indice = 0; indice < parametros.size(); indice++) {
				sentenciaPreparada.setObject(indice + 1, parametros.get(indice));
			}
		} catch (SQLException e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000065);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000068);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		} catch (Exception e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000065);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000069);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		}
	}
	
	private final List<ClienteEntity> ejecutarConsulta(final PreparedStatement sentenciaPreparada){
		final var listaResultados = new ArrayList<ClienteEntity>();
		
		try (final var resultados = sentenciaPreparada.executeQuery()) {
			
			while (resultados.next()) {
				var clienteEntity = ClienteEntity.crear(
						UUID.fromString(resultados.getObject("cli.id").toString()), 
						TipoIdentificacionEntity.crear(UUID.fromString(resultados.getObject("cli.tipoIdentificacion").toString()), 
								resultados.getString("ti.codigo"), resultados.getString("ti.nombre"), BooleanEntity.crear(resultados.getBoolean("ti.estado"), false)), 
						resultados.getString("cli.identificacion"),
						NombreCompletoClienteEntity.crear(resultados.getString("cli.primerNombre"), resultados.getString("cli.segundoNombre"), 
								resultados.getString("cli.primerApellido"), resultados.getString("cli.segundoApellido")),
						CorreoElectronicoClienteEntity.crear(resultados.getString("cli.correoElectronico"), resultados.getBoolean("cli.correoElectronicoConfirmado")),
						NumeroTelefonoMovilClienteEntity.crear(resultados.getString("cli.numeroTelefonoMovil"), resultados.getBoolean("cli.numeroTelefonoMovilConfirmado")),
						resultados.getDate("cli.fechaNacimiento")
						);
				listaResultados.add(clienteEntity);
			}
		} catch (SQLException e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000065);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000070);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);
		} catch (Exception e) {
			var mensajeUsuario = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000065);
			var mensajeTecnico = CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000071);
			throw DataTiendaOnlineException.crear(e, mensajeUsuario, mensajeTecnico);		
		}
		
		return listaResultados;
	}
}
