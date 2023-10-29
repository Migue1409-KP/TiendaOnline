package co.edu.tiendaonline.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import co.edu.tiendaonline.controller.support.mapper.TipoIdentificacionResponse;
import co.edu.tiendaonline.controller.support.request.Solicitar;
import co.edu.tiendaonline.controller.support.response.Respuesta;
import co.edu.tiendaonline.crosscutting.exception.TiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.service.dto.BooleanDTO;
import co.edu.tiendaonline.service.dto.TipoIdentificacionDTO;
import co.edu.tiendaonline.service.facade.concrete.tipoidentificacion.ConsultarTipoIdentificacionFacade;
import co.edu.tiendaonline.service.facade.concrete.tipoidentificacion.RegistrarTipoIdentificacionFacade;

@RestController
@RequestMapping("/api/tipoidentificacion")
public final class TipoIdentificacionController {
    private static final Logger logger = LogManager.getLogger(TipoIdentificacionController.class);
    
	@GetMapping("/dummy")
	public Solicitar obtenerDummy() {
		return new Solicitar();
	}
	
	@GetMapping
	public ResponseEntity<Respuesta<Solicitar>> consultar(
			@RequestParam(name = "id", required = false) UUID id,
			@RequestParam(name = "codigo", required = false) String codigo,
			@RequestParam(name = "nombre", required = false) String nombre,
			@RequestParam(name = "estado", required = false) Boolean estado) {
		final Respuesta<Solicitar> respuesta = new Respuesta<>();
		HttpStatus codigoHttp = HttpStatus.BAD_REQUEST;
		var dto = TipoIdentificacionDTO.crear()
				.setId(id)
				.setCodigo(codigo)
				.setNombre(nombre)
				.setEstado(BooleanDTO.crear().setValor(estado != null ? estado: false).setValorDefecto(estado == null));

		try {
			ConsultarTipoIdentificacionFacade facade = new ConsultarTipoIdentificacionFacade();
			respuesta.setDatos(TipoIdentificacionResponse.convertListToResponse(facade.execute(dto)));
			codigoHttp = HttpStatus.OK;
			respuesta.getMensajes().add(CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000133));
		} catch (TiendaOnlineException e) {
			respuesta.getMensajes().add(e.getMensajeTecnico());
			logger.error(e.getLugar(), e);
		} catch (Exception e) {
			respuesta.getMensajes().add(CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000088));
			logger.error(e);
		}

		return new ResponseEntity<>(respuesta, codigoHttp);
	}
	
	@PostMapping
	public ResponseEntity<Respuesta<TipoIdentificacionDTO>> registrar(@RequestBody Solicitar req) {
		final Respuesta<TipoIdentificacionDTO> respuesta = new Respuesta<>();
		HttpStatus codigoHttp = HttpStatus.BAD_REQUEST;
		
		try {
			RegistrarTipoIdentificacionFacade facade = new RegistrarTipoIdentificacionFacade();
			var dto = TipoIdentificacionDTO.crear()
			.setNombre(req.getNombre())
			.setCodigo(req.getCodigo())
			.setEstado(BooleanDTO.crear()
					.setValor(req.isEstado())
					.setValorDefecto(false));
			facade.execute(dto);
			codigoHttp = HttpStatus.OK;
			respuesta.getMensajes().add(CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000130));
		} catch (TiendaOnlineException e) {
			respuesta.getMensajes().add(e.getMensajeTecnico());
			//System.err.println(e.getLugar());
			//e.getExcepcionRaiz().printStackTrace();
			logger.error(e.getLugar(), e);
		} catch (Exception e) {
			respuesta.getMensajes().add(CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000076));
			//e.printStackTrace();
			logger.error(e);
		}
		
		return new ResponseEntity<>(respuesta, codigoHttp);
	}
	
	@PutMapping("/{id}")
	public String modificar(@PathVariable("id") UUID id, @RequestBody TipoIdentificacionDTO dto) {
		return "Hola mundo";
	}
	
	@DeleteMapping("/{id}")
	public String eliminar(@PathVariable("id") UUID id) {
		return "Hola mundo";
	}
}
