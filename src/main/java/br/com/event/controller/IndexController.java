package br.com.event.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.event.model.Evento;
import br.com.event.service.EventoService;
import br.com.event.utils.Const;

@Controller
public class IndexController {

	@Autowired
	private EventoService eventoService;
	
	@GetMapping(path = "/")
	public ModelAndView index(ModelAndView model) {
		List<Evento> listaEventos = eventoService.findAll();
		
		model.addObject("listaEventos", listaEventos);
		model.setViewName("index");
		
		return model;
	}
	
	@GetMapping(path = "/abrir/{id}")
	public ModelAndView open(@PathVariable(value = "id") Long id, ModelAndView model) {
		Const.ID_EVENTO = id;
		model.setViewName("redirect:/evento");
		
		return model;
	}
	
	@GetMapping(path = "/deletar/{id}")
	public ModelAndView delete(@PathVariable(value = "id") Long id, ModelAndView model, RedirectAttributes redirAttrs) {
		eventoService.deleteById(id);
		
		redirAttrs.addFlashAttribute("evento_excluido", "Evento excluído com sucesso");
		model.setViewName("redirect:/");
		
		return model;
	}
	
	@GetMapping(path = "/chamarAlterarEvento/{id}")
	public ModelAndView update(@PathVariable(value = "id") Long id, ModelAndView model) {
		Const.ID_EVENTO = id;
		model.setViewName("redirect:/alterarEvento");
		
		return model;
	}
}
