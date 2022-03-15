package br.com.event.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.event.model.Participante;
import br.com.event.service.ParticipanteService;
import br.com.event.utils.Const;

@Controller
public class AlterarParticipanteController {
	
	@Autowired
	private ParticipanteService participanteService;

	@GetMapping(path = "/alterarParticipante")
	public ModelAndView index(ModelAndView model) {
		Optional<Participante> participante = participanteService.findById(Const.ID_PARTICIPANTE);
		
		model.addObject("participante", participante);
		model.setViewName("alterarParticipante");
		
		return model;
	}
	
	@PostMapping(path = "/alterarParticipante")
	public ModelAndView update(@ModelAttribute("participante") Participante participante, ModelAndView model, RedirectAttributes redirAttrs) {
		participanteService.update(participante, Const.ID_PARTICIPANTE);
		
		redirAttrs.addFlashAttribute("participante_alterado", "Participante alterado com sucesso");
		model.setViewName("redirect:/evento");
		
		return model;
	}
}
