package br.com.event.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.event.model.Evento;

/**
 * EventoRepository
 * @author gabnunes
 * @since 15/03/2022
 */

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

	/**
	 * Consulta para buscar eventos por nome
	 * @return List<Evento>
	 */
	@Query("Select e From Evento e where e.nome = ?1")
	List<Evento> findAllByName(String nome);
}
