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
import org.springframework.web.bind.annotation.RestController;

import co.edu.tiendaonline.controller.support.response.Respuesta;
import co.edu.tiendaonline.crosscutting.exception.TiendaOnlineException;
import co.edu.tiendaonline.crosscutting.messages.CatalogoMensajes;
import co.edu.tiendaonline.crosscutting.messages.enumerator.CodigoMensaje;
import co.edu.tiendaonline.service.dto.TipoIdentificacionDTO;
import co.edu.tiendaonline.service.facade.concrete.tipoidentificacion.RegistrarTipoIdentificacionFacade;

@RestController
@RequestMapping("/api/tipoidentificacion")
public final class TipoIdentificacionController {
	@GetMapping("/dummy")
	public TipoIdentificacionDTO obtenerDummy() {
		return TipoIdentificacionDTO.crear();
	}
	
	@GetMapping
	public String consultar(@RequestBody TipoIdentificacionDTO dto) {
		return "Hola mundo";
	}
	
	@GetMapping("/{id}")
	public String consultarPorId(@PathVariable("id") UUID id) {
		return "Hola mundo";
	}
	
	@PostMapping
	public ResponseEntity<Respuesta<TipoIdentificacionDTO>> registrar(@RequestBody TipoIdentificacionDTO dto) {
		final Respuesta<TipoIdentificacionDTO> respuesta = new Respuesta<>();
		HttpStatus codigoHttp = HttpStatus.BAD_REQUEST;
		
		try {
			RegistrarTipoIdentificacionFacade facade = new RegistrarTipoIdentificacionFacade();
			facade.execute(dto);
			codigoHttp = HttpStatus.OK;
			respuesta.getMensajes().add(CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000130));
		} catch (TiendaOnlineException e) {
			respuesta.getMensajes().add(e.getMensajeTecnico());
			System.err.println(e.getLugar());
			e.getExcepcionRaiz().printStackTrace();
			//TODO: Hacer logging de la exepcion presentada
		} catch (Exception e) {
			respuesta.getMensajes().add(CatalogoMensajes.obtenerContenidoMensaje(CodigoMensaje.M0000000076));
			e.printStackTrace();
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
