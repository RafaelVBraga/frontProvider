package br.com.rvbraga.frontProvider.controller;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.rvbraga.frontProvider.models.Fornecedor;
import br.com.rvbraga.frontProvider.models.Produto;
import br.com.rvbraga.frontProvider.models.Rota;
import br.com.rvbraga.frontProvider.service.FornecedorService;
import br.com.rvbraga.frontProvider.service.ProdutoService;
import br.com.rvbraga.frontProvider.service.RotaService;
import lombok.Data;

@Controller
@RequestMapping("/alimentacao")
public class MainController {
	@Autowired
	private RotaService rotaService;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	private ProdutoService produtoService;
	
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
		model.addAttribute("message", new Message("text-success","Rota salva!"));
		return rotas(model);
	}
	@PostMapping("/rotas/edit")
	public String saveEditRota(Model model, @ModelAttribute Rota rota) {
		rotaService.updateRota(rota.getId(),rota);	
		model.addAttribute("message", new Message("text-primary","Rota editada!"));
		return rotas(model);
	}
	
	@GetMapping("/rotas/delete")
	public String deleteRota(Model model, @RequestParam UUID id) {
		rotaService.deleteRota(id);
		model.addAttribute("message", new Message("text-danger","Rota deletada!"));
	
		return rotas(model);
	}
	
	/********************************************************************************************************/
	/********************************************************************************************************/
	
	@GetMapping("/fornecedores")
	public String fornecedores(Model model) {
		model.addAttribute("fornecedores", fornecedorService.listFornecedores());
		return "/Fornecedor/fornecedores.xhtml";
	}
	@GetMapping("/fornecedores/add")
	public String fornecedorForm(Model model) {
		model.addAttribute("fornecedor",new Fornecedor());
		return "/Fornecedor/fornecedorForm.xhtml";	
	}
	@GetMapping("/fornecedores/edit")
	public String editFornecedorForm(Model model, @RequestParam UUID id) {
		Fornecedor fornecedor = fornecedorService.getFornecedor(id);
		model.addAttribute("fornecedor",fornecedor);		
		return "/Fornecedor/fornecedorForm.xhtml";	
	} 
	@PostMapping("/fornecedores/save")
	public String saveFornecedor(Model model,@ModelAttribute Fornecedor fornecedor) {	
		fornecedorService.saveFornecedor( fornecedor);	
		model.addAttribute("message", new Message("text-success","Fornecedor salvo!"));
		return fornecedores(model);
	}
	@PostMapping("/fornecedores/edit")
	public String saveEditFornecedor(Model model, @ModelAttribute Fornecedor fornecedor) {
		fornecedorService.updateFornecedor(fornecedor.getId(),fornecedor);	
		model.addAttribute("message", new Message("text-primary","Fornecedor editado!"));
		return fornecedores(model);
	}
	 
	@GetMapping("/fornecedores/delete")
	public String deleteFornecedor(Model model, @RequestParam UUID id) {
		fornecedorService.deleteFornecedor(id);
		model.addAttribute("message", new Message("text-danger","Fornecedor deletado!"));
	
		return fornecedores(model); 
	}
	
	@GetMapping("/fornecedores/fornecedor/produtos")
	private String fornecedorAddProdutos(Model model, UUID id) {
		Fornecedor fornecedor = fornecedorService.getFornecedor(id);
		model.addAttribute("produto", new Produto());
		model.addAttribute("fornecedor", fornecedor);
		return "/Fornecedor/fornecedorProdutosForm.xhtml";
	}
	@PostMapping("/fornecedores/produto/add")	
	private String fornecedorAddProduto(Model model, @RequestParam UUID idFornecedor, @ModelAttribute Produto produto) {
		Fornecedor fornecedor = fornecedorService.getFornecedor(idFornecedor);		
		if(fornecedor.getProdutos()==null) 
			fornecedor.setProdutos(new ArrayList<>());
		try {
			produto.setFornecedor(fornecedor);	
			produtoService.saveProduto(produto);		
						
			model.addAttribute("message", new Message("text-success","Produto adicionado com sucesso"));
		}
		catch(Exception e) {

			model.addAttribute("message", new Message("text-danger",e.getMessage()));
		
		}
		
		return fornecedorAddProdutos(model,fornecedor.getId());
	}
	
	
	@Data
	class Message {
		private String tipo;
		private String texto;
		Message(String tipo, String texto){
			this.tipo = tipo;
			this.texto = texto;
		}
	}
}
