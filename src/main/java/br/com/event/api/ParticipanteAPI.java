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
import br.com.event.model.Participante;
import br.com.event.service.ParticipanteService;

/**
 * ParticipanteAPI
 * @author gabnunes
 * @since 15/03/2022
 */

@RestController
@RequestMapping(path = "/api/participantes")
public class ParticipanteAPI {

	@Autowired
	private ParticipanteService participanteService;
	
	/**
	 * Endpoint para buscar todos os eventos
	 * @return List<Evento>
	 */
	@GetMapping
	public ResponseEntity<List<Participante>> findAll() {
		List<Participante> listaParticipantes = participanteService.findAll();
		
		if (participanteService.findAll().size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).eTag("Participante criado com sucesso").body(listaParticipantes);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag("Lista de Participantes vazia").build();
		}
	}
	
	/**
	 * Endpoint para buscar um participante pelo ID
	 * @param Long ID
	 * @return Participante
	 */
	@GetMapping(path = "consulta/{id}")
	public ResponseEntity<Participante> findById(@PathVariable(value = "id") Long id) {
		Optional<Participante> participante = participanteService.findById(id);
		
		if (participante.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(participante.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag("Participante não encontrado").build();
		}
	}
	
	/**
	 * Endpoint para buscar um participante pelo nome
	 * @param String nome
	 * @return List<Participante>
	 */
	@GetMapping(path = "nome/{nome}")
	public ResponseEntity<List<Participante>> findAllByName(@PathVariable(value = "nome") String nome) {
		if (!nome.equals("") && nome != null) {
			String nomeFormatado = participanteService.formatName(nome);
			List<Participante> listaParticipantes = participanteService.findAllByName(nomeFormatado);
			if (listaParticipantes.size() > 0 && listaParticipantes != null) {
				return ResponseEntity.status(HttpStatus.OK).body(listaParticipantes);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag("Participante não encontrado").build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag("Erro nos dados informados").build();
		}
	}
	
	/**
	 * Endpoint para criar um Participante
	 * @param Participante participante
	 * @return Participante
	 */
	@PostMapping(path = "/criar")
	@ResponseBody
	public ResponseEntity<Participante> create(@RequestBody Participante participante) {
		
		if (participanteService.validateFields(participante)) {
			participanteService.create(participante);
			return ResponseEntity.status(HttpStatus.CREATED).body(participante);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).eTag("Erros nos dados informados").build();
		}
	}
	
	/**
	 * Endpoint para deletar um Participante
	 * @param Long ID
	 * @return Evento
	 */
	@DeleteMapping(path = "/deletar/{id}")
	public ResponseEntity<Participante> delete(@PathVariable(value = "id") Long id) {
		Optional<Participante> participante = participanteService.findById(id);
		
		if (participante.isPresent()) {
			participanteService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(participante.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag("Participante não encontrado").build();
		}
	}
	
	/**
	 * Endpoint para alterar um Participante
	 * @param Long ID, Participante participante
	 * @return Participante
	 */
	@PutMapping(path = "/alterar/{id}")
	public ResponseEntity<Participante> update(@PathVariable(value = "id") Long id, @RequestBody Participante participante) {
		Optional<Participante> part = participanteService.findById(id);
		
		if (part != null && participanteService.validateFields(participante)) {
			participanteService.update(participante, id);
			return ResponseEntity.status(HttpStatus.OK).eTag("Participante alterado com sucesso").body(part.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).eTag("Participante não encontrado").build();
		}
	}
}
