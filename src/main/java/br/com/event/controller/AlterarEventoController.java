package br.com.event.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.event.model.Evento;
import br.com.event.service.EventoService;
import br.com.event.utils.Const;

@Controller
public class AlterarEventoController {

	@Autowired
	private EventoService eventoService;
	
	@GetMapping(path = "/alterarEvento")
	public ModelAndView index(ModelAndView model) {
		Optional<Evento> evento = eventoService.findById(Const.ID_EVENTO);
		
		model.addObject("evento", evento);
		model.setViewName("alterarEvento");
		
		return model;
	}
	
	@PostMapping(path = "/alterarEvento")
	public ModelAndView update(@ModelAttribute("evento") Evento evento, ModelAndView model, RedirectAttributes redirAttrs) {
		eventoService.update(evento, Const.ID_EVENTO);
		
		redirAttrs.addFlashAttribute("evento_alterado", "Evento alterado com sucesso");
		model.setViewName("redirect:/");
		
		return model;
	}
}
