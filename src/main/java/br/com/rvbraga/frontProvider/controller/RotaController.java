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
import br.com.rvbraga.frontProvider.models.Rota;
import br.com.rvbraga.frontProvider.service.RotaService;

@Controller
public class RotaController {

	@Autowired
	private RotaService rotaService;
	
	@GetMapping("/rotas")
	public String rotas(Model model) {
		model.addAttribute("rotas", rotaService.listRotas());
		return "/Rota/rotas.xhtml";
	}
	@GetMapping("/rotas/add")
	public String rotaForm(Model model) {
		model.addAttribute("rota",new Rota());
		return "/Rota/rotaForm.xhtml";	
	}
	@GetMapping("/rotas/edit")
	public String editRotaForm(Model model, @RequestParam UUID id) {
		Rota rota = rotaService.getRota(id);
		model.addAttribute("rota",rota);
		System.out.println(rota.toString());
		return "/Rota/rotaForm.xhtml";	
	} 
	@PostMapping("/rotas/save")
	public String saveRota(Model model,@ModelAttribute Rota rota) {	
		rotaService.saveRota(rota);	
		model.addAttribute("message", new Mensagem("text-success","Rota salva!"));
		return rotas(model);
	}
	@PostMapping("/rotas/edit")
	public String saveEditRota(Model model, @ModelAttribute Rota rota) {
		rotaService.updateRota(rota.getId(),rota);	
		model.addAttribute("message", new Mensagem("text-primary","Rota editada!"));
		return rotas(model);
	}
	
	@GetMapping("/rotas/delete")
	public String deleteRota(Model model, @RequestParam UUID id) {
		rotaService.deleteRota(id);
		model.addAttribute("message", new Mensagem("text-danger","Rota deletada!"));
	
		return rotas(model);
	}
}
