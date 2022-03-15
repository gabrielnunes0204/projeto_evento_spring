package br.com.event.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.event.model.Participante;

/**
 * ParticipanteRepository
 * @author gabnunes
 * @since 15/03/2022
 */

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

	/**
	 * Consulta para buscar participantes por nome
	 * @return List<Participante>
	 */
	@Query("Select p From Participante p where p.nome = ?1")
	List<Participante> findAllByName(String nome);
}
