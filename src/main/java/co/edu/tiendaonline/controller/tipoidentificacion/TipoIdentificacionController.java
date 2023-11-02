package co.edu.tiendaonline.controller.tipoidentificacion;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.tiendaonline.controller.support.request.SolicitarTipoIdentificacion;
import co.edu.tiendaonline.controller.support.response.Respuesta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "TipoIdentificacion", description = "Ofrece apis de consumo para el tipo de identificación")
public interface TipoIdentificacionController {

	@Operation(summary = "Obtener dummy", description = "Servicio encargado de obetener la estructura de un tipo de identificación")
	SolicitarTipoIdentificacion obtenerDummy();

	@Operation(summary = "Obtener tipo de identificación", description = "Servicio encargado de obetener la información de tipos de identificación que cumples los parametros de consulta")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tipo identificación consultado exitosamente"),
			@ApiResponse(responseCode = "400", description = "Problema con consulta de tipo de identificación"),
			@ApiResponse(responseCode = "500", description = "Problema inesperado") })
	ResponseEntity<Respuesta<SolicitarTipoIdentificacion>> consultar(
			@RequestParam(name = "id", required = false) UUID id,
			@RequestParam(name = "codigo", required = false) String codigo,
			@RequestParam(name = "nombre", required = false) String nombre,
			@RequestParam(name = "estado", required = false) Boolean estado);

	@Operation(summary = "Regisrar tipo de identificación", description = "Servicio encargado de registrar un tipo de identificación")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tipo identificación registrado"),
			@ApiResponse(responseCode = "400", description = "Tipo de identificación no registrado"),
			@ApiResponse(responseCode = "500", description = "Problema inesperado") })
	ResponseEntity<Respuesta<SolicitarTipoIdentificacion>> registrar(@RequestBody SolicitarTipoIdentificacion req);

	@Operation(summary = "Modificar tipo de identificación", description = "Servicio encargado de modificar un tipo de identificación a partir de su id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tipo identificación modificado"),
			@ApiResponse(responseCode = "400", description = "Tipo de identificación no modificado"),
			@ApiResponse(responseCode = "500", description = "Problema inesperado") })
	ResponseEntity<Respuesta<SolicitarTipoIdentificacion>> modificar(@PathVariable("id") UUID id,
			@RequestBody SolicitarTipoIdentificacion req);

	@Operation(summary = "Eliminar tipo de identificación", description = "Servicio encargado de eliminar de forma definitiva un tipo de identificación a partir de su id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Tipo identificación eliminado"),
			@ApiResponse(responseCode = "400", description = "Tipo de identificación no eliminado"),
			@ApiResponse(responseCode = "500", description = "Problema inesperado") })
	ResponseEntity<Respuesta<SolicitarTipoIdentificacion>> eliminar(@PathVariable("id") UUID id);
}
