package br.com.event.api;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.event.model.Evento;
import br.com.event.service.EventoService;

/**
 * EventoAPI
 * @author gabnunes
 * @since 15/03/2022
 */

@RestController
@RequestMapping(path = "/api/eventos")
public class EventoAPI {

	@Autowired
	private EventoService eventoService;
	
	/**
	 * Endpoint para buscar todos os eventos
	 * @return List<Evento>
	 */
	@GetMapping
	public ResponseEntity<List<Evento>> findAll() {
		List<Evento> listaEventos = eventoService.findAll();
		
		if (eventoService.findAll().size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(listaEventos);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag("Lista de eventos vazia").build();
		}
	}
	
	/**
	 * Endpoint para buscar um evento pelo ID
	 * @param Long ID
	 * @return Evento
	 */
	@GetMapping(path = "/consultar/{id}")
	public ResponseEntity<Evento> findById(@PathVariable(value = "id") Long id) { 
		Optional<Evento> evento = eventoService.findById(id);
		
		if (evento.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(evento.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag("Evento não encontrado").build();
		}
	}
	
	/**
	 * Endpoint para buscar um evento pelo nome
	 * @param String nome
	 * @return Evento
	 */
	@GetMapping(path = "/nome/{nome}")
	public ResponseEntity<List<Evento>> findAllByName(@PathVariable(value = "nome") String nome) {
		if (!nome.equals("") && nome != null) {
			String nomeFormatado = eventoService.formatName(nome);
			List<Evento> listaEventos = eventoService.findAllByName(nomeFormatado);
			if (listaEventos.size() > 0 && listaEventos != null) {
				return ResponseEntity.status(HttpStatus.OK).body(listaEventos);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag("Evento não encontrado").build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag("Erro nos dados informados").build();
		}
	}
	
	/**
	 * Endpoint para criar um Evento
	 * @param Evento evento
	 * @return Evento
	 */
	@PostMapping(path = "/criar")
	@ResponseBody
	public ResponseEntity<Evento> create(@RequestBody Evento evento) {
		
		if (eventoService.validateFields(evento)) {
			eventoService.create(evento);
			return ResponseEntity.status(HttpStatus.CREATED).eTag("Evento criado com sucesso").body(evento);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).eTag("Erros nos dados informados").build();
		}
	}
	
	/**
	 * Endpoint para deletar um Evento
	 * @param Long ID
	 * @return Evento
	 */
	@DeleteMapping(path = "/deletar/{id}")
	public ResponseEntity<Evento> delete(@PathVariable(value = "id") Long id) {
		Optional<Evento> evento = eventoService.findById(id);
		
		if (evento.isPresent()) {
			eventoService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(evento.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag("Evento não encontrado").build();
		}
	}
	
	/**
	 * Endpoint para alterar um Evento
	 * @param Long ID, Evento evento
	 * @return Evento
	 */
	@PutMapping(path = "/alterar/{id}")
	public ResponseEntity<Evento> update(@PathVariable(value = "id") Long id, @RequestBody Evento evento) {
		Optional<Evento> event = eventoService.findById(id);
		
		if (event != null && eventoService.validateFields(evento)) {
			eventoService.update(event.get(), id);
			return ResponseEntity.status(HttpStatus.OK).eTag("Evento alterado com sucesso").body(event.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag("Evento não encontrado").build();
		}
	}
}
