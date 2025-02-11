package br.com.rvbraga.frontProvider.controller;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.rvbraga.frontProvider.models.Fornecedor;
import br.com.rvbraga.frontProvider.models.Mensagem;
import br.com.rvbraga.frontProvider.models.Produto;
import br.com.rvbraga.frontProvider.service.FornecedorService;
import br.com.rvbraga.frontProvider.service.ProdutoService;

@Controller
public class FornecedorController {
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@Autowired
	private ProdutoService produtoService;


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
		model.addAttribute("message", new Mensagem("text-success","Fornecedor salvo!"));
		return fornecedores(model);
	}
	@PostMapping("/fornecedores/edit")
	public String saveEditFornecedor(Model model, @ModelAttribute Fornecedor fornecedor) {
		fornecedorService.updateFornecedor(fornecedor.getId(),fornecedor);	
		model.addAttribute("message", new Mensagem("text-primary","Fornecedor editado!"));
		return fornecedores(model);
	}
	 
	@GetMapping("/fornecedores/delete")
	public String deleteFornecedor(Model model, @RequestParam UUID id) {
		fornecedorService.deleteFornecedor(id);
		model.addAttribute("message", new Mensagem("text-danger","Fornecedor deletado!"));
	
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
						
			model.addAttribute("message", new Mensagem("text-success","Produto adicionado com sucesso"));
		}
		catch(Exception e) {

			model.addAttribute("message", new Mensagem("text-danger",e.getMessage()));
		
		}
		
		return fornecedorAddProdutos(model,fornecedor.getId());
	}
}
