package br.ufg.inf.backend.spring_cat_tag.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufg.inf.backend.spring_cat_tag.model.Produto;
import br.ufg.inf.backend.spring_cat_tag.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public List<Produto> listarProdutos() {
		return produtoService.listarProdutos();
	}

	@PostMapping
	public Produto adicionarProduto(@RequestBody Produto produto) {
		return produtoService.salvarProduto(produto);
	}

	@GetMapping("/produtos/editar")
	public String mostrarFormularioEditarProduto(@RequestParam("id") Long id, Model model) {
		Produto produto = produtoService.obterProdutos(id);
		model.addAttribute("produto", produto);
		return "editar-produto";
	}

	@PostMapping("/produtos/editar")
	public String editarProduto(@RequestParam("id") Long id, @RequestParam("nome") String nome,
			@RequestParam("preco") double preco, RedirectAttributes redirectAttributes) {
		Produto produto = produtoService.obterProdutos(id);
		produto.setNome(nome);
		produto.setPreco(preco);
		produtoService.salvarProduto(produto);
		redirectAttributes.addAttribute("sucesso", "Produto atualizado com sucesso!");
		return "redirect:/produtos";
	}

	@PostMapping("/produtos/deletar")
	public String deletarProduto(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		produtoService.deletarProduto(id);
		redirectAttributes.addAttribute("sucesso", "Produto deletado com sucesso!");
		return "redirect:/produtos";
	}

}