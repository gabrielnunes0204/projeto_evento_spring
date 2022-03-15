package br.com.event.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.event.model.Evento;
import br.com.event.repository.EventoRepository;

/**
 * EventoService
 * @author gabnunes
 * @since 15/03/2022
 */

@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepository;
	
	/**
	 * Método para criar um Evento
	 * @param Evento evento
	 * @return Evento
	 */
	public Evento create(Evento evento) {
		return eventoRepository.save(evento);
	}
	
	/**
	 * Método para deletar um Evento pelo ID
	 * @param Long ID
	 * @return boolean
	 */
	public boolean deleteById(Long id) {
		if (id != null && id > 0) {
			eventoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Método para deletar um Evento
	 * @param Evento evento
	 * @return boolean
	 */
	public boolean delete(Evento evento) {
		if (evento != null) {
			eventoRepository.delete(evento);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Método para alterar um Evento
	 * @param Long ID, Evento evento
	 * @return Evento
	 */
	public Evento update(Evento evento, Long id) {
		Optional<Evento> found = eventoRepository.findById(id);
		
		found.get().setNome(evento.getNome());
		found.get().setLocal(evento.getLocal());
		found.get().setData(evento.getData());
		found.get().setHorario(evento.getHorario());
		
		eventoRepository.save(found.get());
		
		return found.get();
	}
	
	/**
	 * Método para buscar os Eventos por nome
	 * @param String nome
	 * @return List<Evento>
	 */
	public List<Evento> findAllByName(String nome) {
		List<Evento> listaEventos = eventoRepository.findAllByName(nome);
		if (listaEventos.size() > 0) {
			return listaEventos;
		} else {
			return null;
		}
	}
	
	/**
	 * Método para buscar um Evento pelo ID
	 * @param Long ID
	 * @return Evento
	 */
	public Optional<Evento> findById(Long id) {
		if (id != null && id > 0) {
			Optional<Evento> evento = eventoRepository.findById(id);
			return evento;
		} else {
			return null;
		}
	}
	
	/**
	 * Método para buscar todos os Eventos
	 * @return List<Evento>
	 */
	public List<Evento> findAll() {
		return eventoRepository.findAll();
	}
	
	/**
	 * Método para formatar o nome
	 * @param String nome
	 * @return String
	 */
	public String formatName(String nome) {
		return nome.trim();
	}
	
	/**
	 * Método para validar os campos de Evento
	 * @param Evento evento
	 * @return boolean
	 */
	public boolean validateFields(Evento evento) {
		if (evento.getNome() == null || evento.getLocal() == null || evento.getData() == null || evento.getHorario() == null) {
			return false;
		} else {
			return true;
		}
	}
}
