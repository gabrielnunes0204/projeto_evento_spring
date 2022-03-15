package br.com.event.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.event.model.Evento;
import br.com.event.model.Participante;
import br.com.event.service.EventoService;
import br.com.event.service.ParticipanteService;
import br.com.event.utils.Const;

@Controller
public class EventoController {

	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private ParticipanteService participanteService;
	
	@GetMapping(path = "/evento")
	public ModelAndView index(ModelAndView model) {
		Optional<Evento> evento = eventoService.findById(Const.ID_EVENTO);
		model.addObject("participantes", evento.get().getParticipantes());
		
		model.addObject("evento", evento.get());
		model.setViewName("evento");
		
		return model;
	}
	
	@PostMapping(path = "/adicionar")
	public ModelAndView create(@ModelAttribute("participante") Participante particpante, ModelAndView model, RedirectAttributes redirAttrs) {
		Participante aluno = new Participante(particpante.getNome(), particpante.getCpf(), particpante.getDataNascimento());
		Optional<Evento> evento = eventoService.findById(Const.ID_EVENTO);
		
		evento.get().getParticipantes().add(aluno);
		aluno.setEvento(evento.get());
		eventoService.create(evento.get());
		redirAttrs.addFlashAttribute("participante_cadastrado", "Participante cadastrado com sucesso");
		model.setViewName("redirect:/evento");
		
		return model;
	}
	
	@GetMapping(path = "/excluir/{id}")
	public ModelAndView delete(@PathVariable(value = "id") Long id, ModelAndView model, RedirectAttributes redirAttrs) {
		Optional<Participante> participante = participanteService.findById(id);
		Optional<Evento> evento = eventoService.findById(Const.ID_EVENTO);
		
		evento.get().getParticipantes().remove(participante.get());
		participanteService.deleteById(id);
		eventoService.create(evento.get());
		redirAttrs.addFlashAttribute("participante_excluido", "Participante excluído com sucesso");
		model.setViewName("redirect:/evento");
		
		return model;
	}
	
	@GetMapping(path = "/chamarAlterarParticipante/{id}")
	public ModelAndView update(@PathVariable(value = "id") Long id, ModelAndView model) {
		Const.ID_PARTICIPANTE = id;
		model.setViewName("redirect:/alterarParticipante");
		
		return model;
	}
}
