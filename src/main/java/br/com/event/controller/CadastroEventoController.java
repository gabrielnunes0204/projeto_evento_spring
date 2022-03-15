package br.com.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.event.model.Evento;
import br.com.event.service.EventoService;

@Controller
public class CadastroEventoController {
	
	@Autowired
	private EventoService eventoService;

	@GetMapping(path = "/cadastro")
	public ModelAndView index(ModelAndView model) {
		model.setViewName("cadastroEvento");
		
		return model;
	}
	
	@PostMapping(path = "/salvar")
	public ModelAndView create(@ModelAttribute("evento") Evento evento, ModelAndView model, RedirectAttributes redirAttrs) {
		Evento event = new Evento(evento.getNome(), evento.getLocal(), evento.getData(), evento.getHorario());
		
		eventoService.create(event);
		redirAttrs.addFlashAttribute("evento_cadastrado", "Evento cadastrado com sucesso");
		model.setViewName("redirect:/cadastro");
		
		return model;
	}
}
