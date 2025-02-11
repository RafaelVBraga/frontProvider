package br.com.rvbraga.frontProvider.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import br.com.rvbraga.frontProvider.models.Mensagem;
import br.com.rvbraga.frontProvider.models.Programa;
import br.com.rvbraga.frontProvider.service.ProgramaService;

@Controller
public class ProgramaController {

	@Autowired
	private ProgramaService programaService;
	
	@GetMapping("/programas")
	public String programas(Model model) {
		model.addAttribute("programas", programaService.listProgramas());
		return "/Programa/programas.xhtml";
	}
	@GetMapping("/programas/add")
	public String programaForm(Model model) {
		model.addAttribute("programa",new Programa());
		return "/Programa/programaForm.xhtml";	
	}
	@GetMapping("/programas/edit")
	public String editProgramaForm(Model model, @RequestParam UUID id) {
		Programa programa = programaService.getPrograma(id);
		model.addAttribute("programa",programa);
		System.out.println(programa.toString());
		return "/Programa/programaForm.xhtml";	
	} 
	@PostMapping("/programas/save")
	public String savePrograma(Model model,@ModelAttribute Programa programa) {	
		programaService.savePrograma(programa);	
		model.addAttribute("message", new Mensagem("text-success","Programa salvo!"));
		return programas(model);
	}
	@PostMapping("/programas/edit")
	public String saveEditPrograma(Model model, @ModelAttribute Programa programa) {
		programaService.updatePrograma(programa.getId(),programa);	
		model.addAttribute("message", new Mensagem("text-primary","Programa editado!"));
		return programas(model);
	}
	
	@GetMapping("/programas/delete")
	public String deletePrograma(Model model, @RequestParam UUID id) {
		programaService.deletePrograma(id);
		model.addAttribute("message", new Mensagem("text-danger","Programa deletado!"));
	
		return programas(model);
	}
}
