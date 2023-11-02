package co.edu.tiendaonline.controller.cliente;

import java.sql.Date;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.tiendaonline.controller.support.request.SolicitarCliente;
import co.edu.tiendaonline.controller.support.response.Respuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cliente", description = "Ofrece apis de consumo para el cliente")
public interface ClienteController {

	@Operation(summary = "Obtener dummy", description = "Servicio encargado de obetener la estructura de un cliente")
	SolicitarCliente obtenerDummy();
	
	@Operation(summary = "Modificar Cliente", description = "Servicio encargado de modificar un Cliente a partir de su id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente modificado"),
			@ApiResponse(responseCode = "400", description = "Cliente no modificado"),
			@ApiResponse(responseCode = "500", description = "Problema inesperado") })
	ResponseEntity<Respuesta<SolicitarCliente>> modificar(@PathVariable("id") UUID id, @RequestBody SolicitarCliente req);
	
	@Operation(summary = "Obtener cliente", description = "Servicio encargado de obetener la información de clientes que cumplen los parametros de consulta")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente consultado exitosamente"),
			@ApiResponse(responseCode = "400", description = "Problema con consulta de Cliente "),
			@ApiResponse(responseCode = "500", description = "Problema inesperado") })
	ResponseEntity<Respuesta<SolicitarCliente>> consultar(	
	        @RequestParam(name = "id", required = false) UUID id,
	        @RequestParam(name = "tipoIdentificacion", required = false) UUID tipoIdentificacion,
	        @RequestParam(name = "identificacion", required = false) String identificacion,
	        @RequestParam(name = "primerNombre", required = false) String primerNombre,
	        @RequestParam(name = "segundoNombre", required = false) String segundoNombre,
	        @RequestParam(name = "primerApellido", required = false) String primerApellido,
	        @RequestParam(name = "segundoApellido", required = false) String segundoApellido,
	        @RequestParam(name = "correoElectronico", required = false) String correoElectronico,
	        @RequestParam(name = "correoElectronicoConfirmado", required = false) Boolean correoElectronicoConfirmado,
	        @RequestParam(name = "numeroTelefonoMovil", required = false) String numeroTelefonoMovil,
	        @RequestParam(name = "numeroTelefonoMovilConfirmado", required = false) Boolean numeroTelefonoMovilConfirmado,
	        @RequestParam(name = "fechaNacimiento", required = false) Date fechaNacimiento
			);
	
	@Operation(summary = "Regisrar Cliente", description = "Servicio encargado de registrar un Cliente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente registrado"),
			@ApiResponse(responseCode = "400", description = "Cliente no registrado"),
			@ApiResponse(responseCode = "500", description = "Problema inesperado") })
	ResponseEntity<Respuesta<SolicitarCliente>> registrar(@RequestBody SolicitarCliente req);
	
	@Operation(summary = "Eliminar Cliente", description = "Servicio encargado de eliminar de forma definitiva un Cliente a partir de su id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente eliminado"),
			@ApiResponse(responseCode = "400", description = "Cliente no eliminado"),
			@ApiResponse(responseCode = "500", description = "Problema inesperado") })
	ResponseEntity<Respuesta<SolicitarCliente>> eliminar(@PathVariable("id") UUID id);
}
