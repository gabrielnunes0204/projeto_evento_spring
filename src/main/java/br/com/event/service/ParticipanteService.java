package br.com.event.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.event.model.Participante;
import br.com.event.repository.ParticipanteRepository;

/**
 * ParticipanteService
 * @author gabnunes
 * @since 15/03/2022
 */

@Service
public class ParticipanteService {

	@Autowired
	private ParticipanteRepository participanteRepository;
	
	/**
	 * Método para criar um Participante
	 * @param Participante participante
	 * @return Participante
	 */
	public Participante create(Participante participante) {
		return participanteRepository.save(participante);
	}
	
	/**
	 * Método para deletar um Participante pelo ID
	 * @param Long ID
	 * @return boolean
	 */
	public boolean deleteById(Long id) {
		if (id != null && id > 0) {
			participanteRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Método para deletar um Participante
	 * @param Participante participante
	 * @return boolean
	 */
	public boolean delete(Participante participante) {
		if (participante != null) {
			participanteRepository.delete(participante);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Método para alterar um Participante
	 * @param Participante participante, Long ID
	 * @return Participante
	 */
	public Participante update(Participante participante, Long id) {
		Optional<Participante> found = participanteRepository.findById(id);
		
		found.get().setNome(participante.getNome());
		found.get().setCpf(participante.getCpf());
		found.get().setDataNascimento(participante.getDataNascimento());
		
		participanteRepository.save(found.get());
		
		return found.get();
	}
	
	/**
	 * Método para buscar o Participante pelo ID
	 * @param Participante participante
	 * @return Participante
	 */
	public Optional<Participante> findById(Long id) {
		if (id != null && id > 0) {
			Optional<Participante> participante = participanteRepository.findById(id);
			return participante;
		} else {
			return null;
		}
	}
	
	/**
	 * Método para buscar o Participante pelo nome
	 * @param String nome
	 * @return List<Participante>
	 */
	public List<Participante> findAllByName(String nome) {
		List<Participante> listaParticipantes = participanteRepository.findAllByName(nome);
		return listaParticipantes;
	}
	
	/**
	 * Método para buscar todos os Participantes
	 * @return List<Participante>
	 */
	public List<Participante> findAll() {
		return participanteRepository.findAll();
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
	 * Método para validar os campos de Participante
	 * @param Participante participante
	 * @return boolean
	 */
	public boolean validateFields(Participante participante) {
		if (participante.getNome() == null || participante.getCpf() == null || participante.getDataNascimento() == null) {
			return false;
		} else {
			return true;
		}
	}
}
